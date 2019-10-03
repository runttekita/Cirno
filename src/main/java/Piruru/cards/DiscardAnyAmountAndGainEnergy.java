package Piruru.cards;

import Piruru.abstracts.PiruruCard;
import Piruru.actions.DiscardAnyAmountAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



public class DiscardAnyAmountAndGainEnergy extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(DiscardAnyAmountAndGainEnergy.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST_UP = 0;
    private static final int ENERGY_GAIN = 1;

    public DiscardAnyAmountAndGainEnergy() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST_UP);
        baseMagicNumber = magicNumber = ENERGY_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DiscardAnyAmountAction(list -> list.forEach(
                c -> act(new GainEnergyAction(magicNumber))
        )));
    }
}
