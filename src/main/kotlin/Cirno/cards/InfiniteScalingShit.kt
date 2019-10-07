package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.PiruruCard
import Cirno.actions.RecoverAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.monsters.AbstractMonster


class InfiniteScalingShit : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, RECOVER_UP, COST) {
    init {
        magicNumber = RECOVER_AMT
        baseMagicNumber = magicNumber
        exhaust = true
    }

    override fun upgrade() {
        timesUpgraded++
        if (!upgraded) {
            rawDescription = cardStrings.UPGRADE_DESCRIPTION
            initializeDescription()
        }
        upgraded = true
        name = cardStrings.NAME + "+" + timesUpgraded
        initializeTitle()
        upgradeMagicNumber(RECOVER_UP)
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(RecoverAction(magicNumber))
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(InfiniteScalingShit::class.java.simpleName))
        private val COST = 0
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val RECOVER_AMT = 1
        private val RECOVER_UP = 2
    }
}
