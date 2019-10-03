package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import Piruru.actions.NotShittyChangeStanceAction
import Piruru.stances.ACRO
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class EnterACRO : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST_UP) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        act(NotShittyChangeStanceAction(ACRO()))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(EnterACRO::class.java.simpleName))
        private val COST = 2
        private val TYPE = AbstractCard.CardType.POWER
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val COST_UP = 2
        private val DAMAGE = 0
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
