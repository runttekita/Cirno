package Cirno.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.watcher.CannotChangeStancePower
import com.megacrit.cardcrawl.stances.AbstractStance

class NotShittyChangeStanceAction(private val newStance: AbstractStance) : AbstractGameAction() {

    override fun update() {
        if (AbstractDungeon.player.hasPower(CannotChangeStancePower.POWER_ID)) {
            isDone = true
            return
        }

        val oldStance = AbstractDungeon.player.stance
        if (oldStance.ID != newStance.ID) {
            for (p in AbstractDungeon.player.powers) {
                p.onChangeStance(oldStance, newStance)
            }
            for (r in AbstractDungeon.player.relics) {
                r.onChangeStance(oldStance, newStance)
            }
            oldStance.onExitStance()
            AbstractDungeon.player.stance = newStance
            newStance.onEnterStance()
            AbstractDungeon.actionManager.stancesSwitchedThisTurn.add(newStance)
            AbstractDungeon.player.switchedStance()
            for (c in AbstractDungeon.player.discardPile.group) {
                c.triggerExhaustedCardsOnStanceChange(newStance)
            }
            AbstractDungeon.player.onStanceChange(newStance.ID)
        }
        AbstractDungeon.onModifyPower()
        isDone = true
    }
}
