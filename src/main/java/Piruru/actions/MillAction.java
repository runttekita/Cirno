package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MillAction extends AbstractGameAction {
    private int amount;

    public MillAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        for (int i = 0; i < amount; i++) {
            AbstractDungeon.player.drawPile.getTopCard().moveToDiscardPile();
        }
        isDone = true;
    }
}
