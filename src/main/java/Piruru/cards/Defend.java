package Piruru.cards;

import Piruru.Piruru;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Defend extends PiruruCard {
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int BLOCK = 5;
    private static final int BLOCK_UP = 3;

    public Defend() {
        super(COST, TYPE, RARITY, TARGET, 0, BLOCK_UP, 0, COST);
        baseBlock = block = BLOCK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
