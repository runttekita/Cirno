package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ScoutCardsAction extends AbstractGameAction {
    private int scoutNum;

    public ScoutCardsAction(int scoutNum) {
        this.scoutNum = scoutNum;
    }
    @Override
    public void update() {
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            isDone = true;
            return;
        }
        AbstractCard c;
        if (AbstractDungeon.player.drawPile.size() >= scoutNum) {
            for (int i = 0; i < scoutNum; i++) {
                c = AbstractDungeon.player.drawPile.group.get(i);
               checkCard(c);
            }
        } else {
            for (AbstractCard ca : AbstractDungeon.player.drawPile.group) {
                checkCard(ca);
            }
        }

        isDone = true;
    }

    private void checkCard(AbstractCard c) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new DrawPileToHandAction(c));
        } else {
            AbstractDungeon.actionManager.addToBottom(new DrawPileToDiscardPileAction(c));
        }
    }
}
