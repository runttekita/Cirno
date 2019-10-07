package Cirno.abstracts

import Cirno.Cirno
import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard.Enums.Enums.ARTS
import Cirno.characters.CirnoChar.Enums.enums.Cirno_Ice
import Cirno.interfaces.NotShittyTookDamage
import Cirno.powers.Cold
import basemod.abstracts.CustomCard
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
    : CustomCard(null, strings.NAME, getImg("Cirno:uwu"), cost, strings.DESCRIPTION, type, Cirno_Ice, rarity, target),
        NotShittyTookDamage {

    init {
        cardID = makeID(this.javaClass.simpleName)
    }

    fun makeName(): String {
        return Cirno.makeID(this.javaClass.simpleName)
    }

    fun act(a: AbstractGameAction) {
        AbstractDungeon.actionManager.addToBottom(a)
    }

    private fun dmgInfo(): DamageInfo {
        return DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL)
    }

    fun damage(m: AbstractCreature) {
        act(DamageAction(m, dmgInfo()))
    }

    fun block() {
        act(GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block))
    }

    fun cold(m: AbstractMonster) {
        act(ApplyPowerAction(m, AbstractDungeon.player, Cold(m), 1))
    }

    fun power(target: AbstractCreature, source: AbstractCreature, p: AbstractPower, amount: Int): ApplyPowerAction {
        return ApplyPowerAction(target, source, p, amount)
    }

    override fun notShittyTookDamage(i: DamageInfo) {
        if (hasTag(ARTS) && AbstractDungeon.player.hand.contains(this)) {
            if (i.owner is AbstractMonster) {
                act(QueueCardAction(this, i.owner as AbstractMonster))
            } else {
                act(QueueCardAction(this, AbstractDungeon.getRandomMonster()))
            }
        }
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            upgradeDamage()
            upgradeBlock()
            upgradeMagic()
            upgradeCost()
            if (strings.UPGRADE_DESCRIPTION != null) {
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

    public class Enums {
        object Enums {
            @SpireEnum
            @JvmStatic
            var ARTS: AbstractCard.CardTags? = null
        }
    }

}

fun getImg(id: String): String {
    val imgName = id.substring(id.indexOf(":") + 1).trim { it <= ' ' }
    return Cirno.makeCardPath("betaart.png")
}

