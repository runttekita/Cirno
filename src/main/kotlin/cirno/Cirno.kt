package cirno

import basemod.BaseMod
import cirno.daten.*
import basemod.BaseMod.*
import basemod.interfaces.*
import cirno.abstracts.CirnoCard
import cirno.characters.CirnoChar
import cirno.variable.DisplayVariable
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

@SpireInitializer
class Cirno() :
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,
		PostInitializeSubscriber,
        RenderSubscriber,
        PostRenderSubscriber,
        PostDungeonInitializeSubscriber,
        AddAudioSubscriber {

    companion object Statics {
        var textureLoader = AssetLoader()
        private var modID: String? = null
        public val CIRNO_ICE: Color = Color.valueOf("#000DAB")
        var cirnoCostumes: CirnoCostumes? = null

        fun makeID(c: Class<*>): String {
            return makeID(c.simpleName)
        }

        fun makeID(idText: String): String {
            return getModID() + ":" + idText
        }

        fun getModID(): String? {
            return modID
        }

        fun setModID(ID: String) {
            modID = ID
        }

        fun makeCardPath(resourcePath: String): String {
            return "cirno" + "/images/cards/" + resourcePath
        }

        fun makeRelicPath(resourcePath: String): String {
            return getModID() + "/images/relics/" + resourcePath
        }

        fun makeRelicOutlinePath(resourcePath: String): String {
            return getModID() + "/images/relics/outline/" + resourcePath
        }

        fun makeOrbPath(resourcePath: String): String {
            return getModID() + "/orbs/" + resourcePath
        }

        fun makePowerPath(resourcePath: String): String {
            return getModID() + "/images/powers/" + resourcePath
        }

        fun makeEventPath(resourcePath: String): String {
            return getModID() + "/images/events/" + resourcePath
        }

        @JvmStatic
        fun initialize() {
            Cirno()
            Initialize()
        }

    }

    init {
        subscribe(this)
        setModID("Cirno")
    }

    override fun receiveEditCharacters() {
        ReceiveEditCharacters()
    }

    override fun receiveEditCards() {
        BaseMod.addDynamicVariable(DisplayVariable())
        ReceiveEditCards()
    }

    override fun receiveEditStrings() {
        ReceiveEditStrings()
    }

    override fun receivePostInitialize() {
        ReceivePostInitialize()
        Events()
    }

    override fun receiveEditRelics() {
        ReceiveEditRelics()
    }

    override fun receiveEditKeywords() {
        ReceiveEditKeywords()
    }

    override fun receiveRender(sb: SpriteBatch) {
    }

    override fun receivePostRender(sb: SpriteBatch) {
        cirnoCostumes?.render(sb)
        cirnoCostumes?.update()
    }

    override fun receivePostDungeonInitialize() {
        if (AbstractDungeon.player.chosenClass == CirnoChar.Enums.enums.Cirno) {
            (AbstractDungeon.player as CirnoChar).loadSprite(cirnoCostumes!!.currentCostume)
        }
    }

    override fun receiveAddAudio() {
        BaseMod.addAudio("ICE_CRACK", "cirno/audio/cirnosound.ogg")
    }

}
