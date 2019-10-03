package Piruru.actions

import Piruru.powers.Frozen
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

class FreezeMonsterAction @JvmOverloads constructor(target: AbstractMonster, source: AbstractCreature, amount: Int = 1) : AbstractGameAction() {

    init {
        this.target = target
        this.source = source
        this.amount = amount
        actionType = AbstractGameAction.ActionType.DEBUFF
        duration = Settings.ACTION_DUR_FAST
    }

    override fun update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToTop(ApplyPowerAction(target, source, Frozen(target as AbstractMonster)))
        }
        tickDuration()
    }
}
