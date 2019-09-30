package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.BanishAction;
import Piruru.actions.RecoverAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class BanishThenRecover extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(BanishThenRecover.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int RECOVER_AMT = 1;
    private static final int RECOVER_UP = 1;

    public BanishThenRecover() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, RECOVER_UP, COST);
        baseMagicNumber = magicNumber = RECOVER_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new BanishAction(magicNumber));
        act(new RecoverAction(magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(RECOVER_UP);
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
