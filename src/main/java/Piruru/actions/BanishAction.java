package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BanishAction extends AbstractGameAction {
    private int amount;

    public BanishAction(int amount) {
        this.amount = amount;
        duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            if (AbstractDungeon.player.discardPile.isEmpty()) {
                isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.discardPile, amount,  "", false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(
                        c, AbstractDungeon.player.discardPile
                ));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        isDone = true;
    }
}
