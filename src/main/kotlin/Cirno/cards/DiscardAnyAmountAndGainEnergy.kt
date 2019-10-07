package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard
import Cirno.actions.DiscardAnyAmountAction
import com.megacrit.cardcrawl.actions.common.GainEnergyAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class DiscardAnyAmountAndGainEnergy : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST_UP) {
    init {
        magicNumber = ENERGY_GAIN
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(DiscardAnyAmountAction { list -> list.forEach { c -> act(GainEnergyAction(magicNumber)) } })
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(DiscardAnyAmountAndGainEnergy::class.java.simpleName))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.SELF
        private val COST_UP = 0
        private val ENERGY_GAIN = 1
    }
}
