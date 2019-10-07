package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard
import Cirno.actions.FreezeMonsterAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class FreezeEnemy : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST_UP) {

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(FreezeMonsterAction(m!!, p))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(FreezeEnemy::class.java))
        private val COST = 2
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val COST_UP = 1
    }
}
