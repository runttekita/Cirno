package Piruru.cards;

import Piruru.abstracts.PiruruCard;
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
            makeID(SpreadCold.class));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE = 7;
    private static final int DAMAGE_UP = 3;
    private static final int SPREAD_AMT = 1;

    public SpreadCold() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, 0, COST);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = SPREAD_AMT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
        act(new SpreadColdAction(m, magicNumber));
    }
}
