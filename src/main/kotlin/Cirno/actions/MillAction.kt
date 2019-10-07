package Cirno.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect


class MillAction(private var millAmount: Int) : AbstractGameAction() {
    private var callback: ((AbstractCard) -> Unit)? = null

    constructor(amount: Int, callback: ((AbstractCard) -> Unit)?) : this(amount) {
        this.callback = callback
    }

    override fun update() {
        if (AbstractDungeon.player.drawPile.size() == 0) {
            isDone = true
            return
        }
        val c = AbstractDungeon.player.drawPile.topCard
        AbstractDungeon.player.drawPile.removeCard(c)
        AbstractDungeon.player.discardPile.removeCard(c)
        AbstractDungeon.effectsQueue.add(ShowCardAndAddToDiscardEffect(c))
        millAmount--
        if (millAmount > 0) {
            if (callback != null) {
                callback!!(c)
                AbstractDungeon.actionManager.addToBottom(MillAction(millAmount, callback))
                isDone = true
                return
            }
            AbstractDungeon.actionManager.addToBottom(MillAction(millAmount))
        }
        isDone = true
    }
}
