package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import basemod.interfaces.CloneablePowerInterface
import cirno.interfaces.Helper
import cirno.interfaces.OnApplyCold
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.AbstractPower

class Cold : CirnoPower, CloneablePowerInterface, Helper {

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
        private const val PROC_AMOUNT = 3
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
    }

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
            act(
                    RemoveSpecificPowerAction(owner, owner, this))
            act(
                    ApplyPowerAction(owner, AbstractDungeon.player, Frozen(owner)))
        }
        for (card in AbstractDungeon.player.drawPile.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
        for (card in AbstractDungeon.player.discardPile.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
        for (card in AbstractDungeon.player.exhaustPile.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
        for (card in AbstractDungeon.player.hand.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
    }

    override fun stackPower(amount: Int) {
        fontScale = 8.0f
        this.amount += amount
        for (card in AbstractDungeon.player.drawPile.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
        for (card in AbstractDungeon.player.discardPile.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
        for (card in AbstractDungeon.player.exhaustPile.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
        for (card in AbstractDungeon.player.hand.group) {
            if (card is OnApplyCold && owner is AbstractMonster) {
                card.onApplyCold(owner as AbstractMonster)
            }
        }
        if (this.amount >= PROC_AMOUNT && amount - PROC_AMOUNT < 1) {
            act(
                    RemoveSpecificPowerAction(owner, owner, this))
            act(
                    ApplyPowerAction(owner, AbstractDungeon.player, Frozen(owner)))
        } else {
            act(
                    ReducePowerAction(owner, owner, this, PROC_AMOUNT))
            act(
                    ApplyPowerAction(owner, AbstractDungeon.player, Frozen(owner)))
        }
    }

    override fun makeCopy(): AbstractPower {
        return Cold(amount, owner)
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0] + PROC_AMOUNT + DESCRIPTIONS!![1]
    }

}
