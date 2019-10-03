package Piruru.powers;

import Piruru.abstracts.PiruruPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Cold extends PiruruPower implements CloneablePowerInterface {
    private static final int PROC_AMOUNT = 3;

    public Cold(AbstractCreature owner) {
        super();
        this.owner = owner;
        this.amount = 1;
        isTurnBased = true;
    }

    public Cold(int amount, AbstractCreature owner) {
        super();
        this.amount = amount;
        this.owner = owner;
        isTurnBased = true;
    }

    @Override
    public void onInitialApplication() {
        if (amount >= PROC_AMOUNT) {
            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(owner, owner, this));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(owner, AbstractDungeon.player, new Frozen(owner)));
        }
    }

    @Override
    public void stackPower(int amount) {
        fontScale = 8.0F;
        this.amount += amount;
        if (this.amount >= PROC_AMOUNT && amount - PROC_AMOUNT < 1) {
            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(owner, owner, this));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(owner, AbstractDungeon.player, new Frozen(owner)));
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    new ReducePowerAction(owner, owner, this, PROC_AMOUNT));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(owner, AbstractDungeon.player, new Frozen(owner)));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new Cold(amount, owner);
    }

    @Override
    public void updateDescription() {
        description = getDESCRIPTIONS()[0] + PROC_AMOUNT + getDESCRIPTIONS()[1];
    }
}
