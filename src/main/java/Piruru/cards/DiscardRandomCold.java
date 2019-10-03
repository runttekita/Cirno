package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.DiscardAnyAmountAction;
import Piruru.powers.Cold;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



public class DiscardRandomCold extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(DiscardRandomCold.class.getSimpleName()));
    private static final int COST = 3;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int MAGIC_UP = 0;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int MAGIC = 0;
    private static final int COST_UP = 2;

    public DiscardRandomCold() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST_UP);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DiscardAnyAmountAction(list -> list.forEach(c -> {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster();
            act(new ApplyPowerAction(target, p, new Cold(m), 1));
        })));
    }
}
