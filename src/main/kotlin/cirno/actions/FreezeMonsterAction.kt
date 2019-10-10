package cirno.actions

import cirno.Cirno.Statics.makeID
import cirno.interfaces.Helper
import cirno.powers.FreezeNextTurn
import cirno.powers.Frozen
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

class FreezeMonsterAction @JvmOverloads constructor(target: AbstractMonster, source: AbstractCreature, amount: Int = 1) : AbstractGameAction(), Helper {

    init {
        this.target = target
        this.source = source
        this.amount = amount
        actionType = AbstractGameAction.ActionType.DEBUFF
        duration = Settings.ACTION_DUR_FAST
    }

    override fun update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            if (target.hasPower(makeID(Frozen::class.java))) {
                act(ApplyPowerAction(target, source, FreezeNextTurn(target, 1), 1))
            }
            AbstractDungeon.actionManager.addToTop(ApplyPowerAction(target, source, Frozen(target as AbstractMonster)))
        }
        tickDuration()
    }
}
