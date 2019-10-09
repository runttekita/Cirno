package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.actions.FreezeMonsterAction
import cirno.interfaces.OnApplyCold
import cirno.interfaces.OnApplyColdSpell
import cirno.interfaces.Spell
import cirno.powers.FreezeNextTurn
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class FreezeSpell : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, 1), OnApplyColdSpell {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {

    }

    override fun onApplyCold(m: AbstractMonster) {
    }

    override fun spellEffect(m: AbstractMonster) {
        when {
            AbstractDungeon.actionManager.turnHasEnded -> act(ApplyPowerAction(m, AbstractDungeon.player, FreezeNextTurn(m, magicNumber)))
            else -> act(FreezeMonsterAction(m, AbstractDungeon.player))
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(FreezeSpell::class.java.simpleName))
        private val COST = 2
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 0
        private val BLOCK = 0
        private val MAGIC = 1
    }
}
