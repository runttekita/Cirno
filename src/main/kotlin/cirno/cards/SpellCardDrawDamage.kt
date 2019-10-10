package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.interfaces.OnCardDrawSpell
import cirno.interfaces.shiveredCards
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction
import com.megacrit.cardcrawl.cards.DamageInfo


class SpellCardDrawDamage : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, TRIGGER_UP, COST), Helper, OnCardDrawSpell {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = TRIGGER
        baseMagicNumber = magicNumber
        isMultiDamage = true
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {

    }

    override fun onDraw() {
    }

    override fun spellEffect() {
        if (player.shiveredCards >= magicNumber) {
            act(DamageAllEnemiesAction(player, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE))
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(SpellCardDrawDamage::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 4
        private val BLOCK_UP = 0
        private val TRIGGER_UP = -2
        private val DAMAGE = 20
        private val BLOCK = 0
        private val TRIGGER = 12
    }
}
