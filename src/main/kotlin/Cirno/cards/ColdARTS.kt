package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard
import Cirno.abstracts.CirnoCard.Enums.Enums.ARTS
import Cirno.powers.Cold
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class ColdARTS : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, COLD_UP, COST_UP) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = COLD
        baseMagicNumber = magicNumber
        exhaust = true
        tags.add(ARTS)
        AlwaysRetainField.alwaysRetain.set(this, true)
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(ApplyPowerAction(m, p, Cold(magicNumber, m!!), magicNumber))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ColdARTS::class.java.simpleName))
        private val COST = 2
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.SPECIAL
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val COLD_UP = 0
        private val DAMAGE = 0
        private val BLOCK = 0
        private val COLD = 2
        private val COST_UP = 1
    }
}
