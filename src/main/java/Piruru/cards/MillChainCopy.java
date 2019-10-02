package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.MillAction;
import Piruru.powers.Chain;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class MillChainCopy extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(MillChainCopy.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE_UP = 3;
    private static final int BLOCK_UP = 0;
    private static final int MILL_UP = 2;
    private static final int DAMAGE = 8;
    private static final int BLOCK = 0;
    private static final int MILL = 2;

    public MillChainCopy() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MILL_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MILL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
        act(new MillAction(magicNumber));
        act(new MakeTempCardInDrawPileAction(new MillChainCopy(), 1, true, true));
        act(new ApplyPowerAction(p, p, new Chain(p, 1), 1));
    }
}
