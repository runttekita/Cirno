package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import basemod.BaseMod
import basemod.helpers.TooltipInfo
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster

import java.util.ArrayList


class DamageAndCold : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, 0, COLD_UP, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        magicNumber = COLD_AMT
        baseMagicNumber = magicNumber
    }

    override fun getCustomTooltips(): List<TooltipInfo> {
        val tooltip = TooltipInfo(BaseMod.getKeywordTitle(makeID("freeze").toLowerCase()), BaseMod.getKeywordDescription(makeID("freeze").toLowerCase()))
        val list = ArrayList<TooltipInfo>()
        list.add(tooltip)
        return list
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
        cold(m)
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(DamageAndCold::class.java))
        private val COST = 2
        private val TYPE = AbstractCard.CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.BASIC
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE = 12
        private val DAMAGE_UP = 3
        private val COLD_AMT = 1
        private val COLD_UP = 1
    }
}
