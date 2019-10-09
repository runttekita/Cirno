package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.actions.FreezeMonsterAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class MultiTurnFreeze : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, TURNS_UP, COST) {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = TURNS
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(FreezeMonsterAction(m!!, p, magicNumber))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(MultiTurnFreeze::class.java.simpleName))
        private val COST = 3
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val TURNS_UP = 1
        private val DAMAGE = 0
        private val BLOCK = 0
        private val TURNS = 2
    }
}
