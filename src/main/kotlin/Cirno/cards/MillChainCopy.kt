package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.PiruruCard
import Cirno.actions.MillAction
import Cirno.powers.Chain
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class MillChainCopy : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MILL_UP, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = MILL
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
        act(MillAction(magicNumber))
        act(MakeTempCardInDrawPileAction(MillChainCopy(), 1, true, true))
        act(ApplyPowerAction(p, p, Chain(p, 1), 1))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(MillChainCopy::class.java.simpleName))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 3
        private val BLOCK_UP = 0
        private val MILL_UP = 2
        private val DAMAGE = 8
        private val BLOCK = 0
        private val MILL = 2
    }
}
