package cirno.daten

import cirno.characters.CirnoChar
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.helpers.CardLibrary
import com.megacrit.cardcrawl.unlock.UnlockTracker

/**
 * But Reina, I can hear you ask, why aren't you using autoAddRelics from Kio? Well the answer is simple
 * I'm too lazy to copy paste all that stuff and make like 90 classes and make.sh does this for me.
 */
class ReceiveEditRelics {

    init {
		//autoAddRelics
        var count = 0
        var commonCount = 0
        var uncommonCount = 0
        var rareCount = 0
        var basicCount = 0
        for (c in CardLibrary.getCardList(CirnoChar.Enums.enums.LIBRARY_COLOR)) {
            UnlockTracker.unlockCard(c.cardID)
            if (c.rarity == AbstractCard.CardRarity.BASIC) {
                basicCount++
                count++
            }
            if (c.rarity == AbstractCard.CardRarity.COMMON) {
                commonCount++
                count++
            }
            if (c.rarity == AbstractCard.CardRarity.UNCOMMON) {
                uncommonCount++
                count++
            }
            if (c.rarity == AbstractCard.CardRarity.RARE) {
                rareCount++
                count++
            }
        }
        println("COMMON CARDS $commonCount")
        println("UNCOMMON CARDS $uncommonCount")
        println("RARE CARDS $rareCount")
        println("TOTAL CARDS $count")
    }

}
