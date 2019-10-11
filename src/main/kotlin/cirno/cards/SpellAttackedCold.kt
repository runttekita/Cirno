package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.interfaces.NotShittyOnAttackedSpell
import cirno.powers.Cold
import com.megacrit.cardcrawl.cards.DamageInfo


class SpellAttackedCold : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST), Helper, NotShittyOnAttackedSpell {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {

    }

    override fun notShittyOnAttacked(info: DamageInfo) {
    }

    override fun spellEffect(info: DamageInfo) {
        info.owner.getPower(makeID(Cold::class.java))?.amount?.let {
            for (i in 0 until it) {
                damage(info.owner)
            }
            power(Cold(info.owner))
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(SpellAttackedCold::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 2
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 5
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
