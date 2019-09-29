package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FastDrawPileToDiscardPileAction extends AbstractGameAction {
    private AbstractCard c;

    public FastDrawPileToDiscardPileAction(AbstractCard c) {
        this.c = c;
    }

    @Override
    public void update() {
        AbstractDungeon.player.drawPile.moveToDiscardPile(c);
        isDone = true;
    }
}