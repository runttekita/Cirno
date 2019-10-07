package cirno.daten

import cirno.Cirno
import cirno.characters.CirnoChar
import basemod.BaseMod

class Initialize {

    init {
        val attackIce = "cirno/images/512prod/bg_attack_default_gray.png"
        val skillIce = "cirno/images/512prod/bg_skill_default_gray.png"
        val powerIce = "cirno/images/512prod/bg_power_default_gray.png"
        val iceEnergy = "cirno/images/512prod/card_default_gray_orb.png"
        val cardOrb = "cirno/images/512prod/card_small_orb.png"
        val attackIceBig = "cirno/images/1024prod/bg_attack_default_gray.png"
        val skillIceBig = "cirno/images/1024prod/bg_skill_default_gray.png"
        val powerIceBig = "cirno/images/1024prod/bg_power_default_gray.png"
        val energyOrbBig = "cirno/images/1024prod/card_default_gray_orb.png"
        BaseMod.addColor(CirnoChar.Enums.enums.Cirno_Ice, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE,
                Cirno.CIRNO_ICE, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE,
                attackIce, skillIce, powerIce,
                iceEnergy, attackIceBig,
                skillIceBig, powerIceBig,
                energyOrbBig, cardOrb)
    }

}