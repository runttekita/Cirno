package Cirno.patches

import Cirno.Cirno
import basemod.ReflectionHacks
import com.badlogic.gdx.graphics.Texture
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.PowerTip
import com.megacrit.cardcrawl.monsters.AbstractMonster

object FrozenIntentPatches {

    private val tip: PowerTip
        get() {
            val tip = PowerTip()
            val strings = CardCrawlGame.languagePack.getUIString("Piruru:FrozenIntent")
            tip.header = strings.TEXT[0]
            tip.body = strings.TEXT[1]
            tip.img = Cirno.textureLoader.getTexture("Piruru/images/FrozenIntent.png")
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
                        "Piruru/images/intents/Frozen.png"
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

}
