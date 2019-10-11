package cirno.abstracts

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower
import com.megacrit.cardcrawl.powers.AbstractPower

abstract class CirnoPower : TwoAmountPower() {
    init {

    }

    abstract override fun updateDescription()

    override fun stackPower(amount: Int) {
        fontScale = 8.0f
        this.amount += amount
    }

}
