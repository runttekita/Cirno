package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.actions.FreezeMonsterAction
import cirno.interfaces.TookDamageSpell
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class SpellAttackedFreeze : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST), TookDamageSpell {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {

    }

    override fun spellEffect(info: DamageInfo) {
        act(FreezeMonsterAction(info.owner as AbstractMonster, AbstractDungeon.player))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(SpellAttackedFreeze::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.BASIC
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 4
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 15
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
