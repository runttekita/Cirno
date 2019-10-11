package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper
import cirno.interfaces.Shiver
import cirno.interfaces.cardsDrawnThisCombat
import cirno.interfaces.shivered
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.dungeons.AbstractDungeon


class CardsDrawnThisCombatDamage : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, 1), Helper, Shiver {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
        isMultiDamage = true
        exhaust = true
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        if (shivered) {
            act(DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE, true))
        }
    }

    override fun applyPowers() {
        baseDamage = AbstractDungeon.actionManager.cardsDrawnThisCombat
        super.applyPowers()
        baseDamage = 0
    }

    override fun calculateCardDamage(mo: AbstractMonster?) {
        baseDamage = AbstractDungeon.actionManager.cardsDrawnThisCombat
        super.calculateCardDamage(mo)
        baseDamage = 0
    }

    override fun onShiver() {
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(CardsDrawnThisCombatDamage::class.java.simpleName))
        private val COST = 2
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ALL_ENEMY
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 0
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
