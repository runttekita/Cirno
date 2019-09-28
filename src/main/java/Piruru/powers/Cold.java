package Piruru.powers;

import Piruru.abstracts.PiruruPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Cold extends PiruruPower implements CloneablePowerInterface {
    private static final int PROC_AMOUNT = 3;

    public Cold(AbstractCreature owner, int amount) {
        super();
        this.owner = owner;
        this.amount = amount;
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
        if (this.amount >= PROC_AMOUNT) {
            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(owner, owner, this));
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(owner, AbstractDungeon.player, new Frozen(owner)));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new Cold(owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + PROC_AMOUNT + DESCRIPTIONS[1];
    }
}
