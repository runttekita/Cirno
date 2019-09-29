package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import Piruru.actions.DiscardToGainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class DiscardAnyAmountAndGainEnergy extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(DiscardAnyAmountAndGainEnergy.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST_UP = 0;

    public DiscardAnyAmountAndGainEnergy() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST_UP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new DiscardToGainEnergyAction());
    }
}
