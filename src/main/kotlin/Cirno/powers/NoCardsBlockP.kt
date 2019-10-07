package Cirno.powers

import Cirno.Cirno
import Cirno.abstracts.CirnoPower
import Cirno.interfaces.OnRefreshHand
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.powers.NoDrawPower
import com.megacrit.cardcrawl.rooms.AbstractRoom

class NoCardsBlockP(owner: AbstractCreature, amount: Int) : CirnoPower(), OnRefreshHand {

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
    }

    init {
        val POWER_ID : String = Cirno.makeID(this::class.java.simpleName)
        val powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        ID = POWER_ID
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        updateDescription()
        this.owner = owner
        this.amount = amount
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))

    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0] + amount + DESCRIPTIONS!![1]
    }

    override fun onRefreshHand() {
        if (AbstractDungeon.player.hand.isEmpty && !AbstractDungeon.actionManager.turnHasEnded && !AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID) && !AbstractDungeon.isScreenUp && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && (AbstractDungeon.player.discardPile.size() > 0 || AbstractDungeon.player.drawPile.size() > 0)) {
            this.flash()
            AbstractDungeon.actionManager.addToBottom(GainBlockAction(owner, amount))
        }
    }
}
