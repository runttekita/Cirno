package Piruru.cards;

import Piruru.actions.SpreadColdAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class SpreadCold extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(SpreadCold.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final int DAMAGE = 7;
    private static final int DAMAGE_UP = 3;
    private static final int SPREAD_AMT = 1;

    public SpreadCold() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, 0, COST);
        baseDamage = damage = DAMAGE;
        isMultiDamage = true;
        baseMagicNumber = magicNumber = SPREAD_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE));
        act(new SpreadColdAction(m, magicNumber));
    }
}
