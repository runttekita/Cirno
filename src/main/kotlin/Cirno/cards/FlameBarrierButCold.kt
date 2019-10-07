package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard
import Cirno.powers.FlameBarrierButColdP
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class FlameBarrierButCold : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, BLOCK_UP, 0, COST) {
    init {
        block = BLOCK
        baseBlock = block
        baseMagicNumber = COLD
        magicNumber = baseMagicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        block()
        act(power(p, p, FlameBarrierButColdP(p, magicNumber), magicNumber))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(FlameBarrierButCold::class.java))
        private val COST = 2
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val BLOCK = 12
        private val BLOCK_UP = 4
        private val COLD = 1
    }
}
