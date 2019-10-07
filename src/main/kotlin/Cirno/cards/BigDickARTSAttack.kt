package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard
import Cirno.abstracts.CirnoCard.Enums.Enums.ARTS
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class BigDickARTSAttack : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST) {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
        tags.add(ARTS);
        AlwaysRetainField.alwaysRetain.set(this, true)
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!);
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(BigDickARTSAttack::class.java.simpleName))
        private val COST = 3
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 10
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 45
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
