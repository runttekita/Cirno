package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import Piruru.actions.MillAction
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class AoEMill : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MILL_UP, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = MILL
        baseMagicNumber = magicNumber
        isMultiDamage = true
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster) {
        act(DamageAllEnemiesAction(p, multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.NONE))
        act(MillAction(magicNumber) { c -> AbstractDungeon.getCurrRoom().monsters.monsters.forEach { monster -> act(MillAction(magicNumber)) } })
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(AoEMill::class.java.simpleName))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.COMMON
        private val TARGET = AbstractCard.CardTarget.ALL_ENEMY
        private val DAMAGE_UP = 3
        private val BLOCK_UP = 0
        private val MILL_UP = 1
        private val DAMAGE = 7
        private val BLOCK = 0
        private val MILL = 2
    }
}
