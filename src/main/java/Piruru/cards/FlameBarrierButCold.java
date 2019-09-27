package Piruru.cards;

import Piruru.Piruru;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class FlameBarrierButCold extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(FlameBarrierButCold.class.getSimpleName()));
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int BLOCK = 12;
    private static final int BLOCK_UP = 4;
    private static final int COLD = 1;

    public FlameBarrierButCold() {
        super(cardStrings, COST, TYPE, RARITY, TARGET,0, BLOCK_UP, 0, COST);
        baseBlock = block = BLOCK;
        magicNumber = baseMagicNumber = COLD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        block();
        act(power(p, p, new Piruru.powers.FlameBarrierButCold(p, magicNumber), magicNumber));
    }
}
