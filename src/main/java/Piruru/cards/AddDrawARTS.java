package Piruru.cards;

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



public class AddDrawARTS extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(AddDrawARTS.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 0;
    private static final int ICE_UP = 1;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 0;
    private static final int ICE = 2;

    public AddDrawARTS() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, ICE_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = ICE;
        cardsToPreview = new DrawARTS();
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
        for (int i = 0; i < magicNumber; i++) {
            act(new MakeTempCardInHandAction(new DrawARTS()));
        }
    }
}
