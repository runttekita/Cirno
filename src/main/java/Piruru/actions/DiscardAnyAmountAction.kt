package Piruru.actions

import Piruru.Piruluk.Statics.makeID
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.UIStrings

import java.util.ArrayList
import java.util.function.Consumer


class DiscardAnyAmountAction(private val callback: (ArrayList<AbstractCard>) -> Unit?) : AbstractGameAction() {

    init {
        duration = Settings.ACTION_DUR_FASTER
    }

    override fun update() {
        if (duration == Settings.ACTION_DUR_FASTER) {
            val uiString = CardCrawlGame.languagePack.getUIString(makeID(DiscardAnyAmountAction::class.java))
            val TEXT = uiString.TEXT
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 999, true, true)
            AbstractDungeon.player.hand.applyPowers()
            this.tickDuration()
            return
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            callback(AbstractDungeon.handCardSelectScreen.selectedCards.group)
            AbstractDungeon.handCardSelectScreen.selectedCards.clear()
        }
        isDone = true
    }
}
