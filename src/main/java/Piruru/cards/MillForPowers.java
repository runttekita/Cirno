package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import Piruru.actions.MillAction;
import Piruru.actions.RecoverAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class MillForPowers extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(MillForPowers.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int MILL_UP = 3;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int MILL = 3;

    public MillForPowers() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MILL_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MILL;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new MillAction(magicNumber, c -> {
            if (c.type == CardType.POWER) {
                act(new RecoverAction(c));
            }
        }));
    }
}
