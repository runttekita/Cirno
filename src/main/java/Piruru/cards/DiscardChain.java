package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.powers.Chain;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruluk.makeID;

public class DiscardChain extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(DiscardChain.class.getSimpleName()));
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int CHAIN_UP = 1;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int CHAIN = 1;

    public DiscardChain() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, CHAIN_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = CHAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DiscardAction(p, p, 1, false));
        act(new ApplyPowerAction(p, p, new Chain(p, magicNumber)));
    }
}
