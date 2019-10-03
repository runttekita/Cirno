package Piruru.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class DrawPileToHandAction(private val c: AbstractCard) : AbstractGameAction() {

    override fun update() {
        AbstractDungeon.player.drawPile.moveToHand(c,
                AbstractDungeon.player.hand)
        isDone = true
    }
}
