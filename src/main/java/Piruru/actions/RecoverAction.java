package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * This is literally just a wrapper for Hologram's action.
 * Why?
 * I can't stand the fucking name.
 * @see com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction
 */
public class RecoverAction extends AbstractGameAction {
    private int amount;

    public RecoverAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToBottom(new BetterDiscardPileToHandAction(amount));
        isDone = true;
    }
}
