package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.interfaces.OnApplyCold
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class ColdScalingAttack : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DMG_BONUS_UP, COST), OnApplyCold {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = DMG_BONUS
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
    }

    override fun onApplyCold(m: AbstractMonster) {
        baseDamage += magicNumber
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ColdScalingAttack::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 3
        private val BLOCK_UP = 0
        private val DMG_BONUS_UP = 3
        private val DAMAGE = 6
        private val BLOCK = 0
        private val DMG_BONUS = 4
    }
}
