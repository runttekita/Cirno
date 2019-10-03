package Piruru.abstracts

import Piruru.Piruluk
import Piruru.Piruluk.Statics.makeID
import Piruru.characters.PiruruChar
import Piruru.interfaces.NotShittyTookDamage
import Piruru.powers.Cold
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
import javassist.CtBehavior

abstract class PiruruCard
(private val strings: CardStrings, cost: Int, type: AbstractCard.CardType, rarity: AbstractCard.CardRarity, target: AbstractCard.CardTarget, private val upgradeDamage: Int,
                          private val upgradeBlock: Int, private val upgradeMagic: Int, private val upgradeCost: Int)
    : CustomCard(null, strings.NAME, getImg("Piruru:uwu"), cost, strings.DESCRIPTION, type, PiruruChar.PIRURU_ICE, rarity, target),
        NotShittyTookDamage {

    init {
        cardID = makeID(this.javaClass.simpleName)
    }

    fun makeName(): String {
        return Piruluk.makeID(this.javaClass.simpleName)
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
        if (hasTag(Enums.ARTS) && AbstractDungeon.player.hand.contains(this)) {
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

    object Enums {
        @SpireEnum
        var ARTS: AbstractCard.CardTags? = null
    }

    @SpirePatch(clz = AbstractCard::class, method = "hasEnoughEnergy")
    object PlayARTSOnEnemyTurnPatch {
        @SpireInsertPatch(locator = Locator::class)
        fun Insert(__instance: AbstractCard): SpireReturn<*> {
            return if (__instance is PiruruCard && __instance.hasTag(Enums.ARTS)) {
                SpireReturn.Return(true)
            } else SpireReturn.Continue<Any>()
        }
    }

    class Locator : SpireInsertLocator() {

        @Throws(Exception::class)
        override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
            val finalMatcher = Matcher.FieldAccessMatcher(AbstractCard::class.java, "cantUseMessage")
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher)
        }
    }
}

fun getImg(id: String): String {
    val imgName = id.substring(id.indexOf(":") + 1).trim { it <= ' ' }
    return Piruluk.makeCardPath("betaart.png")
}

