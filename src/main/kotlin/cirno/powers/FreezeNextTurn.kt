package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import cirno.actions.FreezeMonsterAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster

class FreezeNextTurn(target: AbstractCreature, turns: Int) : CirnoPower() {

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
        private const val PROC_AMOUNT = 3
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
        owner = target
        amount = turns
    }

    override fun atStartOfTurnPostDraw() {
        AbstractDungeon.actionManager.addToBottom(FreezeMonsterAction(owner as AbstractMonster, owner, 1))
        AbstractDungeon.actionManager.addToBottom(ReducePowerAction(owner, owner, this, 1))
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0]
    }

}