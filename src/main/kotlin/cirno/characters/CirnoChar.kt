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
import cirno.cards.SpellAttackedFreeze
import cirno.relics.StarterRelic
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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CharacterStrings
import com.badlogic.gdx.utils.FloatArray

class CirnoChar(name: String, setClass: AbstractPlayer.PlayerClass) : CustomPlayer(name, setClass, orbTextures, "cirno/images/char/defaultCharacter/orb/vfx.png", null,
        SpineAnimation("cirno/images/char/defaultCharacter/cirno1/cirno1.atlas", "cirno/images/char/defaultCharacter/cirno1/cirno1.json", 1.0f)) {
    var orbPositionsX = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    var orbPositionsY = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
    var xStartOffset = Settings.WIDTH.toFloat() * 0.30f
    private val xSpaceBetweenSlots = 90 * Settings.scale
    private val xSpaceBottomAlternatingOffset = 0 * Settings.scale
    private val yStartOffset = AbstractDungeon.floorY + 370 * Settings.scale
    private val ySpaceBottomAlternatingOffset = -100 * Settings.scale
    private val ySpaceAlternatingOffset = -50 * Settings.scale

    init {
        val shoulderOne = "cirno/images/char/defaultCharacter/shoulder.png"
        val shoulderTwo = "cirno/images/char/defaultCharacter/shoulder2.png"
        val corpse = "cirno/images/char/defaultCharacter/corpse.png"

        initializeClass(null,
                shoulderOne,
                shoulderTwo,
                corpse,
                loadout, 20.0f, -10.0f, 220.0f, 250.0f,
                EnergyManager(ENERGY_PER_TURN))

        dialogX = drawX + 0.0f * Settings.scale
        dialogY = drawY + 220.0f * Settings.scale
        initializeSlotPositions();
    }

    override fun getLoadout(): CharSelectInfo {
        return CharSelectInfo(NAME!![0], TEXT!![0],
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
        for (i in 0..2) {
            retVal.add(makeID(Defend::class.java))
        }
        retVal.add(makeID(DamageAndCold::class.java))
        retVal.add(makeID(SpellAttackedFreeze::class.java))
        return retVal
    }

    override fun getStartingRelics(): ArrayList<String> {
        val retVal = ArrayList<String>()
        retVal.add(StarterRelic.id)
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
        return FontHelper.energyNumFontBlue
    }

    override fun getLocalizedCharacterName(): String {
        return NAME!![0]
    }

    override fun getStartCardForEvent(): AbstractCard {
        return SpellAttackedFreeze()
    }

    override fun getTitle(playerClass: AbstractPlayer.PlayerClass): String {
        return NAME!![1]
    }

    override fun newInstance(): AbstractPlayer {
        return CirnoChar(NAME!![0], chosenClass)
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
        return TEXT!![1]
    }

    override fun getVampireText(): String {
        return TEXT!![2]
    }

    public fun loadSprite(costume: Int) {
        loadAnimation("cirno/images/char/defaultCharacter/cirno$costume/cirno$costume.atlas", "cirno/images/char/defaultCharacter/cirno$costume/cirno$costume.json", 1.0f)
    }

    override fun onVictory() {
        initializeSlotPositions()
    }

    fun initializeSlotPositions() {
        orbPositionsX[0] = xStartOffset + xSpaceBetweenSlots * 1
        orbPositionsX[1] = xStartOffset + xSpaceBetweenSlots * 1 + xSpaceBottomAlternatingOffset
        orbPositionsX[2] = xStartOffset + xSpaceBetweenSlots * 2
        orbPositionsX[3] = xStartOffset + xSpaceBetweenSlots * 2 + xSpaceBottomAlternatingOffset
        orbPositionsX[4] = xStartOffset + xSpaceBetweenSlots * 3
        orbPositionsX[5] = xStartOffset + xSpaceBetweenSlots * 3 + xSpaceBottomAlternatingOffset
        orbPositionsX[6] = xStartOffset + xSpaceBetweenSlots * 4
        orbPositionsX[7] = xStartOffset + xSpaceBetweenSlots * 4 + xSpaceBottomAlternatingOffset
        orbPositionsX[8] = xStartOffset + xSpaceBetweenSlots * 5
        orbPositionsX[9] = xStartOffset + xSpaceBetweenSlots * 5 + xSpaceBottomAlternatingOffset

        orbPositionsY[0] = yStartOffset
        orbPositionsY[1] = yStartOffset + ySpaceBottomAlternatingOffset
        orbPositionsY[2] = yStartOffset + ySpaceAlternatingOffset
        orbPositionsY[3] = yStartOffset + ySpaceBottomAlternatingOffset + ySpaceAlternatingOffset
        orbPositionsY[4] = yStartOffset
        orbPositionsY[5] = yStartOffset + ySpaceBottomAlternatingOffset
        orbPositionsY[6] = yStartOffset + ySpaceAlternatingOffset
        orbPositionsY[7] = yStartOffset + ySpaceBottomAlternatingOffset + ySpaceAlternatingOffset
        orbPositionsY[8] = yStartOffset
        orbPositionsY[9] = yStartOffset + ySpaceBottomAlternatingOffset
    }

    companion object statics {
        val ENERGY_PER_TURN = 3
        val STARTING_HP = 75
        val MAX_HP = 75
        val STARTING_GOLD = 99
        val CARD_DRAW = 5
        val ORB_SLOTS = 0
        val orbTextures = arrayOf("cirno/images/char/defaultCharacter/orb/layer1.png", "cirno/images/char/defaultCharacter/orb/layer2.png", "cirno/images/char/defaultCharacter/orb/layer3.png", "cirno/images/char/defaultCharacter/orb/layer4.png", "cirno/images/char/defaultCharacter/orb/layer5.png", "cirno/images/char/defaultCharacter/orb/layer6.png", "cirno/images/char/defaultCharacter/orb/layer1d.png", "cirno/images/char/defaultCharacter/orb/layer2d.png", "cirno/images/char/defaultCharacter/orb/layer3d.png", "cirno/images/char/defaultCharacter/orb/layer4d.png", "cirno/images/char/defaultCharacter/orb/layer5d.png")
        val characterStrings = CardCrawlGame.languagePack.getCharacterString(makeID(CirnoChar::class.java))
        val NAME = characterStrings!!.NAMES
        val TEXT = characterStrings!!.TEXT

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
