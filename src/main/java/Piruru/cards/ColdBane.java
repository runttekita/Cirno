package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import Piruru.powers.Cold;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class ColdBane extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(ColdBane.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE_UP = 3;
    private static final int BLOCK_UP = 0;
    private static final int COLD_UP = 0;
    private static final int DAMAGE = 9;
    private static final int BLOCK = 0;
    private static final int COLD = 1;

    public ColdBane() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, COLD_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = COLD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
        if (m.hasPower(makeID(Cold.class))) {
            act(new ApplyPowerAction(m, p, new Cold(m), 1));
        }
    }
}
