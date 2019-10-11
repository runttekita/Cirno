package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.interfaces.Shiver
import cirno.interfaces.shivered


class ShiverAttackCold : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, COLD_UP, COST), Helper, Shiver {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = COLD
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
        if (shivered) {
            damage(m)
            cold(m)
        }
    }

    override fun onShiver() {
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ShiverAttackCold::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 2
        private val BLOCK_UP = 0
        private val COLD_UP = 0
        private val DAMAGE = 6
        private val BLOCK = 0
        private val COLD = 1
    }
}
