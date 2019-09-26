package Piruru.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FrozenPower extends AbstractPower implements CloneablePowerInterface {


    public FrozenPower(AbstractCreature owner, int amount) {
        this.owner = owner;
        this.amount = amount;
    }

    @Override
    public AbstractPower makeCopy() {
        return new FrozenPower(owner, amount);
    }
}
