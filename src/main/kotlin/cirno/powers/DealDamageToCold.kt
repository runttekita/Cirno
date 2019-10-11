package cirno.powers

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoPower
import cirno.interfaces.Helper
import cirno.interfaces.isShivering
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.PowerStrings

class DealDamageToCold(amount: Int, owner: AbstractCreature) : CirnoPower(), Helper {
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
        this.amount = amount
        isTurnBased = true
    }

    override fun onCardDraw(card: AbstractCard?) {
        if (player.isShivering) {
            loopOverMonsters {
                m -> m.getPower(makeID(Cold::class.java))?.let {
                damage(it.owner, amount2, player)
            }}
        }
    }

    override fun updateDescription() {
        description = "${DESCRIPTIONS!![0]}$amount${DESCRIPTIONS!![1]}"
    }

}