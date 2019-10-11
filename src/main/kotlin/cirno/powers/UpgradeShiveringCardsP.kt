package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import cirno.interfaces.isShivering
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings

class UpgradeShiveringCardsP(amount: Int) : CirnoPower() {
    private var cardsUpgradedThisTurn = 0

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
    }

    init {
        val POWER_ID : String = Cirno.makeID(this::class.java.simpleName)
        val powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        ID = POWER_ID
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        owner = AbstractDungeon.player
        this.amount = amount
        updateDescription()
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
    }

    override fun onCardDraw(card: AbstractCard) {
        if (owner is AbstractPlayer && (owner as AbstractPlayer).isShivering) {
            if (cardsUpgradedThisTurn < amount) {
                card.upgrade()
                cardsUpgradedThisTurn++
            }
        }
    }

    override fun atEndOfTurn(isPlayer: Boolean) {
        cardsUpgradedThisTurn = 0
    }
    override fun updateDescription() {
        description = "${DESCRIPTIONS!![0]}$amount${DESCRIPTIONS!![1]}"
    }
}