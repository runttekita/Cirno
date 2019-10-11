package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.actions.XCostAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon


class DrawXReduceX : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DRAW_UP, COST), Helper {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = DRAW
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        when {
            upgraded -> {
                act(XCostAction(energyOnUse) {
                    act(DrawCardAction(p, magicNumber))
                })
            }
            else -> {
                act(XCostAction(energyOnUse + 1) {
                    act(DrawCardAction(p, magicNumber))
                })
            }
        }
        val availableCards = ArrayList<AbstractCard>(hand.group)
        for (i in 0 until magicNumber) {
            val card = availableCards.get(AbstractDungeon.cardRng.random(availableCards.size - 1))
            card.costForTurn = 0
            availableCards.remove(card)
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(DrawXReduceX::class.java.simpleName))
        private val COST = -1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val DRAW_UP = 0
        private val DAMAGE = 0
        private val BLOCK = 0
        private val DRAW = 1
    }
}
