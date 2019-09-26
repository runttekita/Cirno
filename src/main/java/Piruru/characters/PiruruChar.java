package Piruru.characters;

import Piruru.cards.Defend;
import Piruru.cards.Strike;
import Piruru.relics.StarterRelic;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import org.apache.logging.log4j.LogManager;
import java.util.ArrayList;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import Piruru.Piruru;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;

import static Piruru.Piruru.makeID;

public class PiruruChar extends CustomPlayer {

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass PIRURU;
        @SpireEnum(name = "ICE")
        public static AbstractCard.CardColor PIRURU_ICE;
        @SpireEnum(name = "ICE")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 3;

    private static final String ID = makeID("Piruru");
    private static final CharacterStrings characterStrings =
     CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = {"Pirulu", "Pirulu", "Pirulu", "Pirulu", "Pirulu"};
    private static final String[] TEXT = {"Pirulu", "Pirulu", "Pirulu", "Pirulu", "Pirulu"};

    public static final String[] orbTextures = {
            "Piruru/images/char/defaultCharacter/orb/layer1.png",
            "Piruru/images/char/defaultCharacter/orb/layer2.png",
            "Piruru/images/char/defaultCharacter/orb/layer3.png",
            "Piruru/images/char/defaultCharacter/orb/layer4.png",
            "Piruru/images/char/defaultCharacter/orb/layer5.png",
            "Piruru/images/char/defaultCharacter/orb/layer6.png",
            "Piruru/images/char/defaultCharacter/orb/layer1d.png",
            "Piruru/images/char/defaultCharacter/orb/layer2d.png",
            "Piruru/images/char/defaultCharacter/orb/layer3d.png",
            "Piruru/images/char/defaultCharacter/orb/layer4d.png",
            "Piruru/images/char/defaultCharacter/orb/layer5d.png",};

    public PiruruChar(String name, PlayerClass setClass) {
        super(name, setClass, orbTextures,
                "Piruru/images/char/defaultCharacter/orb/vfx.png", null,
                        "Piruru/images/char/defaultCharacter/piru");


        initializeClass(null,
                Piruru.PIRURU_SHOULDER_1,
                Piruru.PIRURU_SHOULDER_2,
                Piruru.PIRURU_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F,
                new EnergyManager(ENERGY_PER_TURN));


        loadAnimation(
                Piruru.PIRURU_SKELETON_ATLAS,
                Piruru.PIRURU_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 220.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("Piruru", "Piruru",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW,
                this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            retVal.add(makeID(Strike.class.getSimpleName()));
        }
        for (int i = 0; i < 5; i++) {
            retVal.add(makeID(Defend.class.getSimpleName()));
        }
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(PrismaticShard.ID);
        retVal.add(StarterRelic.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW,
        ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 0;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return PiruruChar.Enums.PIRURU_ICE;
    }

    @Override
    public Color getCardTrailColor() {
        return Piruru.PIRURU_ICE;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return "Pirulu";
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_Blue();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return "Pirulu";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new PiruruChar(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return Piruru.PIRURU_ICE;
    }

    @Override
    public Color getSlashAttackColor() {
        return Piruru.PIRURU_ICE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    @Override
    public String getSpireHeartText() {
        return "Pirulu";
    }

    @Override
    public String getVampireText() {
        return "Pirulu";
    }
}
