package Piruru.brokenupgodobject

import basemod.BaseMod
import com.megacrit.cardcrawl.localization.*

class ReceiveEditStrings {

    init {
        BaseMod.loadCustomStringsFile(RelicStrings::class.java, "Piruru/localization/eng/prodStrings/pirurelic.json")
        BaseMod.loadCustomStringsFile(CardStrings::class.java, "Piruru/localization/eng/prodStrings/card.json")
        BaseMod.loadCustomStringsFile(UIStrings::class.java, "Piruru/localization/eng/prodStrings/ui.json")
        BaseMod.loadCustomStringsFile(PowerStrings::class.java, "Piruru/localization/eng/prodStrings/powers.json")
        BaseMod.loadCustomStringsFile(StanceStrings::class.java, "Piruru/localization/eng/prodStrings/stances.json")
    }

}