package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.powers.DealDamageOnDraw


class AttackDrawDamage : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DRAW_DMG_UP, COST), Helper {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = DRAW_DMG
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
        power(DealDamageOnDraw(m, magicNumber))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(AttackDrawDamage::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 2
        private val BLOCK_UP = 0
        private val DRAW_DMG_UP = 1
        private val DAMAGE = 6
        private val BLOCK = 0
        private val DRAW_DMG = 1
    }
}
