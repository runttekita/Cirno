package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class ExhaustDiscardAndRecover extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(ExhaustDiscardAndRecover.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int RECOVER_AMT = 1;
    private static final int RECOVER_UP = 1;

    public ExhaustDiscardAndRecover() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, RECOVER_UP, COST);
        baseMagicNumber = magicNumber = RECOVER_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}
