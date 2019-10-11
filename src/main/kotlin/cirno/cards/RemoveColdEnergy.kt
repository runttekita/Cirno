package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.powers.Cold
import com.megacrit.cardcrawl.actions.common.GainEnergyAction
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction


class RemoveColdEnergy : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, ENERGY_UP, COST), Helper {
    var energyString = ""

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = ENERGY
        baseMagicNumber = magicNumber
        for (i in 0 until magicNumber) {
            energyString += "[E] "
        }
        rawDescription = "${cardStrings.EXTENDED_DESCRIPTION[0]}$energyString${cardStrings.EXTENDED_DESCRIPTION[1]}"
        initializeDescription()
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        loopOverMonsters { m.run {
            m?.getPower(makeID(Cold::class.java))?.run {
                act(GainEnergyAction(this.amount))
                act(RemoveSpecificPowerAction(this.owner, p, this))
            }
        }}
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            energyString = ""
            upgradeMagicNumber(ENERGY_UP)
            for (i in 0 until magicNumber) {
                energyString += "[E] "
            }
            rawDescription = "${cardStrings.EXTENDED_DESCRIPTION[0]}$energyString${cardStrings.EXTENDED_DESCRIPTION[1]}"
            initializeDescription()
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(RemoveColdEnergy::class.java.simpleName))
        private val COST = 0
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ALL_ENEMY
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val ENERGY_UP = 1
        private val DAMAGE = 0
        private val BLOCK = 0
        private val ENERGY = 2
    }
}
