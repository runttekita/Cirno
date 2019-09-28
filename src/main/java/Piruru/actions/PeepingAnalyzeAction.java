package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PeepingAnalyzeAction extends AbstractGameAction {
    private int cost;

    public PeepingAnalyzeAction(int cost) {
        this.cost = cost;
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cost == cost) {
                AbstractDungeon.actionManager.addToTop(new FastDrawPileToDiscardPileAction(c));
            }
        }
        isDone = true;
    }
}
