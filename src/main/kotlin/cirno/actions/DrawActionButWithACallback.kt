package cirno.actions

import basemod.BaseMod
import com.badlogic.gdx.Gdx
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.SoulGroup
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.NoDrawPower

/**
 * Please help I'm getting obsessed
 */
class DrawActionButWithACallback(amount: Int, callback: ((AbstractCard) -> Unit)?) : AbstractGameAction() {
    private var shuffleCheck = false
    private var callback: ((AbstractCard) -> Unit)? = null

    init {
        this.callback = callback
        if (Settings.FAST_MODE) {
            this.duration = Settings.ACTION_DUR_XFAST
        } else {
            this.duration = Settings.ACTION_DUR_FASTER
        }
    }

    override fun update() {
        if (AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
            AbstractDungeon.player.getPower(NoDrawPower.POWER_ID).flash()
            isDone = true
            return
        }
        val deckSize = AbstractDungeon.player.drawPile.size()
        val discardSize = AbstractDungeon.player.discardPile.size()
        if (!SoulGroup.isActive()) {
            if (deckSize + discardSize == 0) {
                isDone = true
            } else if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.createHandIsFullDialog()
                isDone = true
            } else {
                if (!shuffleCheck) {
                    var tmp: Int
                    if (amount + AbstractDungeon.player.hand.size() > 10) {
                        tmp = 10 - (amount + AbstractDungeon.player.hand.size())
                        amount += tmp
                        AbstractDungeon.player.createHandIsFullDialog()
                    }

                    if (amount > deckSize) {
                        tmp = amount - deckSize
                        addToTop(DrawActionButWithACallback(tmp, callback))
                        addToTop(EmptyDeckShuffleAction())
                        if (deckSize != 0) {
                            addToTop(DrawActionButWithACallback(deckSize, callback))
                        }
                        amount = 0
                        isDone = true
                    }
                    shuffleCheck = true
                }
                duration -= Gdx.graphics.deltaTime
                if (amount != 0 && duration < 0.0f) {
                    if (Settings.FAST_MODE) {
                        duration = Settings.ACTION_DUR_XFAST
                    } else {
                        duration = Settings.ACTION_DUR_FASTER
                    }

                    --amount
                    if (!AbstractDungeon.player.drawPile.isEmpty) {
                        AbstractDungeon.player.draw()
                        AbstractDungeon.player.hand.refreshHandLayout()
                        callback!!(AbstractDungeon.player.drawPile.topCard)
                    } else {
                        isDone = true
                    }
                    if (amount == 0) {
                        isDone = true
                    }
                }
            }
        }
    }
}
