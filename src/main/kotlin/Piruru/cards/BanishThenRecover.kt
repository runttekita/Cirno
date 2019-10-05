package Piruru.cards

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruCard
import Piruru.actions.BanishAction
import Piruru.actions.RecoverAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class BanishThenRecover : PiruruCard(cardStrings, COST, TYPE, RARITY, TARGET, 0, 0, 0, COST_UP) {
    init {
        magicNumber = RECOVER_AMT
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        act(BanishAction(magicNumber) { act(RecoverAction(magicNumber)) })
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(BanishThenRecover::class.java.simpleName))
        private val COST = 2
        private val TYPE = AbstractCard.CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.SELF
        private val RECOVER_AMT = 2
        private val COST_UP = 1
    }
}
