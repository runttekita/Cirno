package cirno.cards

import basemod.helpers.BaseModCardTags
import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.powers.AttackBackFreeze
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class GlacialForm : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, TURNS_UP, COST) {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = TURNS
        baseMagicNumber = magicNumber
        tags.add(BaseModCardTags.FORM)
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(ApplyPowerAction(p, p, AttackBackFreeze(p, magicNumber)))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(GlacialForm::class.java.simpleName))
        private val COST = 3
        private val TYPE = CardType.POWER
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val TURNS_UP = 1
        private val DAMAGE = 0
        private val BLOCK = 0
        private val TURNS = 3
    }
}
