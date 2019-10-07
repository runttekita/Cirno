package Cirno.daten

import Cirno.Cirno
import Cirno.characters.CirnoChar
import basemod.BaseMod

class Initialize {

    init {
        val attackIce = "Cirno/images/512prod/bg_attack_default_gray.png"
        val skillIce = "Cirno/images/512prod/bg_skill_default_gray.png"
        val powerIce = "Cirno/images/512prod/bg_power_default_gray.png"
        val iceEnergy = "Cirno/images/512prod/card_default_gray_orb.png"
        val cardOrb = "Cirno/images/512prod/card_small_orb.png"
        val attackIceBig = "Cirno/images/1024prod/bg_attack_default_gray.png"
        val skillIceBig = "Cirno/images/1024prod/bg_skill_default_gray.png"
        val powerIceBig = "Cirno/images/1024prod/bg_power_default_gray.png"
        val energyOrbBig = "Cirno/images/1024prod/card_default_gray_orb.png"
        BaseMod.addColor(CirnoChar.Enums.enums.Cirno_ICE, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE,
                Cirno.CIRNO_ICE, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE, Cirno.CIRNO_ICE,
                attackIce, skillIce, powerIce,
                iceEnergy, attackIceBig,
                skillIceBig, powerIceBig,
                energyOrbBig, cardOrb)
    }

}