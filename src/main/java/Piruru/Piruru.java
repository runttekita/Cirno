package Piruru;

import Piruru.cards.*;
import Piruru.relics.StarterRelic;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;

import Piruru.characters.PiruruChar;

@SpireInitializer
public class Piruru implements
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber
    {

        private static String modID;
        public static final Color PIRURU_ICE = CardHelper.getColor(64.0f, 70.0f, 70.0f);

        private static final String ATTACK_PIRURU_ICE = "Piruru/images/512/bg_attack_default_gray.png";
        private static final String SKILL_PIRURU_ICE = "Piruru/images/512/bg_skill_default_gray.png";
        private static final String POWER_PIRURU_ICE = "Piruru/images/512/bg_power_default_gray.png";

        private static final String ENERGY_ORB_PIRURU_ICE = "Piruru/images/512/card_default_gray_orb.png";
        private static final String CARD_ENERGY_ORB = "Piruru/images/512/card_small_orb.png";

        private static final String ATTACK_PIRURU_ICE_PORTRAIT = "Piruru/images/1024/bg_attack_default_gray.png";
        private static final String SKILL_PIRURU_ICE_PORTRAIT = "Piruru/images/1024/bg_skill_default_gray.png";
        private static final String POWER_PIRURU_ICE_PORTRAIT = "Piruru/images/1024/bg_power_default_gray.png";
        private static final String ENERGY_ORB_PIRURU_ICE_PORTRAIT = "Piruru/images/1024/card_default_gray_orb.png";

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

        @Override
        public void receiveEditCharacters() {

            BaseMod.addCharacter(new PiruruChar("Piruru", PiruruChar.Enums.PIRURU),
                    PIRURU_BUTTON, PIRURU_PORTRAIT, PiruruChar.Enums.PIRURU);
        }

        @Override
        public void receiveEditCards() {
            BaseMod.addCard(new Strike());
            BaseMod.addCard(new Defend());
            BaseMod.addCard(new ScoutAttacks());
            BaseMod.addCard(new DiscardAndGainEnergy());
            BaseMod.addCard(new DamageAndCold());
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


        @Override
        public void receiveEditRelics() {
            BaseMod.addRelicToCustomPool(new StarterRelic(), PiruruChar.Enums.PIRURU_ICE);
        }
    }
