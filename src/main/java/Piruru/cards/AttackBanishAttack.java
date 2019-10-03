package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.BanishAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruluk.makeID;

public class AttackBanishAttack extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(AttackBanishAttack.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE_UP = 3;
    private static final int BLOCK_UP = 0;
    private static final int BANISH_UP = 0;
    private static final int DAMAGE = 12;
    private static final int BLOCK = 0;
    private static final int BANISH = 2;

    public AttackBanishAttack() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, BANISH_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = BANISH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new BanishAction(
                magicNumber,
                c -> c.type == CardType.ATTACK,
                c -> damage(m)
        ));
    }
}
