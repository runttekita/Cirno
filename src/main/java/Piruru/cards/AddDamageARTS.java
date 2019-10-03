package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

import static Piruru.Piruru.makeID;

public class AddDamageARTS extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(AddDamageARTS.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int ARTS_UP = 1;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int ARTS = 2;

    public AddDamageARTS() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, ARTS_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = ARTS;
        cardsToPreview = new DamageARTS();
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        TooltipInfo tooltip = new TooltipInfo(BaseMod.getKeywordTitle(makeID("arts").toLowerCase()), BaseMod.getKeywordDescription(makeID("arts").toLowerCase()));
        ArrayList<TooltipInfo> list = new ArrayList<>();
        list.add(tooltip);
        return list;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new MakeTempCardInHandAction(new DamageARTS(), magicNumber));
    }
}
