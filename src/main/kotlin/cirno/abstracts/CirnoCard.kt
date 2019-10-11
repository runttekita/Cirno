package cirno.abstracts

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.characters.CirnoChar.Enums.enums.Cirno_Ice
import cirno.powers.Cold
import basemod.abstracts.CustomCard
import cirno.characters.spellZones
import cirno.interfaces.*
import cirno.powers.ColdEchoFormP
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.actions.common.GainBlockAction
import com.megacrit.cardcrawl.actions.utility.QueueCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.powers.AbstractPower

abstract class CirnoCard
(private val strings: CardStrings, cost: Int, type: AbstractCard.CardType, rarity: AbstractCard.CardRarity,
 target: AbstractCard.CardTarget, private val upgradeDamage: Int, private val upgradeBlock: Int,
 private val upgradeMagic: Int, private val upgradeCost: Int)
    : CustomCard(null, strings.NAME, getImg("Cirno:uwu"), cost, strings.DESCRIPTION, type, Cirno_Ice, rarity, target), Helper {
    override val defaultDrawAmount: Int
        get() = this.magicNumber
    override val defaultSource: AbstractCreature
        get() = player
    override val defaultBlock: Int
        get() = this.block
    override val defaultDamage: Int
        get() = this.damage
    var cirnoDynamicNumber: Int = -1
    var isDynamicUpgraded = false
    var originalCost = 0

    init {
        cardID = makeID(this.javaClass.simpleName)
    }

    fun makeName(): String {
        return Cirno.makeID(this.javaClass.simpleName)
    }

    fun cold(m: AbstractMonster) {
        act(ApplyPowerAction(m, AbstractDungeon.player, Cold(m), 1))
    }

    fun addSpell() {
        AbstractDungeon.player.spellZones.setSpell(this)
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage()
            upgradeBlock()
            upgradeMagic()
            upgradeCost()
            strings.UPGRADE_DESCRIPTION?.run {
                rawDescription = strings.UPGRADE_DESCRIPTION
                initializeDescription()
            }
        }
    }

    private fun upgradeDamage() {
        if (upgradeDamage != 0) {
            baseDamage += upgradeDamage
            upgradedDamage = true
        }
    }

    private fun upgradeBlock() {
        if (upgradeBlock != 0) {
            baseBlock += upgradeBlock
            upgradedBlock = true
        }
    }

    private fun upgradeMagic() {
        if (upgradeMagic != 0) {
            baseMagicNumber += upgradeMagic
            upgradedMagicNumber = true
        }
    }

    private fun upgradeCost() {
        if (upgradeCost != cost) {
            val diff = costForTurn - cost
            cost = upgradeCost
            if (costForTurn > 0) {
                costForTurn = cost + diff
            }

            if (costForTurn < 0) {
                costForTurn = 0
            }
            upgradedCost = true
        }
    }

    override fun calculateCardDamage(mo: AbstractMonster?) {
        super.calculateCardDamage(mo)
        if (costForTurn != 0) {
            originalCost = costForTurn
        }
        if (player.hasPower(makeID(ColdEchoFormP::class.java)) && mo!!.hasPower(makeID(Cold::class.java))) {
            costForTurn = 0
        } else {
            costForTurn = originalCost
        }
    }

    open fun spellEffect() {
    }

    open fun spellEffect(info: DamageInfo) {

    }

    open fun spellEffect(m: AbstractMonster) {

    }

    override fun triggerOnGlowCheck() {
        if (this is Shiver) {
            glowColor = if (shivered) {
                AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
            } else {
                AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy()
            }
        }
    }


}

fun getImg(id: String): String {
    val imgName = id.substring(id.indexOf(":") + 1).trim { it <= ' ' }
    return Cirno.makeCardPath("betaart.png")
}
