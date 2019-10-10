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
import cirno.interfaces.isShivering


class ShiverScaling : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, BONUS_UP, COST), Helper, Shiver {

    init {
        misc = DAMAGE
        baseDamage = misc
        baseBlock = BLOCK
        magicNumber = BONUS
        baseMagicNumber = magicNumber
        exhaust = true
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
        if (p.isShivering) {
            misc += magicNumber
        }
    }

    override fun applyPowers() {
        baseBlock = misc
        super.applyPowers()
        initializeDescription()
    }

    override fun triggerOnGlowCheck() {
        glowColor = if (player.isShivering) {
            AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
        } else {
            AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy()
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ShiverScaling::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val BONUS_UP = 2
        private val DAMAGE = 9
        private val BLOCK = 0
        private val BONUS = 3
    }
}
