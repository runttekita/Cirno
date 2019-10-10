package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.characters.spellZones
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction
import com.megacrit.cardcrawl.cards.DamageInfo


class ClashForSpells : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST), Helper {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
        isMultiDamage = true
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        if (!p.spellZones.isEmpty()) {
            act(DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE, true))
        }

    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ClashForSpells::class.java.simpleName))
        private val COST = 0
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ALL_ENEMY
        private val DAMAGE_UP = 10
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 28
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
