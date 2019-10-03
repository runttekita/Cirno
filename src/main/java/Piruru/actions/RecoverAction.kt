package Piruru.actions

import Piruru.interfaces.OnRecover
import basemod.BaseMod
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.AbstractPower

import java.util.ArrayList
import java.util.function.Consumer

/**
 * This is literally just Hologram's action.
 * Why?
 * I can't stand the fucking name.
 *
 * @see com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction
 * Also added a callback functionality I guess.
 */
class RecoverAction : AbstractGameAction {
    private var recoverAmount: Int = 0
    private var callback: ((ArrayList<AbstractCard>) -> Unit)? = null
    private val player: AbstractPlayer = AbstractDungeon.player
    private var card: AbstractCard? = null

    constructor(recoverAmount: Int) {
        this.recoverAmount = recoverAmount
        duration = Settings.ACTION_DUR_FASTER
    }

    constructor(recoverAmount: Int, callback: ((ArrayList<AbstractCard>) -> Unit)) : this(recoverAmount) {
        this.callback = callback
    }

    constructor(card: AbstractCard) {
        this.card = card
    }

    override fun update() {
        if (player.discardPile.isEmpty) {
            isDone = true
            return
        }
        if (card != null) {
            player.hand.addToHand(card)
            player.discardPile.removeCard(card)
        }
        if (duration == Settings.ACTION_DUR_FASTER) {
            if (player.discardPile.size() < recoverAmount && player.hand.size() + player.discardPile.size() <= BaseMod.MAX_HAND_SIZE) {
                if (callback != null) callback!!(player.discardPile.group)
                for (c in player.discardPile.group) {
                    if (player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                        player.hand.addToHand(c)
                        player.discardPile.removeCard(c)
                    }
                }
                player.hand.refreshHandLayout()
                isDone = true
                return
            }

            if (player.discardPile.size() < recoverAmount) {
                // TODO learn why this doesnt work with the last 2 params swapped later
                AbstractDungeon.gridSelectScreen.open(player.discardPile, player.discardPile.size(), "", false)
            }

            AbstractDungeon.gridSelectScreen.open(player.discardPile, recoverAmount, "", false)
            tickDuration()
            return
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.isNotEmpty()) {
            if (callback != null) callback!!(player.discardPile.group)
            for (c in AbstractDungeon.gridSelectScreen.selectedCards) {
                player.hand.addToHand(c)
                player.discardPile.removeCard(c)
                for (p in AbstractDungeon.player.powers) {
                    if (p is OnRecover) {
                        (p as OnRecover).onRecover(c)
                    }
                }
            }
            player.hand.refreshHandLayout()
            AbstractDungeon.gridSelectScreen.selectedCards.clear()
        }
        isDone = true
    }
}
