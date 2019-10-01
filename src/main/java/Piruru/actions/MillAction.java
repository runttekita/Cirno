package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Consume;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

import java.util.function.Consumer;


public class MillAction extends AbstractGameAction {
    private int amount;
    private Consumer<AbstractCard> callback = null;

    public MillAction(int amount) {
        this.amount = amount;
    }

    public MillAction(int amount, Consumer<AbstractCard> callback) {
        this(amount);
        this.callback = callback;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.drawPile.size() == 0) {
            isDone = true;
            return;
        }
        AbstractCard c = AbstractDungeon.player.drawPile.getTopCard();
        AbstractDungeon.player.drawPile.removeCard(c);
        AbstractDungeon.player.discardPile.removeCard(c);
        AbstractDungeon.effectsQueue.add(new ShowCardAndAddToDiscardEffect(c));
        amount--;
        if (amount > 0) {
            if (callback != null) {
                callback.accept(c);
                AbstractDungeon.actionManager.addToBottom(new MillAction(amount, callback));
                isDone = true;
                return;
            }
            AbstractDungeon.actionManager.addToBottom(new MillAction(amount));
        }
        isDone = true;
    }
}
