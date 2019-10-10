package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import cirno.interfaces.Helper
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower
import com.megacrit.cardcrawl.actions.utility.QueueCardAction
import com.megacrit.cardcrawl.actions.utility.UnlimboAction
import com.megacrit.cardcrawl.actions.utility.UseCardAction
import com.megacrit.cardcrawl.actions.utility.WaitAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.localization.PowerStrings

class PlayCardNextTurnAction(private val card: AbstractCard) : CirnoPower(), Helper, NonStackablePower {

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
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
    }

    override fun updateDescription() {
        description = "${DESCRIPTIONS!![0]}$card${DESCRIPTIONS!![1]}"
    }

    override fun atStartOfTurn() {
        card.freeToPlayOnce = true
        limbo.addToBottom(card)
        val target = randomMonster()
        card.applyPowers()
        act(QueueCardAction(card, target))
        act(UnlimboAction(card))
        if (!Settings.FAST_MODE) {
            actButProbablyAHack(WaitAction(Settings.ACTION_DUR_MED));
        } else {
            actButProbablyAHack(WaitAction(Settings.ACTION_DUR_FASTER));
        }
    }

}