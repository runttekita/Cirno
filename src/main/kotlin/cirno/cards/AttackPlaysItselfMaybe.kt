package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.powers.PlayCardNextTurnPower


class AttackPlaysItselfMaybe : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DRAW_UP, COST), Helper {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = DRAW
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        var attacksDrawn = 0
        draw({c ->
            run {
                if (c.type == CardType.ATTACK) {
                    attacksDrawn++
                    if (attacksDrawn == magicNumber) {
                        power(PlayCardNextTurnPower(this))
                    }
                }
            }
        })
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(AttackPlaysItselfMaybe::class.java.simpleName))
        private val COST = 2
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 3
        private val BLOCK_UP = 0
        private val DRAW_UP = 0
        private val DAMAGE = 16
        private val BLOCK = 0
        private val DRAW = 2
    }
}
