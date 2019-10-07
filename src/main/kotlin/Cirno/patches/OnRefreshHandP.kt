package Cirno.patches

import Cirno.interfaces.OnRefreshHand
import Cirno.stances.Allos
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class OnRefreshHandP {

    @SpirePatch(clz = CardGroup::class, method = "refreshHandLayout")
    object OnRefreshHandPatch {
        @SpireInsertPatch(locator = PatchLocators.OnRefreshLocator::class)
        @JvmStatic
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
