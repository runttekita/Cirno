package Piruru.interfaces;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;

import java.util.ArrayList;

public interface OnRecover {

    public void onRecover(AbstractCard c);

    @SpirePatch(
            clz = BetterDiscardPileToHandAction.class,
            method = "update"
    )
    public static class HologramTriggerOnRecover {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {
                        "c"
                }
        )
        public static void Insert(BetterDiscardPileToHandAction __instance, AbstractCard c) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof OnRecover) {
                    ((OnRecover)p).onRecover(c);
                }
            }
        }

        public static class Locator extends SpireInsertLocator {

            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(BetterDiscardPileToHandAction.class, "tickDuration");
                int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
                return new int[]{lines[lines.length - 1]};
            }
        }
    }

}
