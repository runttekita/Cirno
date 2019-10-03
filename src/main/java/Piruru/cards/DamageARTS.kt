package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import Piruru.abstracts.PiruruCard.Enums.Enums.ARTS
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class DamageARTS : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
        AlwaysRetainField.alwaysRetain.set(this, true)
        tags.add(ARTS)
        exhaust = true
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(DamageARTS::class.java.simpleName))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.SPECIAL
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 4
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 10
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
