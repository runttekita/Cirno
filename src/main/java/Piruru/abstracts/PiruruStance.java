package Piruru.abstracts;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static Piruru.Piruru.makeID;

public abstract class PiruruStance extends AbstractStance {
    public String STANCE_ID;
    public StanceStrings stanceStrings;

    public PiruruStance() {
        super();
        STANCE_ID = makeID(this.getClass());
        ID = STANCE_ID;
        stanceStrings = CardCrawlGame.languagePack.getStanceString(ID);
        name = stanceStrings.NAME;
        updateDescription();
    }

}
