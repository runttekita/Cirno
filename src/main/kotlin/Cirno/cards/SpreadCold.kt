package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class SpreadCold : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, 0, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        magicNumber = SPREAD_AMT
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
        cold(m)
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(SpreadCold::class.java))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE = 7
        private val DAMAGE_UP = 3
        private val SPREAD_AMT = 1
    }
}
