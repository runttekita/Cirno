package Piruru.powers

import Piruru.Piruluk
import Piruru.abstracts.PiruruPower
import Piruru.interfaces.OnRecover
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings

class RecoverDamageP(amount: Int) : PiruruPower(), OnRecover {

    companion object {
        val POWER_ID : String = Piruluk.makeID(this::class.java.simpleName)
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
