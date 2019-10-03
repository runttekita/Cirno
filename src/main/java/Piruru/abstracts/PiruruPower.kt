package Piruru.abstracts

import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.AbstractPower

import Piruru.Piruluk.Statics.initialize
import Piruru.Piruluk.Statics.makeID
import Piruru.Piruluk.Statics.makePowerPath
import Piruru.Piruluk.Statics.textureLoader

abstract class PiruruPower : AbstractPower() {
    var NAME: String
    var DESCRIPTIONS: Array<String>

    init {
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        ID = POWER_ID
        updateDescription()
        img = textureLoader.getTexture(makePowerPath(this.javaClass.simpleName))
    }

    abstract override fun updateDescription()

    override fun stackPower(amount: Int) {
        fontScale = 8.0f
        this.amount += amount
    }

    companion object TheStrings {
        val POWER_ID : String = makeID(this::class.java.simpleName)
        internal var powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
    }

}
