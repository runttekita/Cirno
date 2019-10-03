package Piruru.abstracts;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Piruru.Piruluk.*;

public abstract class PiruruPower extends AbstractPower {
    static PowerStrings powerStrings;
    public String POWER_ID;
    public String NAME;
    public String[] DESCRIPTIONS;


    public PiruruPower() {
        POWER_ID = makeID(this.getClass().getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        name = NAME;
        ID = POWER_ID;
        updateDescription();
        img = textureLoader.getTexture(makePowerPath(this.getClass().getSimpleName()));
    }

    public abstract void updateDescription();

    @Override
    public void stackPower(int amount) {
        fontScale = 8.0F;
        this.amount += amount;
    }

}
