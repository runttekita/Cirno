package Piruru.characters

import Piruru.Piruluk
import Piruru.Piruluk.Statics.makeID
import Piruru.cards.DamageAndCold
import Piruru.cards.Defend
import Piruru.cards.ScoutAttacks
import Piruru.cards.Strike
import Piruru.characters.PiruruChar.Enums.enums.PIRURU_ICE
import Piruru.relics.StarterRelic
import basemod.abstracts.CustomPlayer
import basemod.animations.AbstractAnimation
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.blue.Strike_Blue
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.EnergyManager
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.helpers.CardLibrary
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.helpers.ScreenShake
import com.megacrit.cardcrawl.screens.CharSelectInfo

import java.util.ArrayList

class PiruruChar(name: String, setClass: AbstractPlayer.PlayerClass) : CustomPlayer(name, setClass, orbTextures, "Piruru/images/char/defaultCharacter/orb/vfx.png", null, object : AbstractAnimation() {
    override fun type(): AbstractAnimation.Type {
        return AbstractAnimation.Type.SPRITE
    }
}) {

    init {
        val shoulderOne = "Piruru/images/char/defaultCharacter/shoulder.png"
        val shoulderTwo = "Piruru/images/char/defaultCharacter/shoulder2.png"
        val corpse = "Piruru/images/char/defaultCharacter/corpse.png"
        initializeClass("Piruru/images/char/defaultCharacter/piru.png",
                shoulderOne,
                shoulderTwo,
                corpse,
                loadout, 20.0f, -10.0f, 220.0f, 290.0f,
                EnergyManager(ENERGY_PER_TURN))

        dialogX = drawX + 0.0f * Settings.scale
        dialogY = drawY + 220.0f * Settings.scale
    }

    override fun getLoadout(): CharSelectInfo {
        return CharSelectInfo("Piruru", "Piruru",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW,
                this, startingRelics,
                startingDeck, false)
    }

    override fun getStartingDeck(): ArrayList<String> {
        val retVal = ArrayList<String>()
        for (i in 0..3) {
            retVal.add(makeID(Strike::class.java))
        }
        for (i in 0..3) {
            retVal.add(makeID(Defend::class.java))
        }
        retVal.add(makeID(ScoutAttacks::class.java))
        retVal.add(makeID(DamageAndCold::class.java))
        return retVal
    }

    override fun getStartingRelics(): ArrayList<String> {
        val retVal = ArrayList<String>()
        retVal.add(StarterRelic.ID)
        return retVal
    }

    override fun doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f)
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW,
                ScreenShake.ShakeDur.SHORT,
                false)
    }

    override fun getCustomModeCharacterButtonSoundKey(): String {
        return "ATTACK_DAGGER_1"
    }

    override fun getAscensionMaxHPLoss(): Int {
        return 0
    }

    override fun getCardColor(): AbstractCard.CardColor? {
        return PIRURU_ICE
    }

    override fun getCardTrailColor(): Color {
        return Piruluk.PIRURU_ICE
    }

    override fun getEnergyNumFont(): BitmapFont {
        return FontHelper.energyNumFontRed
    }

    override fun getLocalizedCharacterName(): String {
        return "Piruluk"
    }

    override fun getStartCardForEvent(): AbstractCard {
        return Strike_Blue()
    }

    override fun getTitle(playerClass: AbstractPlayer.PlayerClass): String {
        return "Piruluk"
    }

    override fun newInstance(): AbstractPlayer {
        return PiruruChar(name, chosenClass)
    }

    override fun getCardRenderColor(): Color {
        return Piruluk.PIRURU_ICE
    }

    override fun getSlashAttackColor(): Color {
        return Piruluk.PIRURU_ICE
    }

    override fun getSpireHeartSlashEffect(): Array<AbstractGameAction.AttackEffect> {
        return arrayOf(AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY)
    }

    override fun getSpireHeartText(): String {
        return "Piruluk"
    }

    override fun getVampireText(): String {
        return "Piruluk"
    }

    companion object statics {
        val ENERGY_PER_TURN = 3
        val STARTING_HP = 75
        val MAX_HP = 75
        val STARTING_GOLD = 99
        val CARD_DRAW = 5
        val ORB_SLOTS = 0
        val orbTextures = arrayOf("Piruru/images/char/defaultCharacter/orb/layer1.png", "Piruru/images/char/defaultCharacter/orb/layer2.png", "Piruru/images/char/defaultCharacter/orb/layer3.png", "Piruru/images/char/defaultCharacter/orb/layer4.png", "Piruru/images/char/defaultCharacter/orb/layer5.png", "Piruru/images/char/defaultCharacter/orb/layer6.png", "Piruru/images/char/defaultCharacter/orb/layer1d.png", "Piruru/images/char/defaultCharacter/orb/layer2d.png", "Piruru/images/char/defaultCharacter/orb/layer3d.png", "Piruru/images/char/defaultCharacter/orb/layer4d.png", "Piruru/images/char/defaultCharacter/orb/layer5d.png")
        private val ID = makeID("Piruru")
        private val characterStrings = CardCrawlGame.languagePack.getCharacterString(ID)
        private val NAMES = arrayOf("Piruluk", "Piruluk", "Piruluk", "Piruluk", "Piruluk")
        private val TEXT = arrayOf("Piruluk", "Piruluk", "Piruluk", "Piruluk", "Piruluk")
    }

    public class Enums {
        object enums{
            @SpireEnum
            @JvmStatic
            var PIRURU: AbstractPlayer.PlayerClass? = null
            @SpireEnum(name = "ICE")
            @JvmStatic
            var PIRURU_ICE: AbstractCard.CardColor? = null
            @SpireEnum(name = "ICE")
            @JvmStatic
            var LIBRARY_COLOR: CardLibrary.LibraryType? = null
        }

    }
}
