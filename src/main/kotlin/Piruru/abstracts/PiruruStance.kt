package Piruru.abstracts

import Piruru.Piruluk.Statics.makeID
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.StanceStrings
import com.megacrit.cardcrawl.stances.AbstractStance

abstract class PiruruStance : AbstractStance() {
    var STANCE_ID: String = makeID(this.javaClass)
    var stanceStrings: StanceStrings

    init {
        ID = STANCE_ID
        stanceStrings = CardCrawlGame.languagePack.getStanceString(ID)
        name = stanceStrings.NAME
        updateDescription()
    }

}
