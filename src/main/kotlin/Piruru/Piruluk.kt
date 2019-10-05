package Piruru

import Piruru.daten.*
import basemod.BaseMod.*
import basemod.interfaces.*
import com.badlogic.gdx.graphics.Color
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer

@SpireInitializer
class Piruluk() :
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber,
		PostInitializeSubscriber {

    companion object Statics {
        var textureLoader = AssetLoader()
        private var modID: String? = null
        public val PIRURU_ICE: Color = Color.valueOf("#000DAB")

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
            return getModID() + "/images/cards/" + resourcePath
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
            Piruluk()
            Initialize()
        }
    }

    init {
        subscribe(this)
        setModID("Piruru")
    }

    override fun receiveEditCharacters() {
        ReceiveEditCharacters()
    }

    override fun receiveEditCards() {
        ReceiveEditCards()
    }

    override fun receiveEditStrings() {
        ReceiveEditStrings()
    }

    override fun receivePostInitialize() {
        ReceivePostInitialize()
    }

    override fun receiveEditRelics() {
        ReceiveEditRelics()
    }

    override fun receiveEditKeywords() {
        ReceiveEditKeywords()
    }

}