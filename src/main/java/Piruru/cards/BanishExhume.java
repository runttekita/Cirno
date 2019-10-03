package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.BanishAction;
import Piruru.actions.ExhumeButWithOneLessParameter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



public class BanishExhume extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(BanishExhume.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int MAGIC_UP = 0;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int BANISH = 4;
    private static final int COST_UP = 1;


    public BanishExhume() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST_UP);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = BANISH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new BanishAction(magicNumber, ifSuccess -> act(new ExhumeButWithOneLessParameter())));
    }
}
