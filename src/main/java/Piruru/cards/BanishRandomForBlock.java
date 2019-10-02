package Piruru.cards;

import Piruru.Piruru;
import Piruru.abstracts.PiruruCard;
import Piruru.actions.BanishAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static Piruru.Piruru.makeID;

public class BanishRandomForBlock extends PiruruCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(
            makeID(BanishRandomForBlock.class.getSimpleName()));
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int DAMAGE_UP = 0;
    private static final int BLOCK_UP = 2;
    private static final int BANISH_UP = 2;
    private static final int DAMAGE = 0;
    private static final int BLOCK = 5;
    private static final int BANISH = 2;

    public BanishRandomForBlock() {
        super(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, BANISH_UP, COST);
        baseDamage = damage = DAMAGE;
        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = BANISH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> list = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            list.add(AbstractDungeon.player.discardPile.getRandomCard(true));
        }
        act(new BanishAction(list, banishedCards -> banishedCards.forEach(c -> block())));
    }
}
