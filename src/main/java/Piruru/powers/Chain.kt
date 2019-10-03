package Piruru.powers

import Piruru.abstracts.PiruruPower
import Piruru.interfaces.OnRefreshHand
import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.utility.UseCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class Chain(owner: AbstractCreature, amount: Int) : PiruruPower(), OnRefreshHand {

    init {
        this.owner = owner
        this.amount = amount
    }

    override fun updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2]
        }
    }

    override fun onUseCard(c: AbstractCard?, a: UseCardAction?) {
        for (ca in AbstractDungeon.player.drawPile.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca)
                ca.cost++
                ca.isCostModified = false
                ChainedField.chained.set(ca, false)
            }
        }
        for (ca in AbstractDungeon.player.discardPile.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca)
                ca.cost++
                ca.isCostModified = false
                ChainedField.chained.set(ca, false)
            }
        }
        for (ca in AbstractDungeon.player.hand.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca)
                ca.cost++
                ca.isCostModified = false
                ChainedField.chained.set(ca, false)
            }
        }
        for (ca in AbstractDungeon.player.exhaustPile.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca)
                ca.cost++
                ChainedField.chained.set(ca, false)
            }
        }
        AbstractDungeon.actionManager.addToBottom(ReducePowerAction(owner, owner, this, 1))
    }

    override fun onRefreshHand() {
        for (c in AbstractDungeon.player.hand.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--
                    c.isCostModifiedForTurn = true
                    ChainedField.chained.set(c, true)
                }
                if (c.cost > 0) {
                    c.cost--
                    c.isCostModified = true
                    ChainedField.chained.set(c, true)
                }
            }
        }
    }

    override fun onInitialApplication() {
        for (c in AbstractDungeon.player.drawPile.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn)
                    c.isCostModifiedForTurn = true
                }
                if (c.cost > 0) {
                    c.cost--
                    c.isCostModified = true
                }
                ChainedField.chained.set(c, true)
            }
        }
        for (c in AbstractDungeon.player.discardPile.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn)
                    c.isCostModifiedForTurn = true
                }
                if (c.cost > 0) {
                    c.cost--
                    c.isCostModified = true
                }
                ChainedField.chained.set(c, true)
            }
        }
        for (c in AbstractDungeon.player.exhaustPile.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn)
                    c.isCostModifiedForTurn = true
                }
                if (c.cost > 0) {
                    c.cost--
                    c.isCostModified = true
                }
                ChainedField.chained.set(c, true)
            }
        }
        for (c in AbstractDungeon.player.hand.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn)
                    c.isCostModifiedForTurn = true
                }
                if (c.cost > 0) {
                    c.cost--
                    c.isCostModified = true
                }
                ChainedField.chained.set(c, true)
            }
        }
    }

    @SpirePatch(clz = AbstractCard::class, method = SpirePatch.CLASS)
    object ChainedField {
        var chained = SpireField { false }
    }

    @SpirePatch(clz = AbstractCard::class, method = SpirePatch.CLASS)
    object IsCostModifiedForTurnField {
        var costModifiedTurn = SpireField { false }
    }
}
