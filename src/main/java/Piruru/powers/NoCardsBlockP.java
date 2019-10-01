package Piruru.powers;

import Piruru.abstracts.PiruruPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NoCardsBlockP extends PiruruPower {

    public NoCardsBlockP(AbstractCreature owner, int amount) {
        this.owner = owner;
        this.amount = amount;
    }

    @Override
    public void onDrawOrDiscard() {
        if (AbstractDungeon.player.hand.isEmpty()) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
