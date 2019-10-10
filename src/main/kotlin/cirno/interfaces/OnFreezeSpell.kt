package cirno.interfaces

import com.megacrit.cardcrawl.core.AbstractCreature

interface OnFreezeSpell : Spell {

    fun onFreeze(target: AbstractCreature)
    
}