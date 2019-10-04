package Piruru.brokenupgodobject

import Piruru.characters.PiruruChar
import basemod.BaseMod

class ReceiveEditCharacters {
    val shoulderOne = "Piruru/images/char/defaultCharacter/shoulder.png"
    val shoulderTwo = "Piruru/images/char/defaultCharacter/shoulder2.png"
    val corpse = "Piruru/images/char/defaultCharacter/corpse.png"
    val button = "Piruru/images/charSelect/DefaultCharacterButton.png"
    val portrait = "Piruru/images/charSelect/DefaultCharacterPortraitBG.png"

    init {
        BaseMod.addCharacter(PiruruChar.Enums.enums.PIRURU?.let { PiruruChar("Piruru", it) },
                button, portrait, PiruruChar.Enums.enums.PIRURU)
    }

}