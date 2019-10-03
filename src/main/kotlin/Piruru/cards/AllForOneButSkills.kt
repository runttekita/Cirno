package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import Piruru.actions.AllForSkills
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class AllForOneButSkills : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST) {
    init {
        isEthereal = true
        exhaust = true
    }

    override fun upgrade() {
        if (!upgraded) {
            upgradeName()
            rawDescription = cardStrings.UPGRADE_DESCRIPTION
            initializeDescription()
        }
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(AllForSkills(upgraded))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(AllForOneButSkills::class.java))
        private val COST = 3
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.SELF
    }
}
