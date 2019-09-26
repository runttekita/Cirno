package Piruru.cards;

import Piruru.Piruru;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class    DamageAndFreeze extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(DamageAndFreeze.class.getSimpleName()));
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE = 9;
    private static final int DAMAGE_UP = 6;

    public DamageAndFreeze() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, 0, COST);
        baseDamage = damage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
    }
}
