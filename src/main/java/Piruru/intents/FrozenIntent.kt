package Piruru.intents

import Piruru.Piruluk.Statics.makeID
import Piruru.powers.Frozen
import basemod.ReflectionHacks
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.esotericsoftware.spine.SkeletonMeshRenderer
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.PowerTip
import com.megacrit.cardcrawl.localization.UIStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost
import javassist.CtBehavior


import Piruru.Piruluk.Statics.textureLoader

/**
 * Shader logic stolen and modified from
 * https://github.com/kiooeht/Hubris/blob/29d2f37cfc4035e29e3567671fbde158833004b2/src/main/java/com/evacipated/cardcrawl/mod/hubris/patches/UndeadRenderPatch.java
 * Thank you papa Kio!
 */
object FrozenIntent {
    @SpireEnum
    var FROZEN: AbstractMonster.Intent? = null

    private val tip: PowerTip
        get() {
            val tip = PowerTip()
            val strings = CardCrawlGame.languagePack.getUIString("Piruru:FrozenIntent")
            tip.header = strings.TEXT[0]
            tip.body = strings.TEXT[1]
            tip.img = textureLoader.getTexture("Piruru/images/FrozenIntent.png")
            return tip
        }

    @SpirePatch(clz = AbstractMonster::class, method = "getIntentImg")
    object FrozenIntentImage {
        fun Prefix(__instance: AbstractMonster): SpireReturn<Texture> {
            return if (__instance.intent == FROZEN) {
                SpireReturn.Return(textureLoader.getTexture(
                        "Piruru/images/intents/Frozen.png"
                ))
            } else SpireReturn.Continue()
        }
    }

    @SpirePatch(clz = AbstractMonster::class, method = "updateIntentTip")
    object FrozenIntentTip {
        fun Prefix(__instance: AbstractMonster): SpireReturn<*> {
            if (__instance.intent == FROZEN) {
                ReflectionHacks.setPrivate(__instance, AbstractMonster::class.java, "intentTip", tip)
                return SpireReturn.Return<Any>(null)
            }
            return SpireReturn.Continue<Any>()
        }
    }

    @SpirePatch(clz = AbstractMonster::class, method = "render")
    object FrozenIntentShading {
        private val shader = ShaderProgram(
                Gdx.files.internal("Piruru/shaders/frozen/vertexShader.vs"),
                Gdx.files.internal("Piruru/shaders/frozen/fragShader.fs")
        )

        private val renderer = ShapeRenderer()


        @SpireInsertPatch(locator = LocatorImageStart::class, localvars = ["atlas"])
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
        fun InsertImageEnd(__instance: AbstractMonster, sb: SpriteBatch, atlas: TextureAtlas?) {
            if (atlas == null && __instance.hasPower(makeID(Frozen::class.java)) && __instance !is Hexaghost) {
                sb.shader = null
                shader.end()
            }
        }

        @SpireInsertPatch(locator = LocatorSkeletonStart::class)
        fun InsertSkeletonStart(__instance: AbstractMonster, sb: SpriteBatch) {
            val frozen = __instance.getPower(makeID(Frozen::class.java)) as Frozen
            if (frozen != null && __instance !is Hexaghost) {
                shader.begin()
                shader.setUniformf("shadeTimer", frozen.shaderTimer)
                CardCrawlGame.psb.shader = shader
                renderer.begin(ShapeRenderer.ShapeType.Filled)
                renderer.color = Color.BLUE
                val x = __instance.drawX
                val y = __instance.drawY
                renderer.rect(x, y, __instance.hb_w, __instance.hb_h)
            }
        }

        @SpireInsertPatch(locator = LocatorSkeletonEnd::class)
        fun InsertSkeletonEnd(__instance: AbstractMonster, sb: SpriteBatch) {
            if (__instance.hasPower(makeID(Frozen::class.java)) && __instance !is Hexaghost) {
                CardCrawlGame.psb.shader = null
                shader.end()
                renderer.end()
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
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher)
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
