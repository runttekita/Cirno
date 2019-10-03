package Piruru.powers

import Piruru.abstracts.PiruruPower
import Piruru.interfaces.OnRefreshHand
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.powers.NoDrawPower
import com.megacrit.cardcrawl.rooms.AbstractRoom

class NoCardsBlockP(owner: AbstractCreature, amount: Int) : PiruruPower(), OnRefreshHand {

    init {
        this.owner = owner
        this.amount = amount
    }

    override fun updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]
    }

    override fun onRefreshHand() {
        if (AbstractDungeon.player.hand.isEmpty && !AbstractDungeon.actionManager.turnHasEnded && !AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID) && !AbstractDungeon.isScreenUp && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && (AbstractDungeon.player.discardPile.size() > 0 || AbstractDungeon.player.drawPile.size() > 0)) {
            this.flash()
            AbstractDungeon.actionManager.addToBottom(GainBlockAction(owner, amount))
        }
    }
}