package Piruru.interfaces

import Piruru.stances.Allos
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower
import javassist.CtBehavior

interface OnRefreshHand {

    fun onRefreshHand()


    @SpirePatch(clz = CardGroup::class, method = "refreshHandLayout")
    object OnRefreshHandPatch {
        @SpireInsertPatch(locator = Locator::class)
        fun Insert(__instance: CardGroup) {
            for (p in AbstractDungeon.player.powers) {
                if (p is OnRefreshHand) {
                    (p as OnRefreshHand).onRefreshHand()
                }
            }
            if (AbstractDungeon.player.stance is Allos) {
                (AbstractDungeon.player.stance as OnRefreshHand).onRefreshHand()
            }
        }

        class Locator : SpireInsertLocator() {

            @Throws(Exception::class)
            override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
                val finalMatcher = Matcher.FieldAccessMatcher(CardGroup::class.java, "group")
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher)
            }
        }
    }

}
