package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruluk.makeID;

public class DiscardAndGainEnergy extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(DiscardAndGainEnergy.class));
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int ENERGY = 1;
    private static final int NRG_UP = 1;

    public DiscardAndGainEnergy() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, NRG_UP, COST);
        baseMagicNumber = magicNumber = ENERGY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DiscardAction(p, p, 1, false));
        act(new GainEnergyAction(magicNumber));
    }
}
