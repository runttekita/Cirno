package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.interfaces.OnFreezeSpell
import com.megacrit.cardcrawl.actions.common.GainEnergyAction
import com.megacrit.cardcrawl.core.AbstractCreature


class SpellEnergyFreeze : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, ENERGY_UP, COST), Helper, OnFreezeSpell {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = ENERGY
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {

    }

    override fun spellEffect(m: AbstractMonster) {
        act(GainEnergyAction(magicNumber))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(SpellEnergyFreeze::class.java.simpleName))
        private val COST = 0
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val ENERGY_UP = 1
        private val DAMAGE = 0
        private val BLOCK = 0
        private val ENERGY = 2
    }
}
