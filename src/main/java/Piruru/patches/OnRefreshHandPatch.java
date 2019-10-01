package Piruru.patches;

import Piruru.interfaces.OnRefreshHand;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

@SpirePatch(
        clz = CardGroup.class,
        method = "refreshHandLayout"
)
public class OnRefreshHandPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(CardGroup __instance) {
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnRefreshHand) {
                ((OnRefreshHand)p).onRefreshHand();
            }
        }
    }

    public static class Locator extends SpireInsertLocator {

        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(CardGroup.class, "group");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
