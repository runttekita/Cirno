package Piruru;

import Piruru.cards.*;
import Piruru.characters.PiruruChar;
import Piruru.relics.StarterRelic;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

@SpireInitializer
public class Piruru implements
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber {
    public static AssetLoader textureLoader = new AssetLoader();

    private static String modID;
    public static final Color PIRURU_ICE = CardHelper.getColor(64.0f, 70.0f, 70.0f);

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
    public static final String PIRURU_SHOULDER_1 = "Piruru/images/char/defaultCharacter/shoulder.png";
    public static final String PIRURU_SHOULDER_2 = "Piruru/images/char/defaultCharacter/shoulder2.png";
    public static final String PIRURU_CORPSE = "Piruru/images/char/defaultCharacter/corpse.png";

    public static final String BADGE_IMAGE = "Piruru/images/Badge.png";

    public static final String PIRURU_SKELETON_ATLAS = "Piruru/images/char/defaultCharacter/skeleton.atlas";
    public static final String PIRURU_SKELETON_JSON = "Piruru/images/char/defaultCharacter/skeleton.json";

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

    public static void setModID(String ID) {
        modID = ID;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        Piruru piruru = new Piruru();
    }

    public static String makeID(Class c) {
        return makeID(c.getSimpleName());
    }

    @Override
    public void receiveEditCharacters() {

        BaseMod.addCharacter(new PiruruChar("Piruru", PiruruChar.Enums.PIRURU),
                PIRURU_BUTTON, PIRURU_PORTRAIT, PiruruChar.Enums.PIRURU);
    }

    //***************
    // But Reina, I can hear you ask, why aren't you using autoAddCards from Kio? Well the answer is simple
    // I'm too lazy to copy paste all that stuff and make like 90 classes and Makefile does this for me.
    //***************
    @Override
    public void receiveEditCards() {
		//autoAddCards
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings.class, "Piruru/localization/eng/prodStrings/pirurelic.json");
        BaseMod.loadCustomStringsFile(CardStrings.class, "Piruru/localization/eng/prodStrings/card.json");
        BaseMod.loadCustomStringsFile(UIStrings.class, "Piruru/localization/eng/prodStrings/ui.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "Piruru/localization/eng/prodStrings/powers.json");
    }


    //***************
    // But Reina, I can hear you ask, why aren't you using autoAddRelics from Kio? Well the answer is simple
    // I'm too lazy to copy paste all that stuff and make like 90 classes and Makefile does this for me.
    //***************
    @Override
    public void receiveEditRelics() {
		//autoAddRelics
    }
}
