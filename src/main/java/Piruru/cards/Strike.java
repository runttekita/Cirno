package Piruru.cards;

import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class Strike extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(Strike.class));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int DAMAGE = 6;
    private static final int DAMAGE_UP = 3;

    public Strike() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, 0, COST);
        baseDamage = damage = DAMAGE;
        tags.add(BaseModCardTags.BASIC_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damage(m);
    }
}
