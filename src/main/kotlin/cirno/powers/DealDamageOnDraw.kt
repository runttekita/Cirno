package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import cirno.interfaces.Helper
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.PowerStrings

class DealDamageOnDraw(owner: AbstractCreature, amount: Int) : CirnoPower(), Helper {
    override val defaultDamage
        get() = amount2
    override val defaultSource: AbstractCreature
        get() = player

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
        updateDescription()
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
        this.owner = owner
        this.amount = 1
        this.amount2 = amount
    }

    override fun stackPower(amount: Int) {
        fontScale = 8.0f
        amount2 += amount
        this.amount += 1
    }

    override fun onCardDraw(card: AbstractCard?) {
        damage(owner)
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
       act(ReducePowerAction(owner, owner, this, 1))
    }

    override fun updateDescription() {
        description = "$DESCRIPTIONS!![0]$amount2$DESCRIPTIONS[1]"
    }

}