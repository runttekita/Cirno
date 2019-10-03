package Piruru.powers

import Piruru.abstracts.PiruruPower
import Piruru.interfaces.OnRecover
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class RecoverDamageP(amount: Int) : PiruruPower(), OnRecover {

    init {
        this.owner = AbstractDungeon.player
        this.amount = amount
        updateDescription()
    }

    override fun onRecover(c: AbstractCard) {
        AbstractDungeon.actionManager.addToBottom(DamageRandomEnemyAction(
                DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE
        ))
    }

    override fun updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]
    }
}
