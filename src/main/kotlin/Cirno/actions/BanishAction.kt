package Cirno.actions

import Cirno.Cirno.Statics.makeID
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.vfx.SpeechBubble

import java.util.ArrayList


class BanishAction(private var banishAmount: Int) : AbstractGameAction() {
    private var callback: ((ArrayList<AbstractCard>) -> Unit)? = null
    private var anyAmount = false
    private var predicate: ((AbstractCard) -> Boolean)? = null

    constructor(banishAmount: Int, callback: (ArrayList<AbstractCard>) -> Unit) : this(banishAmount) {
        this.callback = callback
    }

    init {
        duration = Settings.ACTION_DUR_FASTER
    }

    constructor(banishAmount: Int, callback: (ArrayList<AbstractCard>) -> Unit, anyAmount: Boolean) : this(banishAmount, callback) {
        this.anyAmount = anyAmount
    }

    constructor(banishAmount: Int, predicate: (AbstractCard) -> Boolean, callback: (ArrayList<AbstractCard>) -> Unit) : this(banishAmount) {
        this.predicate = predicate
        this.callback = callback
    }

    override fun update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            if (AbstractDungeon.player.discardPile.isEmpty) {
                isDone = true
                return
            }
            if (AbstractDungeon.player.discardPile.size() < banishAmount) {
                AbstractDungeon.effectList.add(SpeechBubble(
                        AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.5f,
                        uiStrings.TEXT[0], true))
                isDone = true
                return
            }

            if (predicate != null) {
                val tmp = CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (c in AbstractDungeon.player.discardPile.group) {
                    if(predicate!!(c)) {
                        tmp.addToBottom(c)
                    }
                }
                if (tmp.size() < banishAmount) {
                    AbstractDungeon.effectList.add(SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.5f,
                            uiStrings.TEXT[0], true))
                    tickDuration()
                    return
                }
                AbstractDungeon.gridSelectScreen.open(tmp, banishAmount, "", false)
                tickDuration()
                return
            }

            if (anyAmount) {
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.discardPile, banishAmount, true, "")
                tickDuration()
                return
            }
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.discardPile, banishAmount, "", false)
            tickDuration()
            return
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.isNotEmpty()) {
            if (callback != null) callback!!(AbstractDungeon.gridSelectScreen.selectedCards)
            for (c in AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.actionManager.addToBottom(ExhaustSpecificCardAction(
                        c, AbstractDungeon.player.discardPile
                ))
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear()
        }
        isDone = true
    }

    companion object {
        private val uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BanishAction::class.java))
    }
}
