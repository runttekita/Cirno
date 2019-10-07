package cirno.daten

import cirno.characters.CirnoChar
import basemod.BaseMod

class ReceiveEditCharacters() {
    val button = "cirno/images/charSelect/DefaultCharacterButton.png"
    val portrait = "cirno/images/charSelect/DefaultCharacterPortraitBG.png"
    init {

        BaseMod.addCharacter(CirnoChar.Enums.enums.Cirno?.let { CirnoChar("Cirno", it) },
                button, portrait, CirnoChar.Enums.enums.Cirno)
    }

}