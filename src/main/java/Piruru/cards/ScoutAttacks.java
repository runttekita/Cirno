package Piruru.cards;

import Piruru.Piruru;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class ScoutAttacks extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(ScoutAttacks.class.getSimpleName()));
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int SCOUT_NUM = 3;
    private static final int SCOUT_NUM_UP = 3;

    public ScoutAttacks() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, SCOUT_NUM_UP, COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }
}