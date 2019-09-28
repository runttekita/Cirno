package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.powers.Cold;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class DamageAndCold extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(DamageAndCold.class));
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE = 12;
    private static final int DAMAGE_UP = 3;
    private static final int COLD_AMT = 1;
    private static final int COLD_UP = 1;

    public DamageAndCold() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, COLD_UP, COST);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = COLD_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
        act(power(m, p, new Cold(m, magicNumber), magicNumber));
    }
}
