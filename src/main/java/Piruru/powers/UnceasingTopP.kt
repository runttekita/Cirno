package Piruru.powers

import Piruru.Piruluk
import Piruru.interfaces.OnRefreshHand
import Piruru.abstracts.PiruruPower
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.NoDrawPower
import com.megacrit.cardcrawl.rooms.AbstractRoom

class UnceasingTopP(amount: Int) : PiruruPower(), OnRefreshHand {

    companion object {
        val POWER_ID : String = Piruluk.makeID(this.javaClass.simpleName)
        internal var powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        var NAME: String? = null
        var DESCRIPTION: String? = null
    }

    init {
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        ID = POWER_ID
        updateDescription()
        this.amount = amount
        this.owner = AbstractDungeon.player
        img = Piruluk.textureLoader.getTexture(Piruluk.makePowerPath(this.javaClass.simpleName))
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
