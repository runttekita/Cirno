package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.actions.FreezeMonsterAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import cirno.interfaces.Helper


class DrawFreezeAll : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, DRAW_UP, COST), Helper {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = DRAW
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        var skillsDrawn = 0
        draw({c ->
            run {
                if (c.type == CardType.SKILL) {
                    skillsDrawn++
                    if (skillsDrawn == damage) {
                        loopOverMonsters { monster ->
                            act(FreezeMonsterAction(monster, player))
                        }
                    }
                }
            }
        })

    }

    override fun applyPowers() {

    }

    override fun calculateCardDamage(mo: AbstractMonster?) {

    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(DrawFreezeAll::class.java.simpleName))
        private val COST = 2
        private val TYPE = CardType.SKILL
        private val RARITY = AbstractCard.CardRarity.RARE
        private val TARGET = AbstractCard.CardTarget.ALL_ENEMY
        val DAMAGE_UP = 0
        private val BLOCK_UP = 0
        private val DRAW_UP = 2
        private val DAMAGE = 2
        private val BLOCK = 0
        private val DRAW = 3
    }
}
