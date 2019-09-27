package Piruru.powers;

import Piruru.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Piruru.Piruru.makePowerPath;

public class ColdPower extends AbstractPower implements CloneablePowerInterface {
    private static final int PROC_AMOUNT = 3;

    public ColdPower(AbstractCreature owner, int amount) {
        this.owner = owner;
        this.amount = amount;
        img = TextureLoader.getTexture(makePowerPath(ColdPower.class.getSimpleName()));
    }

    @Override
    public void onInitialApplication() {
        if (amount >= PROC_AMOUNT) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, AbstractDungeon.player, new FrozenPower(owner)));
        }
    }

    @Override
    public void stackPower(int amount) {
        fontScale = 8.0F;
        this.amount += amount;
        if (amount >= PROC_AMOUNT) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, AbstractDungeon.player, new FrozenPower(owner)));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ColdPower(owner, amount);
    }
}
