package Piruru.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ChainPatch {

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class ChainedField {
        public static SpireField<Boolean> chained = new SpireField<>(() -> false);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class IsCostModifiedForTurnField {
        public static SpireField<Boolean> costModifiedTurn = new SpireField<>(() -> false);
    }
}
