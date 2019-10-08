package cirno.powers

import cirno.abstracts.CirnoPower
import cirno.actions.FreezeMonsterAction
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

class AttackBackFreeze(private val target: AbstractCreature, private val times: Int) : CirnoPower() {

    init {
        amount = times
        owner = target
    }

    override fun onAttacked(info: DamageInfo, damageAmount: Int): Int {
        if (info.owner is AbstractMonster) {
            AbstractDungeon.actionManager.addToBottom(FreezeMonsterAction(info.owner as AbstractMonster, AbstractDungeon.player))
            AbstractDungeon.actionManager.addToBottom(ReducePowerAction(info.owner, info.owner, this, 1))
        }
        return damageAmount
    }

    override fun updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]
    }
}