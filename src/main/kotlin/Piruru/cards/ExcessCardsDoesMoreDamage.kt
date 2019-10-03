package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import Piruru.actions.BanishAction
import Piruru.interfaces.NotShittyTookDamage
import Piruru.interfaces.OnRefreshHand
import com.evacipated.cardcrawl.mod.stslib.StSLib
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class ExcessCardsDoesMoreDamage : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, HAND_UP, COST),
        OnRefreshHand {

    override fun onRefreshHand() {
        if (AbstractDungeon.player.hand.size() >= magicNumber) baseDamage * 2
    }

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = HAND
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        damage(m!!)
    }

    override fun triggerOnGlowCheck() {
        glowColor = if (AbstractDungeon.player.hand.size() >= magicNumber)
            AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy()
        else
            AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy()
    }


    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(ExcessCardsDoesMoreDamage::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ENEMY
        private val DAMAGE_UP = 2
        private val BLOCK_UP = 0
        private val HAND_UP = -1
        private val DAMAGE = 8
        private val BLOCK = 0
        private val HAND = 7
    }
}
