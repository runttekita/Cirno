package Piruru.daten

import Piruru.characters.PiruruChar
import basemod.BaseMod

class ReceiveEditCharacters {
    val button = "Piruru/images/charSelect/DefaultCharacterButton.png"
    val portrait = "Piruru/images/charSelect/DefaultCharacterPortraitBG.png"
    init {
        BaseMod.addCharacter(PiruruChar.Enums.enums.PIRURU?.let { PiruruChar("Piruru", it) },
                button, portrait, PiruruChar.Enums.enums.PIRURU)
    }

}