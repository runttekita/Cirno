package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import basemod.interfaces.CloneablePowerInterface
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.AbstractPower

class FlameBarrierButColdP(owner: AbstractCreature, amount: Int) : CirnoPower(), CloneablePowerInterface {

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

    override fun onAttacked(info: DamageInfo, damageAmount: Int): Int {
        AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(info.owner, owner,
                Cold(info.owner), 1))
        return damageAmount
    }

    override fun atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(
                ReducePowerAction(owner, owner, this, 1))
        if (amount == 0) {
            AbstractDungeon.actionManager.addToBottom(RemoveSpecificPowerAction(AbstractDungeon.player,
                    AbstractDungeon.player, this))
        }
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0] + DESCRIPTIONS!![1]
    }

    override fun makeCopy(): AbstractPower {
        return FlameBarrierButColdP(owner, amount)
    }
}
