package cirno.variable

import basemod.abstracts.DynamicVariable
import cirno.abstracts.CirnoCard
import com.megacrit.cardcrawl.cards.AbstractCard

class DisplayVariable : DynamicVariable() {

    override fun isModified(card: AbstractCard): Boolean {
        return (card as CirnoCard).cirnoDynamicNumber != baseValue(card)
    }

    override fun upgraded(card: AbstractCard): Boolean {
        return (card as CirnoCard).isDynamicUpgraded
    }

    override fun value(card: AbstractCard): Int {
        return (card as CirnoCard).cirnoDynamicNumber
    }

    override fun baseValue(card: AbstractCard): Int {
        return (card as CirnoCard).cirnoDynamicNumber
    }

    override fun key(): String {
        return "baka"
    }

}