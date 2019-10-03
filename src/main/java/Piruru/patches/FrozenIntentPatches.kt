package Piruru.patches

import Piruru.Piruluk
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

object FrozenIntentPatches {

    private val tip: PowerTip
        get() {
            val tip = PowerTip()
            val strings = CardCrawlGame.languagePack.getUIString("Piruru:FrozenIntent")
            tip.header = strings.TEXT[0]
            tip.body = strings.TEXT[1]
            tip.img = Piruluk.textureLoader.getTexture("Piruru/images/FrozenIntent.png")
            return tip
        }

    object FrozenIntentEnum {
        @SpireEnum
        var FROZEN: AbstractMonster.Intent? = null
    }

    @SpirePatch(clz = AbstractMonster::class, method = "getIntentImg")
    object FrozenIntentImage {
        fun Prefix(__instance: AbstractMonster): SpireReturn<Texture> {
            return if (__instance.intent == FrozenIntentEnum.FROZEN) {
                SpireReturn.Return(Piruluk.textureLoader.getTexture(
                        "Piruru/images/intents/Frozen.png"
                ))
            } else SpireReturn.Continue()
        }
    }

    @SpirePatch(clz = AbstractMonster::class, method = "updateIntentTip")
    object FrozenIntentTip {
        fun Prefix(__instance: AbstractMonster): SpireReturn<*> {
            if (__instance.intent == FrozenIntentEnum.FROZEN) {
                ReflectionHacks.setPrivate(__instance, AbstractMonster::class.java, "intentTip", tip)
                return SpireReturn.Return<Any>(null)
            }
            return SpireReturn.Continue<Any>()
        }
    }

}
