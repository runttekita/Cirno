package cirno.interfaces

import cirno.cards.Defend
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower

interface Helper {
    val player: AbstractPlayer
        get() = AbstractDungeon.player
    val drawPile: CardGroup
        get() = player.drawPile
    val discardPile: CardGroup
        get() = player.discardPile
    val hand: CardGroup
        get() = player.hand
    val exhaustPile: CardGroup
        get() = player.exhaustPile

    fun act(a: AbstractGameAction) {
        AbstractDungeon.actionManager.addToBottom(a)
    }

    fun power(power: AbstractPower) {
        act(ApplyPowerAction(power.owner, player, power))
    }

    fun block(creature: AbstractCreature, amt: Int) {
        act(GainBlockAction(creature, creature, amt))
    }

    fun loopOverAllPiles(callback: (ArrayList<AbstractCard>) -> Unit) {
        callback(drawPile.group)
        callback(discardPile.group)
        callback(hand.group)
        callback(exhaustPile.group)
    }

}