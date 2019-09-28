package Piruru.cards;

import Piruru.actions.MillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class AttackAndMill extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(AttackAndMill.class));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE = 9;
    private static final int DAMAGE_UP = 3;
    private static final int MILL_AMT = 4;
    private static final int MILL_UP = 4;

    public AttackAndMill() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, MILL_UP, COST);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MILL_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
        act(new MillAction(magicNumber));
    }
}
