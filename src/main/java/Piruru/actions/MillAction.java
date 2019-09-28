package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;


/**
 * Card rendering from here
 * @see com.megacrit.cardcrawl.actions.common.PlayTopCardAction
 **/
public class MillAction extends AbstractGameAction {
    private int amount;

    public MillAction(int amount) {
        this.amount = amount;
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
            AbstractDungeon.actionManager.addToBottom(new MillAction(amount));
        }
        isDone = true;
    }
}
