package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.PiruruCard
import Cirno.actions.ScoutCardsAction
import Cirno.actions.ScoutFollowUpAction
import com.megacrit.cardcrawl.actions.utility.WaitAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class ScoutAttacks : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, SCOUT_NUM_UP, COST) {
    init {
        magicNumber = SCOUT_NUM
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(ScoutCardsAction(p, magicNumber))
        act(WaitAction(0.4f))
        act(ScoutFollowUpAction())
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ScoutAttacks::class.java))
        private val COST = 0
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.BASIC
        private val TARGET = AbstractCard.CardTarget.SELF
        private val SCOUT_NUM = 3
        private val SCOUT_NUM_UP = 3
    }
}
