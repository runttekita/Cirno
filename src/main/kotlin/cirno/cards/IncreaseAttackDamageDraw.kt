package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import com.megacrit.cardcrawl.actions.common.DrawCardAction


class IncreaseAttackDamageDraw : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DRAW_UP, 0), Helper {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = DRAW
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        var attacksDrawn = 0
        act(DrawCardAction(p, magicNumber))
        for (i in 0 until magicNumber) {
            if (drawPile.getNCardFromTop(i).type == CardType.ATTACK) {
                attacksDrawn++
            }
        }
        if (attacksDrawn == damage) {
            loopOverAllPiles { list ->
                list.forEach {
                    if (it.type == CardType.ATTACK) {
                        it.baseDamage += block
                    }
                }
            }
        }
    }

    override fun applyPowers() {

    }

    override fun calculateCardDamage(mo: AbstractMonster?) {

    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(IncreaseAttackDamageDraw::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val DRAW_UP = 0
        private val DAMAGE = 2
        private val BLOCK = 4
        private val DRAW = 2
    }
}
