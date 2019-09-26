package Piruru.cards;

import Piruru.Piruru;
import Piruru.characters.PiruruChar;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

public abstract class PiruruCard extends CustomCard {
    public String id;

    public PiruruCard(String id, int cost, String rawDescription, CardType type, CardRarity rarity, CardTarget target) {
        super(id, null, getImg(id), cost, rawDescription, type, PiruruChar.Enums.PIRURU_ICE, rarity, target);
        this.id = id;
        CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        name = cardStrings.NAME;
    }

    private static String getImg(String id) {
        String imgName = id.substring((id.indexOf(":") + 1)).trim();
        return Piruru.makeCardPath(imgName);
    }

}
