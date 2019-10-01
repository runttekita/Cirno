package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class AttackDiscard extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(AttackDiscard.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE_UP = 4;
    private static final int BLOCK_UP = 0;
    private static final int DISCARD_UP = 1;
    private static final int DAMAGE = 13;
    private static final int BLOCK = 0;
    private static final int DISCARD = 2;

    public AttackDiscard() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DISCARD_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = DISCARD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
        act(new DiscardAction(p, p, magicNumber, true));
    }
}
