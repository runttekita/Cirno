package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.PiruruCard
import Cirno.actions.NotShittyChangeStanceAction
import Cirno.stances.ApexForm
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class EnterApexForm : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST_UP) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(NotShittyChangeStanceAction(ApexForm()))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(EnterApexForm::class.java.simpleName))
        private val COST = 3
        private val TYPE = AbstractCard.CardType.POWER
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val MAGIC_UP = 2
        private val COST_UP = 2
        private val DAMAGE = 0
        private val BLOCK = 0
        private val MAGIC = 1
    }
}
