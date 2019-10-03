package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import basemod.helpers.BaseModCardTags
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class Defend : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, BLOCK_UP, 0, COST) {
    init {
        block = BLOCK
        baseBlock = block
        tags.add(BaseModCardTags.BASIC_DEFEND)
    }

    override fun use(abstractPlayer: AbstractPlayer, abstractMonster: AbstractMonster) {
        block()
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(Defend::class.java))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.BASIC
        private val TARGET = AbstractCard.CardTarget.SELF
        private val BLOCK = 5
        private val BLOCK_UP = 3
    }
}
