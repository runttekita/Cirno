package Piruru

import Piruru.characters.PiruruChar
import basemod.BaseMod
import basemod.interfaces.*
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.evacipated.cardcrawl.mod.stslib.Keyword
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum
import com.google.gson.Gson
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.helpers.CardLibrary
import com.megacrit.cardcrawl.localization.*
import com.megacrit.cardcrawl.unlock.UnlockTracker
import java.nio.charset.StandardCharsets
import java.util.*

class Piruluk() :
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber,
        EditKeywordsSubscriber {

    companion object Statics {
        val PIRURU_ICE: Color = Color.valueOf("#000DAB")
        val PIRURU_SHOULDER_1 = "Piruru/images/char/defaultCharacter/shoulder.png"
        val PIRURU_SHOULDER_2 = "Piruru/images/char/defaultCharacter/shoulder2.png"
        val PIRURU_CORPSE = "Piruru/images/char/defaultCharacter/corpse.png"
        private val ATTACK_PIRURU_ICE = "Piruru/images/512prod/bg_attack_default_gray.png"
        private val SKILL_PIRURU_ICE = "Piruru/images/512prod/bg_skill_default_gray.png"
        private val POWER_PIRURU_ICE = "Piruru/images/512prod/bg_power_default_gray.png"
        private val ENERGY_ORB_PIRURU_ICE = "Piruru/images/512prod/card_default_gray_orb.png"
        private val CARD_ENERGY_ORB = "Piruru/images/512prod/card_small_orb.png"
        private val ATTACK_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/bg_attack_default_gray.png"
        private val SKILL_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/bg_skill_default_gray.png"
        private val POWER_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/bg_power_default_gray.png"
        private val ENERGY_ORB_PIRURU_ICE_PORTRAIT = "Piruru/images/1024prod/card_default_gray_orb.png"
        private val PIRURU_BUTTON = "Piruru/images/charSelect/DefaultCharacterButton.png"
        private val PIRURU_PORTRAIT = "Piruru/images/charSelect/DefaultCharacterPortraitBG.png"
        var textureLoader = AssetLoader()
        private var modID: String? = null

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
            BaseMod.addColor(PiruruChar.PIRURU_ICE, PIRURU_ICE, PIRURU_ICE, PIRURU_ICE,
                    PIRURU_ICE, PIRURU_ICE, PIRURU_ICE, PIRURU_ICE,
                    ATTACK_PIRURU_ICE, SKILL_PIRURU_ICE, POWER_PIRURU_ICE,
                    ENERGY_ORB_PIRURU_ICE, ATTACK_PIRURU_ICE_PORTRAIT,
                    SKILL_PIRURU_ICE_PORTRAIT, POWER_PIRURU_ICE_PORTRAIT,
                    ENERGY_ORB_PIRURU_ICE_PORTRAIT, CARD_ENERGY_ORB)
        }
    }

    fun Piruru() {
        BaseMod.subscribe(this)
        setModID("Piruru")
    }

    override fun receiveEditCharacters() {
        BaseMod.addCharacter(PiruruChar.PIRURU?.let { PiruruChar("Piruru", it) },
                PIRURU_BUTTON, PIRURU_PORTRAIT, PiruruChar.PIRURU)
    }

    /**
     * But Reina, I can hear you ask, why aren't you using autoAddCards from Kio? Well the answer is simple
     * I'm too lazy to copy paste all that stuff and make like 90 classes and make.sh does this for me.
     */
    override fun receiveEditCards() {
        BaseMod.addCard(AddColdARTS());//delete
		BaseMod.addCard(AddDamageARTS());//delete
		BaseMod.addCard(AddDrawARTS());//delete
		BaseMod.addCard(AllForOneButSkills());//delete
		BaseMod.addCard(AoECold());//delete
		BaseMod.addCard(AoEFreeze());//delete
		BaseMod.addCard(AoEMill());//delete
		BaseMod.addCard(AttackAndMill());//delete
		BaseMod.addCard(AttackAndScry());//delete
		BaseMod.addCard(AttackBanishAttack());//delete
		BaseMod.addCard(AttackBlock());//delete
		BaseMod.addCard(AttackDiscard());//delete
		BaseMod.addCard(AttackExhume());//delete
		BaseMod.addCard(AttackRecoverRandom());//delete
		BaseMod.addCard(AttackReturnAttacks());//delete
		BaseMod.addCard(BanishExhume());//delete
		BaseMod.addCard(BanishThenRecover());//delete
		BaseMod.addCard(BlockDrawMill());//delete
		BaseMod.addCard(ColdARTS());//delete
		BaseMod.addCard(ColdBane());//delete
		BaseMod.addCard(ColdDraw());//delete
		BaseMod.addCard(DamageAndCold());//delete
		BaseMod.addCard(DamageARTS());//delete
		BaseMod.addCard(Defend());//delete
		BaseMod.addCard(DiscardAndGainEnergy());//delete
		BaseMod.addCard(DiscardAnyAmountAndGainEnergy());//delete
		BaseMod.addCard(DiscardChain());//delete
		BaseMod.addCard(DiscardForBlock());//delete
		BaseMod.addCard(DiscardRandomCold());//delete
		BaseMod.addCard(DrawARTS());//delete
		BaseMod.addCard(EnterACRO());//delete
		BaseMod.addCard(EnterAllos());//delete
		BaseMod.addCard(EnterApexForm());//delete
		BaseMod.addCard(EnterRemember());//delete
		BaseMod.addCard(FlameBarrierButCold());//delete
		BaseMod.addCard(FreezeEnemy());//delete
		BaseMod.addCard(InfiniteScalingShit());//delete
		BaseMod.addCard(MillChainCopy());//delete
		BaseMod.addCard(MillForPowers());//delete
		BaseMod.addCard(Mulligan());//delete
		BaseMod.addCard(NoCardsBlock());//delete
		BaseMod.addCard(RecoverDamage());//delete
		BaseMod.addCard(ScoutAttacks());//delete
		BaseMod.addCard(SpreadCold());//delete
		BaseMod.addCard(Strike());//delete
		BaseMod.addCard(UnceasingTopPower());//delete
		BaseMod.addCard(AddColdARTS());//delete
		BaseMod.addCard(AddDamageARTS());//delete
		BaseMod.addCard(AddDrawARTS());//delete
		BaseMod.addCard(AllForOneButSkills());//delete
		BaseMod.addCard(AoECold());//delete
		BaseMod.addCard(AoEFreeze());//delete
		BaseMod.addCard(AoEMill());//delete
		BaseMod.addCard(AttackAndMill());//delete
		BaseMod.addCard(AttackAndScry());//delete
		BaseMod.addCard(AttackBanishAttack());//delete
		BaseMod.addCard(AttackBlock());//delete
		BaseMod.addCard(AttackDiscard());//delete
		BaseMod.addCard(AttackExhume());//delete
		BaseMod.addCard(AttackRecoverRandom());//delete
		BaseMod.addCard(AttackReturnAttacks());//delete
		BaseMod.addCard(BanishExhume());//delete
		BaseMod.addCard(BanishThenRecover());//delete
		BaseMod.addCard(BlockDrawMill());//delete
		BaseMod.addCard(ColdARTS());//delete
		BaseMod.addCard(ColdBane());//delete
		BaseMod.addCard(ColdDraw());//delete
		BaseMod.addCard(DamageAndCold());//delete
		BaseMod.addCard(DamageARTS());//delete
		BaseMod.addCard(Defend());//delete
		BaseMod.addCard(DiscardAndGainEnergy());//delete
		BaseMod.addCard(DiscardAnyAmountAndGainEnergy());//delete
		BaseMod.addCard(DiscardChain());//delete
		BaseMod.addCard(DiscardForBlock());//delete
		BaseMod.addCard(DiscardRandomCold());//delete
		BaseMod.addCard(DrawARTS());//delete
		BaseMod.addCard(EnterACRO());//delete
		BaseMod.addCard(EnterAllos());//delete
		BaseMod.addCard(EnterApexForm());//delete
		BaseMod.addCard(EnterRemember());//delete
		BaseMod.addCard(FlameBarrierButCold());//delete
		BaseMod.addCard(FreezeEnemy());//delete
		BaseMod.addCard(InfiniteScalingShit());//delete
		BaseMod.addCard(MillChainCopy());//delete
		BaseMod.addCard(MillForPowers());//delete
		BaseMod.addCard(Mulligan());//delete
		BaseMod.addCard(NoCardsBlock());//delete
		BaseMod.addCard(RecoverDamage());//delete
		BaseMod.addCard(ScoutAttacks());//delete
		BaseMod.addCard(SpreadCold());//delete
		BaseMod.addCard(Strike());//delete
		BaseMod.addCard(UnceasingTopPower());//delete
		//autoAddCards
    }

    override fun receiveEditStrings() {
        BaseMod.loadCustomStringsFile(RelicStrings::class.java, "Piruru/localization/eng/prodStrings/pirurelic.json")
        BaseMod.loadCustomStringsFile(CardStrings::class.java, "Piruru/localization/eng/prodStrings/card.json")
        BaseMod.loadCustomStringsFile(UIStrings::class.java, "Piruru/localization/eng/prodStrings/ui.json")
        BaseMod.loadCustomStringsFile(PowerStrings::class.java, "Piruru/localization/eng/prodStrings/powers.json")
        BaseMod.loadCustomStringsFile(StanceStrings::class.java, "Piruru/localization/eng/prodStrings/stances.json")
    }


    /**
     * But Reina, I can hear you ask, why aren't you using autoAddRelicsg from Kio? Well the answer is simple
     * I'm too lazy to copy paste all that stuff and make like 90 classes and make.sh does this for me.
     */
    override fun receiveEditRelics() {
        BaseMod.addRelicToCustomPool(StarterRelic(), PiruruChar.Enums.PIRURU_ICE);//delete
		BaseMod.addRelicToCustomPool(StarterRelic(), PiruruChar.Enums.PIRURU_ICE);//delete
		//autoAddRelics
        var count = 0
        var commonCount = 0
        var uncommonCount = 0
        var rareCount = 0
        var basicCount = 0
        for (c in CardLibrary.getCardList(PiruruChar.LIBRARY_COLOR)) {
            UnlockTracker.unlockCard(c.cardID)
            if (c.rarity == AbstractCard.CardRarity.BASIC) {
                basicCount++
                count++
            }
            if (c.rarity == AbstractCard.CardRarity.COMMON) {
                commonCount++
                count++
            }
            if (c.rarity == AbstractCard.CardRarity.UNCOMMON) {
                uncommonCount++
                count++
            }
            if (c.rarity == AbstractCard.CardRarity.RARE) {
                rareCount++
                count++
            }
        }
        println("COMMON CARDS$commonCount")
        println("UNCOMMON CARDS$uncommonCount")
        println("RARE CARDS$rareCount")
        println("TOTAL CARDS$count")
    }

    /**
     * https://github.com/kiooeht/Bard/blob/master/src/main/java/com/evacipated/cardcrawl/mod/bard/BardMod.java#L345
     */
    private fun loadLocKeywords(language: Settings.GameLanguage) {

    }

    override fun receiveEditKeywords() {
        val gson = Gson()
        val json = Gdx.files.internal("Piruru/localization/eng/prodStrings/keywords.json").readString(StandardCharsets.UTF_8.toString())
        val keywords = gson.fromJson(json, Array<Keyword>::class.java)

        if (keywords != null) {
            for (keyword in keywords) {
                BaseMod.addKeyword(getModID()?.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION)
            }
        }
    }

    /**
     * Idea taken from Alchyr
     * ASCII Art made from
     * https://asciiart.club/
     */
    object ASCII_ART {
        @SpireEnum
        var dab: AbstractCard.CardTags? = null

        init {
            init()
        }

        /**
         * https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
         */
        private fun getRandomNumberInRange(min: Int, max: Int): Int {
            val r = Random()
            return r.nextInt(max - min + 1) + min
        }

        private fun init() {
            val random = getRandomNumberInRange(0, 3)
            if (random == 0) {
                println("\n" +
                        "                                                           ,╔▄▄▄▄▓▓▓▓▄▄w\n" +
                        "                                                   ╓▄▄  ▄▓▓████████████▓▓▓╦\n" +
                        "                                                ,╣▓▓██▓▓█▓███████▓▓▓▓▓▓▓▓▓▓▓╗\n" +
                        "                                                ▓██▓█▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╫▓▓╫╫▓,\n" +
                        "                                                ▓███▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓┐\n" +
                        "                                                ╙▀█▓▓▓▓▓▓▓▓▓▓▓▓▓Å▓╣▓▓▓▓▓▓▓▓██▓▓▌\n" +
                        "                                                 .▓▓▓▓▓█▓M▓▓█▓██H▓╟██▓▌█████████H\n" +
                        "                                                 ║▓████╬▓^▓╫███▌ Ñ║███░▀████████▌\n" +
                        "                                                 ╫▓████ ╣ ,,╙╙▀   \"╫▌▀▓█▓╫█▓╫███M\n" +
                        "                                                 ║M▓█▀█▌║▌╥╠▓N     `╙Ñ#╨`▓█▌░██▌\n" +
                        "                                                 ╙H╢█░███⌐ ╙╙`          ╣█╩╩▓██M\n" +
                        "                                                  \"╦▓▓████            \"╟▀╔▓██▌█M\n" +
                        "                                             .▄▓▓▓█████████µ       .═ ^ ╔░███M█H\n" +
                        "                                             ╣▓▓▓▓▓█████████▓▄,      ,«╨»»╟▓╫▌╙▓\n" +
                        "                                       ,    ╔▓█████████████╠╨╫▒╫╬╗«╦░»»»»»╔▓▓▓╫╣▀╦╦╗,\n" +
                        "                                       `══%▓█▓███████████ ╟░/╨░╫▓▌≈░»µw«═╗▓▓╫Ü]░╨`  ╙╬\n" +
                        "                                          ▓█▀╙` ▄▓██████▌ ╫Ñ]░Ü░╩╫╫╫╬╗µ╓╬╫╫░░╦^      ╟Ñ\n" +
                        "                                         jÑ`  ╔▓▀╙ ▓████▌ Ñ╫▄½¿░░╫╫╫▓╫▓░░░░╬M   ,»/  ╟▓U\n" +
                        "                                          `   ╫`  ╟█████▌ ╫╣▓▓╬╦░╫╫╫▓M░░╦▄▓M  .n»\"   ╟▓▓╗\n" +
                        "                                              V,   ▀███╨╚ ╫░╫╫▀▓▄╫╫╫╬╬╣▓▓╫Ñ ,µ«^`   ,╣▓▓╫╬╗,\n" +
                        "                                                    \"▀M;`½ ╫╨╨╨╫Ñ╫╫╫╫╫╫Ñ╨~═^`       ╠╫▓▓▓▓╫╫Ñ═\n" +
                        "                                                      `½   ] `,╫╦╬▓H»╚░            `]╫▓▓▓▓M\"\n" +
                        "                                                        ]╦,,╦╬░╫╫Å┴`  ╔w      ..,»»µH╨╨`\n" +
                        "                                                         ╚╨╫Ü╫╫╫Ü`   ,Ö░╫╦µµ»»»»╓╦╨░»\n" +
                        "                                                          ╫╫╫╫╫▓⌂    ╫░╦╦░╫╫╣▓▓▓▌»»»`»`\n" +
                        "                                                        ,╫╫╫╫▓▓H╙,  .`╙╨╩╣▓▓▓╫╫M»»` ,«`\n" +
                        "                                                       ]░╫╫╫▓▓▌``!]/   `\"╓╩````\"\"```\n" +
                        "                                                         `╙▀▓▓]  ,\"     ]░»»»,\n" +
                        "                                                            ``╙ \"     ,╩µ»»»»»»»;»,.  STAN YOHANE\n")
            }
            if (random == 1) {
                println("\n" +
                        "    ███████████████████████████████████▓▓▓▓▓▓▓▓▓▓███████████████████████████████████\n" +
                        "    ███████████████████████████▓▀╫╫╫╫╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓╫╫▓█████████████████████████████\n" +
                        "    ███████████████████████▀╫╫╫╫╫╫╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╫▓█████████████████████████\n" +
                        "    ████████████████████▀╫╫╫╫╫╫╫╫╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██████████████████████\n" +
                        "    ██████████████████╫╬╫╫╫╫╫╫╫╫╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓╫▓▓▓╫╫╫▓▓▓╬╣▓█████████████████████\n" +
                        "    ████████████████Ü]╬╫╫╫╫╫╫╫▌╫╫╫▓▓▓▓╫▓╣▓▓▓╫▓╫╣╫▓╫╟╫╣╫╫╫▓▓▓▓╫▓▓▓███████████████████\n" +
                        "    █████████████▀╫╫╫╫╫╫╠╫╫╫╫▓Ñ╣╫╣▓▌╣▓╬╣▓╫╫▓▓╫╫Ñ╫▓▓╬╬╫╣▓▓▓▓▓▓▓▓▓▓███████████████████\n" +
                        "    ███████████▌╫╫╫╫╫╫╫╫╫▓╫▓▓▓╫▓▓╫█▓▓▓▓▓▓▓▓▓██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██████████████████\n" +
                        "    █████████▓╫╫╫╫▓╫╫▓▓╫▓▓▓▓██▓▓▓▓█╫▀███████████▓▓▓▓▓█▓▓▓▓▓▓▓▓▓▓▓▓▓█████████████████\n" +
                        "    ███████▓█▓╫▓▓▓▓▓▓▓▓██▓█╣▌▀▓████▌Ñ╙╨▀█▀█████╫▀██▓▓█▓█▓▓▓▓▓▓▓▓▓▓▓▓████████████████\n" +
                        "    ████████▓▓▓█▓▓▓▓█▓▓█▒╫M░▓╫╙█████Ñ ,æ╣▓▓▓▓▓█▌╫╫▓▓██████▓▓▓▓▓▓▓▓▓▓▓███████████████\n" +
                        "    ████████▓██▓▓▓▓██▓██▓▀▀▀▀M» ╙▀███░  ` ..  `╙╨▀╫▓█████▓▓▓▓▓▓▓▓▓▓▓▓▓██████████████\n" +
                        "    ███████▓██▓▓▓████╫█M ... ``  `  ╙╨`  »╦░░≈»» ╨╫█╫███▓▓▓▓▓▓▓▓█╫╫▓▓▓▓▓████████████\n" +
                        "    ▓█████████▓▓█████▌H\"»»\"\"\"`          ``       `╟▒╫███▓▓▓▓▓▓▓▓█▌▓▓▓▓╫▓╫▀██████████\n" +
                        "    ▓█████████████████H                           ╚╨╫▓██▓▓▓▓▓▓█╫█▌█▓▓█▓╫▓▓▓▓▓███████\n" +
                        "    ▓████████▓▓▓╫████▒Ü─     .          `»»»┬╥╥¡   »╫▓█╫╫╫╫▓╫██▓▓██▌▓██▓╫╫██████████\n" +
                        "    ▓█████▓▓▓╫╫╫▓▓▓███H  ,▄#▓▓φ         ,╫█▀▀╜▀▀▀▓▄»░▓▌╫╬╣╣╬╫█╫▓▓███▓██▓██▓██▓▓▓▓▓▓█\n" +
                        "    ████▓▓▓╫╫╫╫╫╫╫▓╫▓█▌`.▌`   ╙─          `      `█M╔▓╫▓╫╫▓▌▓╫╫▓██████▌█▀███▓▓▓▓▓▓▓▓\n" +
                        "    ███▓▓▓╫╫╫╫╫╫╫╫╫▓╫██▄: `     ;,»            »»»»»╣▓▓▓▓▓▓▌▌▄████████▓█▓▓█▓▓▓▓▓▓▓▓▓\n" +
                        "    ▓▓▓▓▓╫╫╫╫╫╫▓▓╫▓▓▌▓██»»»'    \"]░░         `»»»»╓▓██▓▓▓█▓▓█████╫██▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ▓▓▓▓▓╫╫╫╫╫▓╫▓▓███▓██H````     ``         ```╓╫▀╣█▓▓████▓███╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ▓▓▓▓▓╫╫╫╫╫╫╫╫▓█╫╫▓▓█▌¿`                   `'\"░╫▓██████████╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ▓▓▓▓╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫▓▌╫▄.       ,   `       .»╦╫███▓████╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ▓▓▓▓╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫▓Nµ     ` ``       ,╔╫╫╫▀╫╫████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ▓▓▓▓╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫Ñ╗¡         ,╔╬╫╫╫╫╫╫╫╫▌╫█▓▓▓▓▓▓▓▓▓╫╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ▓▓▓▓▓╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╬╦µ,,,╔╗╬╫╫╫╫╫╫╫╫╫╫╫╫╫╫▓▓▓▓▓▓▓▓╫╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ╫▓▓▓╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫Ñ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫Ñ╨``╙╣▓▓▓╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫M`╨╩╬╫╫╫╫Ñ╩╩╨\"╙``   .╟╫╫╫▓▓╫╫▓╫▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫Å╩╨]`   ╔░Ñ╫Ñ╫░          `╟M╨░░╨╨╜╣▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╣╩╨\"`   ``j»µ╦╔█▓▓▄▄▄▄         ,A````````»»»╙╣▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫Å`         `»U╠╫██▓▓▓▓██▌¿   ,»µñ░`    `   `````»╙▀▓▓▓▓▓▓▓▓▓▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╨             \"╨╨╩▓▓▓▓██▀▒░«»*\"\"`                ``»░╣▓▓▓▓▓▓▓▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫`                 »║████▌╫Ü`                        `!░╨▓▓▓▓▓▓▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫H                  ║█████▌╫`                          `»»░▓▓▓▓╫▓▓\n" +
                        "    ╫╫╫╫╫╫╫╫╫╫╫╫╫╫╫                   ▓██▓██▌╨                     ']    `»»»╠▓▓▓▓▓▓ Rin Tezuka <3\n")
            }
            if (random == 2) {
                println("\n" +
                        "    ▐██████████████████████████████████▌░█████`╫█▌░░╫███▌ ░▓╫█████▌░███████████████M\n" +
                        "    ▐█████████████████████████████████▀▀╣███▀ ╔█▀Ñ\"░▓██▌  ╟M╣█████░░▓██████████████M\n" +
                        "    ▐████████████████████████████████▀╩ ▓██▀*Φ█▌╨  ]███   ╫░╫████Mñ░▓███▌▓█████████M\n" +
                        "    ▐███████████████████████████████Ü\" ╓█▀` ▄▀░`╙*╤▄██H   ▌░▓███▌ ]░╫███Ü▓█████████M\n" +
                        "    ▐██████████████████████████████░` ,▓\"  /Ü\"     ▐█▀W¿ ║`░▓███  ]░╫██▌]▓█████████M\n" +
                        "    ▐███████▀╙████████████████████▓▄▄▄,    ',,     j█    ▌ ]▓██   ]Ñ╫██ ]╫█████████M\n" +
                        "    ▐██████`#▀██████████████████`╟██████▓▓▓▄▄;\"*%µ jM   j  `▓█┘   ]`▓█▌,╔▓█████████M\n" +
                        "    ▐█████ ║░░╫████████████████▌ ▓██▓█▓▓▓▓▀`▀▀▀M╗       \"   █\"    \".█▀  »╫█████████M\n" +
                        "    ▐█████ ]░░╙████████████████▌ █▌Å╣██▓█▓▓▄m``  `         ╝       ▐▀   »▓█████████M\n" +
                        "    ▐█████▄`╨╦░▓███████████████▌ⁿ█▓╦╦▀▀▀░╫▓▌                       ╨**% `▓█████████M\n" +
                        "    ▐██████▄` ╚╣████████████████   ╙▀▀▓▄▄▓▌                       ▓██▓▓▄▄██╟███████M\n" +
                        "    ▐████████▄  ▓███████████████                                 ▓███⌠``H▀╔╢███████M\n" +
                        "    ▐███████████▓███████████████⌐`  .                           ╟███▓▌    ╔████████░\n" +
                        "    ▐███████████████████████████M'```                           █╫▀╫▌   `,████████▀░\n" +
                        "    ▐███████████████████████████▌         `                    ╙▀▄▄▌    .████████▌╫░\n" +
                        "    ▐████████████████████████████                                       ▓███████╫╫╫░\n" +
                        "    ▐██████████████░███████████║█Ω                         ▄      . .  ▓███████╫╫╫╫░\n" +
                        "    ▐█████████████░▄▄██████████H██                         ╙^    ``'`,▓██████▌╫╫╫╫╫░\n" +
                        "    ▐███████████████████████████╟█▄                                `╓████████▌╫╫╫╫╫░\n" +
                        "    ▐█████████████▀▒▒▀██████████▌▀█H╥         ````\"               ╓▄█████████╫╫╫╫╫╫░\n" +
                        "    ▐███████████▀╫╫╫╫╫▀╙▓████████░║█╫▄≈                     ,╓▄▓████████████▀▀▄Å▀▀╙░\n" +
                        "    ▐█████████▓▌╫╫╫╫╫╫▌  \"▓█▀\"▀▀█▓▄▓▌╙▓▄▄            ,╓▄▄▄█▓╫╫╫▓██████████▀░▄▀▓Ö─`╙░\n" +
                        "    ▐████████▓▌╫╫╫╫╫╫╬╬▌M╙` ,    '▀▀██████▓▄µ╓▄▄▄▄▓████████╫╫╫╫▓█████████▓▄▀▄╨»╙▄» :\n" +
                        "    ▐███████▓▀╫╫╬╫╩╩╙└ ` .µ▄▄░╦      ╙▀███████████████████▌╫╫╫╫▓████████▓▓▀▓░»»»╙▌ª⌐\n" +
                        "    ▐███████╫╫╫╨    ,,▄▄▓▓▓▓▓█▓▄µ        ▀▀███████████████╫╫╫╫╫████████▓▓▀▓╙▀▄»`»║⌐:\n" +
                        "    ▐█████▌╫╫╫╫╫▓▓██▀▀└     ``└'            ╙▀╫▄░▒███████▌╫╫╫╫╫███████▓▓▌╣░░╔╝╫⌂`.╣:\n" +
                        "    ▐█████╫╫╫▄▓╩▀╙  ,;╦╦╦╥╥,,.           ▄╦▄╗#Φ▓▀▀▓▒▀█▓▒▀▓╫╫╫╫╫███████▓▌║Ü░╔▌»,▓▄»▐░\n" +
                        "    ▐█████▀\"└    »≈╨ÑΦ▀▀▀▀▀╨╨╨^          ╙▀▀▓▓▓▓▓▓M\"╫╫███▓▓▓╫╫▓██████▓█╔▌░░▓░░╣Ñ╙@ ░\n" +
                        "    ▐████▌▓▄╝╙                              `▀▓████▄ ╣╫▓████▓▓██████▓▓H▓░░║M░║╫░»║⌂:\n" +
                        "    ▐████▒╫╫¥▄µ,,,,,,,╓╓▄▄▄▄▄▄▄▄µ,            \"▀▓███▓▓▌╫████████████▓▌╦▌░░▓░░▌Ñ»»░╣▐\n" +
                        "    ▐████M╫╫╫╫╫▓▓▓▓▓▓▓▓▓▓▓▓█▀▀▀▀▀▀*             ╙▓▓╨. ▌╫╢████████████M╢Mµ▄Ü░╟╫H░▄M╢M Homura did nothing wrong.\n")
            }
            if (random == 3) {
                println("\n" +
                        "                                    `'```''`````'`''```` `\n" +
                        "                                  `» .¡;µ╦╦╦╦╦µ¡»»»»:»»````\n" +
                        "                                 »,»]╦╦░░░░░░░░░░░░╦░»»`''`''```\n" +
                        "                              '»»\"»░░░░░░░░░░░░░░░░░░░≈\"»»''````\n" +
                        "                            ``,░`  `░░░░░░░░░░░░░░░░\"`   \"»»``'```\n" +
                        "                            ',╨,   »░░░░░░░░░░░░░░`       `»»''```` `\n" +
                        "                           »»░╦░╦╦░░░░░░░░Ñ░╦░░╦╦░╦»,,╦╦╦»,.»»`'`````\n" +
                        "                       `   »»░░░░░░╦░░╦╦Ñ╫░╬╫╫╫╫╫Ñ░╦░░░░░░░╦ù`''`````\n" +
                        "                       ' ` »░░░░░░ÑÑ░╦Ñ╫ÑÑ»»╩╫░╫╫╫╫╦╫░░░░░░░░H`'`````\n" +
                        "                       '```╠░░░╦░╫╫╫╫Ñ╫HÑ░»»»╨H»╚╫░╨╫Ñ░░░░░░Ü]`'`````\n" +
                        "                       '  '╫]░░╫╦╫H╫╫░Ñ»╬`````\"»≈╠░»╨Ñ░░░░░░░░````''`\n" +
                        "                      ```'»Ñ╦░░╠░H,╟╫M¥H```````▐╨╣M╠M░Ñ░░K░░░UH»»»`'`   `\n" +
                        "                      `'''»Ñ╫░░░╫╦╨╬╫▄╬`        ╠▓▓╫▓\"╟░░╫░░H╙U»`»' ```\n" +
                        "                  ''  '»'']Ñ╫░░░░╠ ╟╫▀╫H '»     `╠╫╫Ñ:▐Ñ░░░░H`╙U»»`````\n" +
                        "                  ``` '»`']Ñ╫░░░░░»`╙╙\"  '»``   `     `Ñ░░]░`»`]»'.````\n" +
                        "                 ``'` '»'`»Ñ╬░░░]H'      ```           ░Ñ░░H ``╨░»`»»``\n" +
                        "                ```'` '»'`»Ñ░░░░░╫ `       ``    `    ]KÑ░░░H `»Ü»``»``\n" +
                        "               ````'` '»``»Ñ░░░░░╫Ñ⌂      `,,,»     ,╬╫M»╟░░╦ ,░``»````   `\n" +
                        "                 `'`'''»`'»Ñ░░░░]╫╫╫Nµ,          :╔╬╫╫╫```╫Ñ╫╫╫H``'````\n" +
                        "                 ````''```»Ñ░Ñ░░]╫╫╫╫╫╫╬N≈,,.,,»ñ░╫╫╫╫╫ ``╙╫╫╫╫╫`»`````\n" +
                        "                    `````'»Ñ░Ñ░░╫╫╫╫╫╫╫╫╫m»░░░»»»░╫╫╫╫╫H '`╙╫╫╫╫»``'``\n" +
                        "                    ````'`»Ü░Ñ░░╫╫╫Ñ╫╫╫╫╫Ñ»»»»»»»░╟╫╩╫╫╫:   ╙╫╫╫H`\n" +
                        "                 `````````]░╫Ñ░░╫╩╨╨╨`╚╨░░»»»»»»»»░░░`]╬░`  '╟Ñ╫H`  `\n" +
                        "                 ``````»``]Ñ╨╫░░Ü``    »`»»»»»»»\"``; `]░H` '``╫╫Ü   `\n" +
                        "          `````````````»»»╨! ╟░░`     ```````\":`  `░Ü»:   ╙╫╫`'``\n" +
                        "       ````''''''```````»╔  \\\"░░╦                .^    ]Ñ»»    ╙╫░».'\n" +
                        "       ''`````'````````»»╩   `]░╬N,     \"       .`     `Ü»»`   ```\"»¡`\n" +
                        "      `  ```` '''''''''`/     `╦░░`╨╦╥   \".    ,`  .,╥╦ñ╨░ '    '\", `»:'  `\n" +
                        "     ``` ''''''''''''''`╨  . . \"ÑÜ»╥ `╨╩╦╦╥H .]N╩╨╨\"`  ,»» ``    '╙N╦»`» ` `\n" +
                        "     ``` '''''''''''»»»»    » »` ╨H`╨»╦»≈,,,U≈╥«;»╦≈^`   `` `     `╙░N,` ```  `  `\n" +
                        "     ``` '''''''''''`»»`     \".`       `\"╥░░░*░░``        »```     .¡`H»``` Trans rights are human rights\n")
            }
        }
    }
}