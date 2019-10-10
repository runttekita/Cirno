package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import cirno.interfaces.Helper
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.actions.utility.UseCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings

class PutBackCards : CirnoPower(), Helper {
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
        owner = AbstractDungeon.player
        amount = 1
        updateDescription()
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
    }

    override fun onUseCard(card: AbstractCard, action: UseCardAction) {
        hand.moveToBottomOfDeck(hand.getRandomCard(true))
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        act(ReducePowerAction(owner, owner, this, 1))
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0]
    }
}