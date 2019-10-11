package cirno.interfaces

import cirno.characters.spellZones
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import javassist.CtBehavior

interface NotShittyOnAttackedSpell : Spell {

    fun notShittyOnAttacked(info: DamageInfo)

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "damage"
    )
    public class NotShittyOnAttackedPatch {
        public companion object {
            @SpireInsertPatch(
                    locator = Locator::class
            )
            @JvmStatic
            fun Insert(__instance: AbstractPlayer, info: DamageInfo) {
                if (info.owner != null) {
                    __instance.spellZones.notShittyTookDamage(info)
                }
            }

        }
    }

    public class Locator : SpireInsertLocator() {
        override fun Locate(p0: CtBehavior?): IntArray {
            val matcher = Matcher.MethodCallMatcher(Math::class.java, "min")
            return LineFinder.findInOrder(p0, matcher)
        }

    }
}