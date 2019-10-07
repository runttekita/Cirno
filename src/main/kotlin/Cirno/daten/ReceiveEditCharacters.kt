package Cirno.daten

import Cirno.characters.CirnoChar
import basemod.BaseMod

class ReceiveEditCharacters {
    val button = "Cirno/images/charSelect/DefaultCharacterButton.png"
    val portrait = "Cirno/images/charSelect/DefaultCharacterPortraitBG.png"
    init {
        BaseMod.addCharacter(CirnoChar.Enums.enums.Cirno?.let { CirnoChar("Cirno", it) },
                button, portrait, CirnoChar.Enums.enums.Cirno)
    }

}