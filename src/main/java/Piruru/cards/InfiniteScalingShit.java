package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.RecoverAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



public class InfiniteScalingShit extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(InfiniteScalingShit.class.getSimpleName()));
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int RECOVER_AMT = 1;
    private static final int RECOVER_UP = 2;

    public InfiniteScalingShit() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, RECOVER_UP, COST);
        baseMagicNumber = magicNumber = RECOVER_AMT;
        exhaust = true;
    }

    @Override
    public void upgrade() {
        timesUpgraded++;
        if (!upgraded) {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
        upgraded = true;
        name = cardStrings.NAME + "+" + timesUpgraded;
        initializeTitle();
        upgradeMagicNumber(RECOVER_UP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new RecoverAction(magicNumber));
    }
}
