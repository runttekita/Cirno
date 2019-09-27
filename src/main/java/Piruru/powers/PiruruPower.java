package Piruru.powers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Piruru.Piruru.*;

public abstract class PiruruPower extends AbstractPower {
    public static String POWER_ID;
    private static PowerStrings powerStrings;
    public static String NAME;
    public static String[] DESCRIPTIONS;


    public PiruruPower() {
        POWER_ID = makeID(this.getClass().getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
        name = NAME;
        ID = POWER_ID;
        img = textureLoader.getTexture(makePowerPath(this.getClass().getSimpleName()));
        updateDescription();
    }

    public abstract void updateDescription();
}
