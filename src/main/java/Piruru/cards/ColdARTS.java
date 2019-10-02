package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import Piruru.powers.Cold;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class ColdARTS extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(ColdARTS.class.getSimpleName()));
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int COLD_UP = 0;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int COLD = 2;
    private static final int COST_UP = 1;

    public ColdARTS() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, COLD_UP, COST_UP);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = COLD;
        exhaust = true;
        tags.add(Enums.ARTS);
        AlwaysRetainField.alwaysRetain.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ApplyPowerAction(m, p, new Cold(magicNumber, m), magicNumber));
    }
}
