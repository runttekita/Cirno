package Piruru.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ReturnAction extends MoveCardsAction {

    public ReturnAction(Predicate<AbstractCard> predicate, int amount) {
        super(AbstractDungeon.player.exhaustPile, AbstractDungeon.player.discardPile, predicate, amount);
    }

    public ReturnAction(Predicate<AbstractCard> predicate, int amount, Consumer<List<AbstractCard>> callback) {
        super(AbstractDungeon.player.exhaustPile, AbstractDungeon.player.discardPile, predicate, amount, callback);
    }
}
