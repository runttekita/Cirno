package Piruru.cards;

import Piruru.Piruru;
import Piruru.characters.PiruruChar;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

public abstract class PiruruCard extends CustomCard {
    protected CardStrings cardStrings;


    public PiruruCard(int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(null, null, (String)null, cost, null, type, PiruruChar.Enums.PIRURU_ICE, rarity, target);
        cardID = this.getClass().getSimpleName();
        cardStrings =  CardCrawlGame.languagePack.getCardStrings(cardID);;
        name = cardStrings.NAME;
        rawDescription = cardStrings.DESCRIPTION;
    }

    private static String getImg(String id) {
        String imgName = id.substring((id.indexOf(":") + 1)).trim();
        return Piruru.makeCardPath(imgName);
    }

    protected String makeName() {
        return Piruru.makeID(this.getClass().getSimpleName());
    }

    protected void act(AbstractGameAction a) {
        AbstractDungeon.actionManager.addToBottom(a);
    }

    protected DamageInfo dmgInfo() {
        return new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL);
    }
}
