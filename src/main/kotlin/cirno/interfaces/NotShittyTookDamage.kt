package cirno.interfaces

import cirno.characters.spellZones
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import javassist.CtBehavior

interface NotShittyTookDamage {

    fun notShittyTookDamage(i: DamageInfo)

    @SpirePatch(clz = AbstractPlayer::class, method = "damage")
    object NotShittyTookDamagePatch {
        @SpireInsertPatch(locator = Locator::class)
        @JvmStatic
        fun Insert(__instance: AbstractPlayer, i: DamageInfo) {
            for (c in AbstractDungeon.player.hand.group) {
                if (c is NotShittyTookDamage) {
                    (c as NotShittyTookDamage).notShittyTookDamage(i)
                }
            }
            AbstractDungeon.player.spellZones.notShittyTookDamage(i)
        }
    }

    class Locator : SpireInsertLocator() {

        @Throws(Exception::class)
        override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
            val finalMatcher = Matcher.MethodCallMatcher(AbstractPlayer::class.java, "updateCardsOnDamage")
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher)
        }
    }

}
