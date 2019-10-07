package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.PiruruCard
import Cirno.abstracts.PiruruCard.Enums.Enums.ARTS
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField
import com.megacrit.cardcrawl.actions.common.DrawCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class DrawARTS : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DRAW_UP, COST) {
    init {
        damage = DAMAGE
        baseDamage = damage
        block = BLOCK
        baseBlock = block
        magicNumber = DRAW
        baseMagicNumber = magicNumber
        AlwaysRetainField.alwaysRetain.set(this, true)
        tags.add(ARTS)
        exhaust = true
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(DrawCardAction(magicNumber))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(DrawARTS::class.java.simpleName))
        private val COST = 1
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.SPECIAL
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val DRAW_UP = 1
        private val DAMAGE = 0
        private val BLOCK = 0
        private val DRAW = 2
    }
}
