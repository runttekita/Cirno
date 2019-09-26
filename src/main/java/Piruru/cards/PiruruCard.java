package Piruru.cards;

import Piruru.Piruru;
import Piruru.characters.PiruruChar;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public abstract class PiruruCard extends CustomCard {

    public PiruruCard(int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target) {
        super(null, null, (String)null, cost, rawDescription, type, PiruruChar.Enums.PIRURU_ICE, rarity, target);
        cardID = this.getClass().getSimpleName();
        CardStrings cardStrings =  CardCrawlGame.languagePack.getCardStrings(cardID);;
        name = cardStrings.NAME;
    }

    private static String getImg(String id) {
        String imgName = id.substring((id.indexOf(":") + 1)).trim();
        return Piruru.makeCardPath(imgName);
    }

    protected String makeName() {
        return Piruru.makeID(this.getClass().getSimpleName());
    }

}
