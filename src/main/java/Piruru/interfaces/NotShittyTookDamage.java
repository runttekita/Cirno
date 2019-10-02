package Piruru.interfaces;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;

public interface NotShittyTookDamage {

    public void notShittyTookDamage(DamageInfo i);

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class NotShittyTookDamagePatch {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static void Insert(AbstractPlayer __instance, DamageInfo i) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c instanceof NotShittyTookDamage) {
                    ((NotShittyTookDamage)c).notShittyTookDamage(i);
                }
            }
        }
    }

    public static class Locator extends SpireInsertLocator {

        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "updateCardsOnDamage");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
