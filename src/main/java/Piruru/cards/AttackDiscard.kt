package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import com.megacrit.cardcrawl.actions.common.DiscardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class AttackDiscard : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DISCARD_UP, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = DISCARD
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        damage(m)
        act(DiscardAction(p, p, magicNumber, true))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(AttackDiscard::class.java.simpleName))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 3
        private val BLOCK_UP = 0
        private val DISCARD_UP = 1
        private val DAMAGE = 12
        private val BLOCK = 0
        private val DISCARD = 2
    }
}
