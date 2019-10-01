package Piruru;

import Piruru.characters.PiruruChar;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import Piruru.cards.*;
import Piruru.relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class Piruru implements
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber {
    public static final Color PIRURU_ICE = Color.valueOf("#000DAB");
    public static final String PIRURU_SHOULDER_1 = "Piruru/images/char/defaultCharacter/shoulder.png";
    public static final String PIRURU_SHOULDER_2 = "Piruru/images/char/defaultCharacter/shoulder2.png";
    public static final String PIRURU_CORPSE = "Piruru/images/char/defaultCharacter/corpse.png";
    public static final String BADGE_IMAGE = "Piruru/images/Badge.png";
    public static final String PIRURU_SKELETON_ATLAS = "Piruru/images/char/defaultCharacter/skeleton.atlas";
    public static final String PIRURU_SKELETON_JSON = "Piruru/images/char/defaultCharacter/skeleton.json";
    private static final String ATTACK_PIRURU_ICE = "Piruru/images/512prod/bg_attack_default_gray.png";
    private static final String SKILL_PIRURU_ICE = "Piruru/images/512prod/bg_skill_default_gray.png";
    private static final String POWER_PIRURU_ICE = "Piruru/images/512prod/bg_power_default_gray.png";
    private static final String ENERGY_ORB_PIRURU_ICE = "Piruru/images/512prod/card_default_gray_orb.png";
    private static final String CARD_ENERGY_ORB = "Piruru/images/512prod/card_small_orb.png";
    private static final String ATTACK_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/bg_attack_default_gray.png";
    private static final String SKILL_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/bg_skill_default_gray.png";
    private static final String POWER_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/bg_power_default_gray.png";
    private static final String ENERGY_ORB_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/card_default_gray_orb.png";
    private static final String PIRURU_BUTTON = "Piruru/images/charSelect/DefaultCharacterButton.png";
    private static final String PIRURU_PORTRAIT = "Piruru/images/charSelect/DefaultCharacterPortraitBG.png";
    public static AssetLoader textureLoader = new AssetLoader();
    private static String modID;

    public Piruru() {
        BaseMod.subscribe(this);
        setModID("Piruru");
        BaseMod.addColor(PiruruChar.Enums.PIRURU_ICE, PIRURU_ICE, PIRURU_ICE, PIRURU_ICE,
                PIRURU_ICE, PIRURU_ICE, PIRURU_ICE, PIRURU_ICE,
                ATTACK_PIRURU_ICE, SKILL_PIRURU_ICE, POWER_PIRURU_ICE,
                ENERGY_ORB_PIRURU_ICE, ATTACK_PIRURU_ICE_PORTRAIT,
                SKILL_PIRURU_ICE_PORTRAIT, POWER_PIRURU_ICE_PORTRAIT,
                ENERGY_ORB_PIRURU_ICE_PORTRAIT, CARD_ENERGY_ORB);
    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "/images/relics/outline/" + resourcePath;
    }

    public static String makeOrbPath(String resourcePath) {
        return getModID() + "/orbs/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "/images/powers/" + resourcePath;
    }

    public static String makeEventPath(String resourcePath) {
        return getModID() + "/images/events/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void setModID(String ID) {
        modID = ID;
    }

    public static void initialize() {
        Piruru piruru = new Piruru();
    }

    public static String makeID(Class c) {
        return makeID(c.getSimpleName());
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {

        BaseMod.addCharacter(new PiruruChar("Piruru", PiruruChar.Enums.PIRURU),
                PIRURU_BUTTON, PIRURU_PORTRAIT, PiruruChar.Enums.PIRURU);
    }

    /**
     * But Reina, I can hear you ask, why aren't you using autoAddCards from Kio? Well the answer is simple
     * I'm too lazy to copy paste all that stuff and make like 90 classes and make.sh does this for me.
     */
    @Override
    public void receiveEditCards() {
		//autoAddCards
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, "Piruru/localization/eng/prodStrings/pirurelic.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "Piruru/localization/eng/prodStrings/card.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "Piruru/localization/eng/prodStrings/ui.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "Piruru/localization/eng/prodStrings/powers.json");
        BaseMod.loadCustomStringsFile(StanceStrings.class, "Piruru/localization/eng/prodStrings/stances.json");
    }


    /**
     * But Reina, I can hear you ask, why aren't you using autoAddRelicsg from Kio? Well the answer is simple
     * I'm too lazy to copy paste all that stuff and make like 90 classes and make.sh does this for me.
     */
    @Override
    public void receiveEditRelics() {
		//autoAddRelics
        int count = 0;
        int commonCount = 0;
        int uncommonCount = 0;
        int rareCount = 0;
        for (AbstractCard c : CardLibrary.getCardList(PiruruChar.Enums.LIBRARY_COLOR)) {
            UnlockTracker.unlockCard(c.cardID);
            if (c.rarity == AbstractCard.CardRarity.COMMON) {
                commonCount++;
                count++;
            }
            if (c.rarity == AbstractCard.CardRarity.UNCOMMON) {
                uncommonCount++;
                count++;
            }
            if (c.rarity == AbstractCard.CardRarity.RARE) {
                rareCount++;
                count++;
            }
        }
        System.out.println("COMMON CARDS" + commonCount);
        System.out.println("UNCOMMON CARDS" + uncommonCount);
        System.out.println("RARE CARDS" + rareCount);
        System.out.println("TOTAL CARDS" + count);
    }

    /**
     * https://github.com/kiooeht/Bard/blob/master/src/main/java/com/evacipated/cardcrawl/mod/bard/BardMod.java#L345
     */
    private void loadLocKeywords(Settings.GameLanguage language) {

    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal("Piruru/localization/eng/prodStrings/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }
}