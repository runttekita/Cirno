package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import Piruru.actions.BanishAction
import Piruru.interfaces.OnRefreshHand
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class ExcessBlock : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, EXCESS_UP, COST),
    OnRefreshHand{

    override fun onRefreshHand() {
        if (AbstractDungeon.player.hand.size() >= magicNumber)
            block * 2
    }

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = EXCESS
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        block()
    }

    override fun triggerOnGlowCheck() {
        glowColor = if (AbstractDungeon.player.hand.size() >= magicNumber)
            AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
        else
            AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy()
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ExcessBlock::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 7
        private val EXCESS_UP = -1
        private val DAMAGE = 0
        private val BLOCK = 7
        private val EXCESS = 7
    }
}
