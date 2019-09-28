package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawPileToHandAction extends AbstractGameAction {
    private AbstractCard c;

    public DrawPileToHandAction(AbstractCard c) {
        this.c = c;
    }

    @Override
    public void update() {
        AbstractDungeon.player.drawPile.moveToHand(c,
                AbstractDungeon.player.hand);
        isDone = true;
    }
}
