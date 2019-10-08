package cirno.patches

import cirno.Cirno
import basemod.ReflectionHacks
import cirno.Cirno.Statics.makeID
import com.badlogic.gdx.graphics.Texture
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.PowerTip
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.evacipated.cardcrawl.modthespire.lib.LineFinder
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.Matcher.MethodCallMatcher
import javassist.CtBehavior
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator
import com.esotericsoftware.spine.SkeletonMeshRenderer
import java.util.Arrays
import com.evacipated.cardcrawl.modthespire.lib.Matcher.FieldAccessMatcher
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost
import cirno.powers.Frozen
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch



object FrozenIntentPatches {

    private val tip: PowerTip
        get() {
            val tip = PowerTip()
            val strings = CardCrawlGame.languagePack.getUIString("Cirno:FrozenIntent")
            tip.header = strings.TEXT[0]
            tip.body = strings.TEXT[1]
            tip.img = Cirno.textureLoader.getTexture("cirno/images/FrozenIntent.png")
            return tip
        }

    public class Enum{
        object FrozenIntentEnum {
            @SpireEnum
            @JvmStatic
            var FROZEN: AbstractMonster.Intent? = null
        }
    }

    @SpirePatch(clz = AbstractMonster::class, method = "getIntentImg")
    object FrozenIntentImage {
        @JvmStatic
        fun Prefix(__instance: AbstractMonster): SpireReturn<Texture> {
            return if (__instance.intent == FrozenIntentPatches.Enum.FrozenIntentEnum.FROZEN) {
                SpireReturn.Return(Cirno.textureLoader.getTexture(
                        "cirno/images/intents/Frozen.png"
                ))
            } else SpireReturn.Continue()
        }
    }

    @SpirePatch(clz = AbstractMonster::class, method = "updateIntentTip")
    object FrozenIntentTip {
        @JvmStatic
        fun Prefix(__instance: AbstractMonster): SpireReturn<*> {
            if (__instance.intent == FrozenIntentPatches.Enum.FrozenIntentEnum.FROZEN) {
                ReflectionHacks.setPrivate(__instance, AbstractMonster::class.java, "intentTip", tip)
                return SpireReturn.Return<Any>(null)
            }
            return SpireReturn.Continue<Any>()
        }
    }

    @SpirePatch(clz = AbstractMonster::class, method = "render")
    object FrozenIntentShading {
        private val shader = ShaderProgram(
                Gdx.files.internal("cirno/shaders/frozen/vertexShader.vs"),
                Gdx.files.internal("cirno/shaders/frozen/fragShader.fs")
        )


        @SpireInsertPatch(locator = LocatorImageStart::class, localvars = ["atlas"])
        @JvmStatic
        fun InsertImageStart(__instance: AbstractMonster, sb: SpriteBatch, atlas: TextureAtlas?) {
            if (atlas == null && __instance !is Hexaghost) {
                val frozen = __instance.getPower(makeID(Frozen::class.java)) as Frozen
                if (frozen != null) {
                    shader.begin()
                    shader.setUniformf("shadeTimer", frozen.shaderTimer)
                    sb.shader = shader
                }
            }
        }


        @SpireInsertPatch(locator = LocatorImageEnd::class, localvars = ["atlas"])
        @JvmStatic
        fun InsertImageEnd(__instance: AbstractMonster, sb: SpriteBatch, atlas: TextureAtlas?) {
            if (atlas == null && __instance.hasPower(makeID(Frozen::class.java)) && __instance !is Hexaghost) {
                sb.shader = null
                shader.end()
            }
        }

        @SpireInsertPatch(locator = LocatorSkeletonStart::class)
        @JvmStatic
        fun InsertSkeletonStart(__instance: AbstractMonster, sb: SpriteBatch) {
            val frozen = if (__instance.getPower(makeID(Frozen::class.java)) != null) {
                __instance.getPower(makeID(Frozen::class.java)) as Frozen
            } else {
                null
            }
            if (frozen != null && __instance !is Hexaghost) {
                shader.begin()
                shader.setUniformf("shadeTimer", frozen.shaderTimer)
                CardCrawlGame.psb.shader = shader
            }
        }

        @SpireInsertPatch(locator = LocatorSkeletonEnd::class)
        @JvmStatic
        fun InsertSkeletonEnd(__instance: AbstractMonster, sb: SpriteBatch) {
            if (__instance.hasPower(makeID(Frozen::class.java)) && __instance !is Hexaghost) {
                CardCrawlGame.psb.shader = null
                shader.end()
            }
        }


        private class LocatorImageStart : SpireInsertLocator() {
            @Throws(Exception::class)
            override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
                val finalMatcher = Matcher.FieldAccessMatcher(AbstractMonster::class.java, "atlas")
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher)
            }
        }

        private class LocatorImageEnd : SpireInsertLocator() {
            @Throws(Exception::class)
            override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
                val finalMatcher = Matcher.FieldAccessMatcher(AbstractMonster::class.java, "isDying")
                return Arrays.copyOfRange(LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher), 1, 2)
            }
        }

        private class LocatorSkeletonStart : SpireInsertLocator() {
            @Throws(Exception::class)
            override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
                val finalMatcher = Matcher.MethodCallMatcher(SkeletonMeshRenderer::class.java, "draw")
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher)
            }
        }

        private class LocatorSkeletonEnd : SpireInsertLocator() {
            @Throws(Exception::class)
            override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
                val finalMatcher = Matcher.MethodCallMatcher(PolygonSpriteBatch::class.java, "end")
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher)
            }
        }
    }

}
