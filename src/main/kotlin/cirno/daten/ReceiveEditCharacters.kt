package cirno.daten

import cirno.characters.CirnoChar
import basemod.BaseMod

class ReceiveEditCharacters(private val cirnoChar: CirnoChar) {
    val button = "Cirno/images/charSelect/DefaultCharacterButton.png"
    val portrait = "Cirno/images/charSelect/DefaultCharacterPortraitBG.png"
    init {

        BaseMod.addCharacter(cirnoChar,
                button, portrait, CirnoChar.Enums.enums.Cirno)
    }

}