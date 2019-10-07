package Cirno.abstracts

import com.megacrit.cardcrawl.powers.AbstractPower

abstract class CirnoPower : AbstractPower() {
    init {

    }

    abstract override fun updateDescription()

    override fun stackPower(amount: Int) {
        fontScale = 8.0f
        this.amount += amount
    }

}
