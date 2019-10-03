package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.BanishAction;
import Piruru.actions.RecoverAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruluk.makeID;

public class BanishThenRecover extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(BanishThenRecover.class.getSimpleName()));
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int RECOVER_AMT = 2;
    private static final int COST_UP = 1;

    public BanishThenRecover() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST_UP);
        baseMagicNumber = magicNumber = RECOVER_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new BanishAction(magicNumber, ifSuccess -> act(new RecoverAction(magicNumber))));
    }
}
