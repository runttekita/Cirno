package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import basemod.helpers.BaseModCardTags
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class Strike : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, 0, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        tags.add(BaseModCardTags.BASIC_STRIKE)
        tags.add(AbstractCard.CardTags.STRIKE)
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(Strike::class.java))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.BASIC
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE = 6
        private val DAMAGE_UP = 3
    }
}
