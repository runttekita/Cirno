package Cirno.actions

import Cirno.Cirno.Statics.makeID
import Cirno.powers.Cold
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster


class SpreadColdAction(private val targeted: AbstractCreature) : AbstractGameAction() {
    private val amount: Int = 0

    //TODO Refactor this so it isn't ugly as sin
    override fun update() {
        if (targeted.hasPower(makeID(Cold::class.java)) && target is AbstractMonster) {
            val slot = AbstractDungeon.getMonsters().monsters.indexOf(target as AbstractMonster)
            var left: AbstractMonster? = null
            if (slot > 0) left = AbstractDungeon.getMonsters().monsters[slot - 1]
            var right: AbstractMonster? = null
            if (slot < AbstractDungeon.getMonsters().monsters.size - 1)
                right = AbstractDungeon.getMonsters().monsters[slot + 1]
            if (left != null && !left.isDeadOrEscaped) {
                AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(left, AbstractDungeon.player, Cold(left)))
            }
            if (right != null && !right.isDeadOrEscaped) {
                AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(right, AbstractDungeon.player, Cold(right)))
            }
        }
        isDone = true
    }
}
