package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.ReturnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



public class AttackReturnAttacks extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(AttackReturnAttacks.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE_UP = 3;
    private static final int BLOCK_UP = 0;
    private static final int RETURN_UP = 1;
    private static final int DAMAGE = 9;
    private static final int BLOCK = 0;
    private static final int RETURN = 2;

    public AttackReturnAttacks() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, RETURN_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = RETURN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ReturnAction(c -> c.type == CardType.ATTACK, magicNumber));
    }
}
