package cirno.powers

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoPower
import cirno.interfaces.Helper
import com.megacrit.cardcrawl.actions.utility.UseCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardQueueItem
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster

class ColdEchoFormP(amount: Int) : CirnoPower(), Helper {
    override val defaultSource: AbstractCreature
        get() = owner

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
        this.owner = player
        this.amount = amount
    }

    override fun onUseCard(card: AbstractCard, action: UseCardAction) {
        if (!card.purgeOnUse && this.amount > 0 && action.target.hasPower(makeID(Cold::class.java))) {
            for (i in 0 until amount) {
                flash()
                var m: AbstractMonster? = null
                if (action.target != null) {
                    m = action.target as AbstractMonster
                }

                val tmp = card.makeSameInstanceOf()
                limbo.addToBottom(tmp)
                tmp.current_x = card.current_x
                tmp.current_y = card.current_y
                tmp.target_x = Settings.WIDTH.toFloat() / 2.0f - 300.0f * Settings.scale
                tmp.target_y = Settings.HEIGHT.toFloat() / 2.0f
                if (tmp.cost > 0) {
                    tmp.freeToPlayOnce = true
                }
                if (m != null) {
                    tmp.calculateCardDamage(m)
                }

                tmp.purgeOnUse = true
                AbstractDungeon.actionManager.cardQueue.add(CardQueueItem(tmp, m, card.energyOnUse, true))
            }
        }
    }

    override fun updateDescription() {
        description = when {
            amount == 1 -> "${DESCRIPTIONS!![0]}$amount${DESCRIPTIONS!![1]}"
            else -> "${DESCRIPTIONS!![0]}$amount${DESCRIPTIONS!![2]}"
        }
    }
}