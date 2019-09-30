package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import Piruru.actions.NotShittyChangeStanceAction;
import Piruru.powers.ApexForm;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class EnterApexForm extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(EnterApexForm.class.getSimpleName()));
    private static final int COST = 3;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int MAGIC_UP = 2;
    private static final int COST_UP = 2;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int MAGIC = 1;

    public EnterApexForm() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST_UP);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new NotShittyChangeStanceAction(new ApexForm()));
    }
}
