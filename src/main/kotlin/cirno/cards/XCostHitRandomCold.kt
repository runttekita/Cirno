package cirno.cards

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoCard
import cirno.actions.XCostAction
import cirno.powers.Cold
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.EnergyManager
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster


class XCostHitRandomCold : CirnoCard(cardStrings, COST, TYPE, RARITY, TARGET, DAMAGE_UP, BLOCK_UP, MAGIC_UP, COST) {

    init {
        baseDamage = DAMAGE
        baseBlock = BLOCK
        magicNumber = MAGIC
        baseMagicNumber = magicNumber
    }

    override fun use(p: AbstractPlayer, m: AbstractMonster?) {
        when {
            upgraded -> {
                act(XCostAction(energyOnUse) {
                    val bitchToHit = AbstractDungeon.getRandomMonster()
                    damage(bitchToHit)
                    act(ApplyPowerAction(bitchToHit, p, Cold(bitchToHit)))
                })
            }
            else -> {
                act(XCostAction(energyOnUse + 1) {
                    val bitchToHit = AbstractDungeon.getRandomMonster()
                    damage(bitchToHit)
                    act(ApplyPowerAction(bitchToHit, p, Cold(bitchToHit)))
                })
            }
        }
    }

    companion object {
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(
                makeID(XCostHitRandomCold::class.java.simpleName))
        private val COST = -1
        private val TYPE = CardType.ATTACK
        private val RARITY = AbstractCard.CardRarity.UNCOMMON
        private val TARGET = AbstractCard.CardTarget.ALL_ENEMY
        private val DAMAGE_UP = 2
        private val BLOCK_UP = 0
        private val MAGIC_UP = 0
        private val DAMAGE = 8
        private val BLOCK = 0
        private val MAGIC = 0
    }
}
