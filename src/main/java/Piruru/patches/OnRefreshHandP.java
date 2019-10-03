package Piruru.patches;

import Piruru.interfaces.OnRefreshHand;
import Piruru.stances.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

public class OnRefreshHandP {

    @SpirePatch(
            clz = CardGroup.class,
            method = "refreshHandLayout"
    )
    public static class OnRefreshHandPatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(CardGroup __instance) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnRefreshHand) {
                    ((OnRefreshHand) p).onRefreshHand();
                }
            }
            if (AbstractDungeon.player.stance instanceof Allos) {
                ((OnRefreshHand) AbstractDungeon.player.stance).onRefreshHand();
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
}
