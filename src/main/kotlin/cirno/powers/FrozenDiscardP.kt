package cirno.powers

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoPower
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings

class FrozenDiscardP() : CirnoPower() {
    var frozenDiscard = true

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
    }

    init {
        val POWER_ID : String = Cirno.makeID(this::class.java.simpleName)
        val powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        ID = POWER_ID
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        this.owner = AbstractDungeon.player
        updateDescription()
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0]
    }

    override fun stackPower(amount: Int) {
    }

    override fun onInitialApplication() {
        AbstractDungeon.player.frozenDiscard = true
    }

    override fun onVictory() {
        AbstractDungeon.player.frozenDiscard = false
    }

}

var AbstractPlayer.frozenDiscard: Boolean
    get() = (AbstractDungeon.player.getPower(makeID(FrozenDiscardP::class.java)) as FrozenDiscardP)?.frozenDiscard
    set(value) {(AbstractDungeon.player.getPower(makeID(FrozenDiscardP::class.java)) as FrozenDiscardP)?.frozenDiscard = value}
