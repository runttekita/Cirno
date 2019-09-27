package Piruru.powers;

import Piruru.TextureLoader;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Piruru.Piruru.makePowerPath;

public class FrozenPower extends AbstractPower implements CloneablePowerInterface {


    public FrozenPower(AbstractCreature owner) {
        this.owner = owner;
        this.amount = 1;
        img = TextureLoader.getTexture(makePowerPath(FrozenPower.class.getSimpleName()));
    }

    @Override
    public AbstractPower makeCopy() {
        return new FrozenPower(owner);
    }
}
