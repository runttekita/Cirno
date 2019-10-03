package Piruru.powers;

import Piruru.abstracts.PiruruPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FlameBarrierButColdP extends PiruruPower implements CloneablePowerInterface {

    public FlameBarrierButColdP(AbstractCreature owner, int amount) {
        this.owner = owner;
        this.amount = amount;
        isTurnBased = true;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(info.owner, owner,
                new Cold(info.owner), 1));
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(owner, owner, this, 1));
        if (amount == 0) {
            AbstractDungeon.actionManager.addToBottom
                    (new RemoveSpecificPowerAction(AbstractDungeon.player,
                            AbstractDungeon.player, this));
        }
    }

    @Override
    public void updateDescription() {
        description = getDESCRIPTIONS()[0] + getDESCRIPTIONS()[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new FlameBarrierButColdP(owner, amount);
    }
}
