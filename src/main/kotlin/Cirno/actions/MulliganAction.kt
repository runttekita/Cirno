package Cirno.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class MulliganAction(private val mulliganAmount: Int) : AbstractGameAction() {

    override fun update() {
        for (i in 0 until mulliganAmount) {
            AbstractDungeon.actionManager.addToBottom(DrawCardAction(1))
        }
        AbstractDungeon.actionManager.addToBottom(DrawCardAction(1))
        isDone = true
    }
}
