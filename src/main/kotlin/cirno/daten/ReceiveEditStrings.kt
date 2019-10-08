package cirno.daten

import basemod.BaseMod
import com.megacrit.cardcrawl.localization.*

class ReceiveEditStrings {

    init {
        BaseMod.loadCustomStringsFile(CardStrings::class.java, "cirno/localization/eng/prodStrings/card.json")
        BaseMod.loadCustomStringsFile(UIStrings::class.java, "cirno/localization/eng/prodStrings/ui.json")
        BaseMod.loadCustomStringsFile(PowerStrings::class.java, "cirno/localization/eng/prodStrings/powers.json")
        BaseMod.loadCustomStringsFile(RelicStrings::class.java, "cirno/localization/eng/prodStrings/relics.json")
    }

}