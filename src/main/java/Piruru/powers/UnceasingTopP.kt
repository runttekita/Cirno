package Piruru.powers

import Piruru.interfaces.OnRefreshHand
import Piruru.abstracts.PiruruPower
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.NoDrawPower
import com.megacrit.cardcrawl.rooms.AbstractRoom

class UnceasingTopP(amount: Int) : PiruruPower(), OnRefreshHand {

    init {
        this.amount = amount
        this.owner = AbstractDungeon.player
    }

    override fun updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2]
        }
    }

    override fun onRefreshHand() {
        if (AbstractDungeon.player.hand.isEmpty && !AbstractDungeon.actionManager.turnHasEnded && !AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID) && !AbstractDungeon.isScreenUp && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && (AbstractDungeon.player.discardPile.size() > 0 || AbstractDungeon.player.drawPile.size() > 0)) {
            this.flash()
            AbstractDungeon.actionManager.addToBottom(DrawCardAction(owner, amount))
        }
    }
}