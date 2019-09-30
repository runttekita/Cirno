package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import static Piruru.Piruru.makeID;

public class DiscardToGainEnergyAction extends AbstractGameAction {

    public DiscardToGainEnergyAction() {
        duration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            UIStrings uiString = CardCrawlGame.languagePack.getUIString(makeID(DiscardToGainEnergyAction.class));
            String[] TEXT = uiString.TEXT;
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 999, true, true);
            AbstractDungeon.player.hand.applyPowers();
            this.tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            int energyToGain = 0;
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                energyToGain += c.cost;
                AbstractDungeon.player.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(energyToGain));
        }
        isDone = true;
    }
}
