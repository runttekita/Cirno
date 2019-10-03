package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import Piruru.actions.DiscardAnyAmountAction;
import Piruru.actions.MulliganAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class Mulligan extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(Mulligan.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int MAGIC_UP = 0;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int MAGIC = 0;
    private static final int COST_UP

    public Mulligan() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST_UP);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DiscardAnyAmountAction(list -> act(new MulliganAction(list.size()))));
    }
}
