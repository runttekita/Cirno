package Piruru.brokenupgodobject

import Piruru.Piruluk
import Piruru.characters.PiruruChar
import basemod.BaseMod

class Initialize {

    init {
        val attackIce = "Piruru/images/512prod/bg_attack_default_gray.png"
        val skillIce = "Piruru/images/512prod/bg_skill_default_gray.png"
        val powerIce = "Piruru/images/512prod/bg_power_default_gray.png"
        val iceEnergy = "Piruru/images/512prod/card_default_gray_orb.png"
        val cardOrb = "Piruru/images/512prod/card_small_orb.png"
        val attackIceBig = "Piruru/images/1024prod/bg_attack_default_gray.png"
        val skillIceBig = "Piruru/images/1024prod/bg_skill_default_gray.png"
        val powerIceBig = "Piruru/images/1024prod/bg_power_default_gray.png"
        val energyOrbBig = "Piruru/images/1024prod/card_default_gray_orb.png"
        BaseMod.addColor(PiruruChar.Enums.enums.PIRURU_ICE, Piruluk.PIRURU_ICE, Piruluk.PIRURU_ICE, Piruluk.PIRURU_ICE,
                Piruluk.PIRURU_ICE, Piruluk.PIRURU_ICE, Piruluk.PIRURU_ICE, Piruluk.PIRURU_ICE,
                attackIce, skillIce, powerIce,
                iceEnergy, attackIceBig,
                skillIceBig, powerIceBig,
                energyOrbBig, cardOrb)
    }

}