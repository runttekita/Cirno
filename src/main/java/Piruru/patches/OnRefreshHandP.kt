package Piruru.patches

import Piruru.interfaces.OnRefreshHand
import Piruru.stances.Allos
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower
import javassist.CtBehavior

class OnRefreshHandP {

    @SpirePatch(clz = CardGroup::class, method = "refreshHandLayout")
    class OnRefreshHandPatch {
        @SpireInsertPatch(locator = PatchLocators.OnRefreshLocator::class)
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

    }
}
