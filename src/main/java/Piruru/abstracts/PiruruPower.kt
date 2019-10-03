package Piruru.abstracts

import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.AbstractPower

import Piruru.Piruluk.Statics.initialize
import Piruru.Piruluk.Statics.makeID
import Piruru.Piruluk.Statics.makePowerPath
import Piruru.Piruluk.Statics.textureLoader

abstract class PiruruPower : AbstractPower() {
    init {

    }

    abstract override fun updateDescription()

    override fun stackPower(amount: Int) {
        fontScale = 8.0f
        this.amount += amount
    }

}
