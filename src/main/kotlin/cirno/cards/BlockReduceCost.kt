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
import com.megacrit.cardcrawl.actions.unique.MadnessAction


class BlockReduceCost : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, 0), Helper, Shiver {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        block()
        if (shivered) {
            for (card in hand.group) {
                if (card.cost > 0) {
                    card.cost = 0
                    card.costForTurn = 0
                    card.isCostModified = true
                    card.isCostModifiedForTurn = true
                }
            }
            exhaust = true
        }
    }

    override fun onShiver() {
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(BlockReduceCost::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 2
        private val MAGIC_UP = 0
        private val DAMAGE = 0
        private val BLOCK = 3
        private val MAGIC = 0
    }
}
