package Piruru.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class FastDrawPileToDiscardPileAction(private val c: AbstractCard) : AbstractGameAction() {

    override fun update() {
        AbstractDungeon.player.drawPile.moveToDiscardPile(c)
        isDone = true
    }
}