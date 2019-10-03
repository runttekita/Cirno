package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.ExhumeButWithOneLessParameter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruluk.makeID;

public class AttackExhume extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(AttackExhume.class.getSimpleName()));
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE_UP = 2;
    private static final int BLOCK_UP = 0;
    private static final int MAGIC_UP = 0;
    private static final int DAMAGE = 10;
    private static final int BLOCK = 0;
    private static final int MAGIC = 0;

    public AttackExhume() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
        act(new ExhumeButWithOneLessParameter());
    }
}
