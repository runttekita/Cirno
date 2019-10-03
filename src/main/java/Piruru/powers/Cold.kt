package Piruru.powers

import Piruru.abstracts.PiruruPower
import basemod.interfaces.CloneablePowerInterface
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower

class Cold : PiruruPower, CloneablePowerInterface {

    constructor(owner: AbstractCreature) : super() {
        this.owner = owner
        this.amount = 1
        isTurnBased = true
    }

    constructor(amount: Int, owner: AbstractCreature) : super() {
        this.amount = amount
        this.owner = owner
        isTurnBased = true
    }

    override fun onInitialApplication() {
        if (amount >= PROC_AMOUNT) {
            AbstractDungeon.actionManager.addToBottom(
                    RemoveSpecificPowerAction(owner, owner, this))
            AbstractDungeon.actionManager.addToBottom(
                    ApplyPowerAction(owner, AbstractDungeon.player, Frozen(owner)))
        }
    }

    override fun stackPower(amount: Int) {
        fontScale = 8.0f
        this.amount += amount
        if (this.amount >= PROC_AMOUNT && amount - PROC_AMOUNT < 1) {
            AbstractDungeon.actionManager.addToBottom(
                    RemoveSpecificPowerAction(owner, owner, this))
            AbstractDungeon.actionManager.addToBottom(
                    ApplyPowerAction(owner, AbstractDungeon.player, Frozen(owner)))
        } else {
            AbstractDungeon.actionManager.addToBottom(
                    ReducePowerAction(owner, owner, this, PROC_AMOUNT))
            AbstractDungeon.actionManager.addToBottom(
                    ApplyPowerAction(owner, AbstractDungeon.player, Frozen(owner)))
        }
    }

    override fun makeCopy(): AbstractPower {
        return Cold(amount, owner)
    }

    override fun updateDescription() {
        description = DESCRIPTIONS[0] + PROC_AMOUNT + DESCRIPTIONS[1]
    }

    companion object {
        private val PROC_AMOUNT = 3
    }
}
