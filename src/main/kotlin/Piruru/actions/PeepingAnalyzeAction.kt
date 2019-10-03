package Piruru.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class PeepingAnalyzeAction(private val cost: Int) : AbstractGameAction() {

    override fun update() {
        for (c in AbstractDungeon.player.drawPile.group) {
            if (c.cost == cost) {
                AbstractDungeon.actionManager.addToTop(FastDrawPileToDiscardPileAction(c))
            }
        }
        isDone = true
    }
}
