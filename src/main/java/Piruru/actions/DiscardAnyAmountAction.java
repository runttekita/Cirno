package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.function.Consumer;



public class DiscardAnyAmountAction extends AbstractGameAction {
    private Consumer<ArrayList<AbstractCard>> callback;

    public DiscardAnyAmountAction(Consumer<ArrayList<AbstractCard>> callback) {
        duration = Settings.ACTION_DUR_FASTER;
        this.callback = callback;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            UIStrings uiString = CardCrawlGame.languagePack.getUIString(makeID(DiscardAnyAmountAction.class));
            String[] TEXT = uiString.TEXT;
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 999, true, true);
            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            if (callback != null) {
                callback.accept(AbstractDungeon.handCardSelectScreen.selectedCards.group);
            }
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
        }
        isDone = true;
    }
}
