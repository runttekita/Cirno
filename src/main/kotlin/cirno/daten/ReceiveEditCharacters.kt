package cirno.daten

import cirno.characters.CirnoChar
import basemod.BaseMod
import cirno.Cirno.Statics.makeID
import com.megacrit.cardcrawl.core.CardCrawlGame

class ReceiveEditCharacters() {
    val button = "cirno/images/charSelect/DefaultCharacterButton.png"
    val portrait = "cirno/images/charSelect/DefaultCharacterPortraitBG.png"
    init {
        val charStrings = CardCrawlGame.languagePack.getCharacterString(makeID(CirnoChar::class.java))
        BaseMod.addCharacter(CirnoChar.Enums.enums.Cirno?.let { CirnoChar(charStrings.NAMES[0], it) },
                button, portrait, CirnoChar.Enums.enums.Cirno)
    }

}