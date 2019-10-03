package Piruru.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

/**
 * @see com.megacrit.cardcrawl.actions.defect.AllCostToHandAction
 */
class AllForSkills(private val upgraded: Boolean) : AbstractGameAction() {

    override fun update() {
        if (AbstractDungeon.player.discardPile.size() > 0) {
            for (c in AbstractDungeon.player.discardPile.group) {
                if (c.type == AbstractCard.CardType.SKILL) {
                    if (upgraded) {
                        AbstractDungeon.actionManager.addToBottom(object : AbstractGameAction() {
                            override fun update() {
                                if (AbstractDungeon.player.discardPile.contains(c)) {
                                    AbstractDungeon.player.hand.addToHand(c)
                                    c.unhover()
                                    c.setAngle(0.0f)
                                    c.lighten(false)
                                    c.drawScale = 0.12f
                                    c.targetDrawScale = 0.75f
                                    c.applyPowers()
                                    AbstractDungeon.player.discardPile.removeCard(c)
                                }
                                AbstractDungeon.player.hand.refreshHandLayout()
                                AbstractDungeon.player.hand.glowCheck()
                                isDone = true
                            }
                        })
                    } else {
                        AbstractDungeon.actionManager.addToBottom(DiscardToHandAction(c))
                    }
                }
            }
        }
        isDone = true
    }

}
