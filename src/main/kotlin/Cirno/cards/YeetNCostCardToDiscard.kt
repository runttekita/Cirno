package Cirno.cards

import Cirno.Cirno.Statics.makeID
import Cirno.abstracts.CirnoCard
import Cirno.actions.MoveNCostToDiscard
import Cirno.relics.StarterRelic
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster


class YeetNCostCardToDiscard : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST_UP) {
    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        magicNumber =
                if (AbstractDungeon.player.hasRelic(StarterRelic.ID))
                    AbstractDungeon.player.getRelic(StarterRelic.ID).counter
                else
                    0
        act(MoveNCostToDiscard(magicNumber))
    }

    override fun applyPowers() {
        super.applyPowers()
        magicNumber =
                if (AbstractDungeon.player.hasRelic(StarterRelic.ID))
                    AbstractDungeon.player.getRelic(StarterRelic.ID).counter
                else
                    0
        baseMagicNumber = magicNumber;
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(YeetNCostCardToDiscard::class.java.simpleName))
        private val COST = 1
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.SELF
        private val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 0
        private val BLOCK = 0
        private val MAGIC = 0
        private val COST_UP = 0;
    }

    @SpirePatch(clz = AbstractPlayer::class, method = "loseRelic")
    object RemoveIfNoStarterPatch {
        @JvmStatic
        fun Postfix(__instance: AbstractPlayer, id: String) {
            if (id == StarterRelic.ID)
                AbstractDungeon.rareCardPool.removeCard(makeID(YeetNCostCardToDiscard::class.java))
        }
    }
}
