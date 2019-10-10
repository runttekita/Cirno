package cirno.powers

import basemod.interfaces.CloneablePowerInterface
import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoPower
import cirno.actions.FreezeMonsterAction
import cirno.interfaces.Helper
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.AbstractPower

class AttackBackFreeze(private val target: AbstractCreature, private val turns: Int) : CirnoPower(), Helper, CloneablePowerInterface {

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
        owner = target
        amount = turns
    }

    override fun onAttacked(info: DamageInfo, damageAmount: Int): Int {
        if (info.owner is AbstractMonster) {
            act(ApplyPowerAction(info.owner, AbstractDungeon.player, FreezeNextTurn(info.owner, 1), 1))
        }
        return damageAmount
    }

    override fun atStartOfTurn() {
        act(ReducePowerAction(owner, owner, this, 1))
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0]
    }

    override fun makeCopy(): AbstractPower {
        return AttackBackFreeze(owner, amount)
    }

}