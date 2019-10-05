package Piruru.patches;

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;

public class PatchLocators {

    public static class OnRefreshLocator extends SpireInsertLocator {

        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(CardGroup.class, "group");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    public static class ARTSLocator extends SpireInsertLocator {

        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "cantUseMessage");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    public static class LocatorImageStart extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "atlas");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    public static class LocatorImageEnd extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "isDying");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    public static class LocatorSkeletonStart extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(SkeletonMeshRenderer.class, "draw");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    public static class LocatorSkeletonEnd extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(PolygonSpriteBatch.class, "end");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
