package cirno.characters

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.Cirno.Statics.textureLoader
import cirno.cards.DamageAndCold
import cirno.cards.Defend
import cirno.cards.Strike
import basemod.abstracts.CustomPlayer
import basemod.animations.AbstractAnimation
import basemod.animations.SpineAnimation
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
import com.megacrit.cardcrawl.relics.IceCream
import com.megacrit.cardcrawl.screens.CharSelectInfo

class CirnoChar(name: String, setClass: AbstractPlayer.PlayerClass) : CustomPlayer(name, setClass, orbTextures, "cirno/images/char/defaultCharacter/orb/vfx.png", null,
        SpineAnimation("cirno/images/char/defaultCharacter/cimo1/Cimo1.atlas", "cirno/images/char/defaultCharacter/cimo1/Cimo1.json", 1.0f)) {

    init {
        val shoulderOne = "cirno/images/char/defaultCharacter/shoulder.png"
        val shoulderTwo = "cirno/images/char/defaultCharacter/shoulder2.png"
        val corpse = "cirno/images/char/defaultCharacter/corpse.png"
        initializeClass(null,
                shoulderOne,
                shoulderTwo,
                corpse,
                loadout, 20.0f, -10.0f, 220.0f, 290.0f,
                EnergyManager(ENERGY_PER_TURN))

        dialogX = drawX + 0.0f * Settings.scale
        dialogY = drawY + 220.0f * Settings.scale
    }

    override fun getLoadout(): CharSelectInfo {
        return CharSelectInfo("Cirno", "Cirno",
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW,
                this, startingRelics,
                startingDeck, false)
    }

    public fun changeCostume(costume: Int) {

    }

    override fun getStartingDeck(): ArrayList<String> {
        val retVal = ArrayList<String>()
        for (i in 0..3) {
            retVal.add(makeID(Strike::class.java))
        }
        for (i in 0..3) {
            retVal.add(makeID(Defend::class.java))
        }
        retVal.add(makeID(DamageAndCold::class.java))
        return retVal
    }

    override fun getStartingRelics(): ArrayList<String> {
        val retVal = ArrayList<String>()
        retVal.add(IceCream.ID)
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
        return Enums.enums.Cirno_Ice
    }

    override fun getCardTrailColor(): Color {
        return Cirno.CIRNO_ICE
    }

    override fun getEnergyNumFont(): BitmapFont {
        return FontHelper.energyNumFontRed
    }

    override fun getLocalizedCharacterName(): String {
        return "Cirno"
    }

    override fun getStartCardForEvent(): AbstractCard {
        return Strike_Blue()
    }

    override fun getTitle(playerClass: AbstractPlayer.PlayerClass): String {
        return "Cirno"
    }

    override fun newInstance(): AbstractPlayer {
        return CirnoChar(name, chosenClass)
    }

    override fun getCardRenderColor(): Color {
        return Cirno.CIRNO_ICE
    }

    override fun getSlashAttackColor(): Color {
        return Cirno.CIRNO_ICE
    }

    override fun getSpireHeartSlashEffect(): Array<AbstractGameAction.AttackEffect> {
        return arrayOf(AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.BLUNT_HEAVY)
    }

    override fun getSpireHeartText(): String {
        return "Cirno"
    }

    override fun getVampireText(): String {
        return "Cirno"
    }

    public fun loadSprite(costume: Int) {
        loadAnimation("cirno/images/char/defaultCharacter/cimo$costume/Cimo$costume.atlas", "cirno/images/char/defaultCharacter/cimo$costume/Cimo$costume.json", 1.0f)
    }

    companion object statics {
        val ENERGY_PER_TURN = 3
        val STARTING_HP = 75
        val MAX_HP = 75
        val STARTING_GOLD = 99
        val CARD_DRAW = 5
        val ORB_SLOTS = 0
        val orbTextures = arrayOf("cirno/images/char/defaultCharacter/orb/layer1.png", "cirno/images/char/defaultCharacter/orb/layer2.png", "cirno/images/char/defaultCharacter/orb/layer3.png", "cirno/images/char/defaultCharacter/orb/layer4.png", "cirno/images/char/defaultCharacter/orb/layer5.png", "cirno/images/char/defaultCharacter/orb/layer6.png", "cirno/images/char/defaultCharacter/orb/layer1d.png", "cirno/images/char/defaultCharacter/orb/layer2d.png", "cirno/images/char/defaultCharacter/orb/layer3d.png", "cirno/images/char/defaultCharacter/orb/layer4d.png", "cirno/images/char/defaultCharacter/orb/layer5d.png")
        private val ID = makeID("Cirno")
        private val characterStrings = CardCrawlGame.languagePack.getCharacterString(ID)
        private val NAMES = arrayOf("Cirno", "Cirno", "Cirno", "Cirno", "Cirno")
        private val TEXT = arrayOf("Cirno", "Cirno", "Cirno", "Cirno", "Cirno")

    }

    public class Enums {
        object enums{
            @SpireEnum
            @JvmStatic
            var Cirno: AbstractPlayer.PlayerClass? = null
            @SpireEnum(name = "ICE")
            @JvmStatic
            var Cirno_Ice: AbstractCard.CardColor? = null
            @SpireEnum(name = "ICE")
            @JvmStatic
            var LIBRARY_COLOR: CardLibrary.LibraryType? = null
        }

    }
}
