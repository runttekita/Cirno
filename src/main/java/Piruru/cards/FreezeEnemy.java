package Piruru.cards;

import Piruru.actions.FreezeMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Piruru.Piruru.makeID;

public class FreezeEnemy extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(FreezeEnemy.class.getSimpleName()));
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST_UP = 1;

    public FreezeEnemy() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST_UP);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new FreezeMonsterAction(m, p));
    }
}
