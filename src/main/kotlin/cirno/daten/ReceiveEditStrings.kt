package cirno.daten

import basemod.BaseMod
import com.megacrit.cardcrawl.localization.*

class ReceiveEditStrings {

    init {
        BaseMod.loadCustomStringsFile(RelicStrings::class.java, "Cirno/localization/eng/prodStrings/pirurelic.json")
        BaseMod.loadCustomStringsFile(CardStrings::class.java, "Cirno/localization/eng/prodStrings/card.json")
        BaseMod.loadCustomStringsFile(UIStrings::class.java, "Cirno/localization/eng/prodStrings/ui.json")
        BaseMod.loadCustomStringsFile(PowerStrings::class.java, "Cirno/localization/eng/prodStrings/powers.json")
        BaseMod.loadCustomStringsFile(StanceStrings::class.java, "Cirno/localization/eng/prodStrings/stances.json")
    }

}