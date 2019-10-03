package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import basemod.BaseMod
import basemod.helpers.TooltipInfo
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster

import java.util.ArrayList


class AddDrawARTS : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, ICE_UP, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = ICE
        baseMagicNumber = magicNumber
        cardsToPreview = DrawARTS()
    }

    override fun getCustomTooltips(): List<TooltipInfo> {
        val tooltip = TooltipInfo(BaseMod.getKeywordTitle(makeID("arts").toLowerCase()), BaseMod.getKeywordDescription(makeID("arts").toLowerCase()))
        val list = ArrayList<TooltipInfo>()
        list.add(tooltip)
        return list
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        for (i in 0 until magicNumber) {
            act(MakeTempCardInHandAction(DrawARTS()))
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(AddDrawARTS::class.java.simpleName))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val ICE_UP = 1
        private val DAMAGE = 0
        private val BLOCK = 0
        private val ICE = 2
    }
}
