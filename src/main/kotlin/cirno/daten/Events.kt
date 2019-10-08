package cirno.daten

import basemod.BaseMod
import cirno.events.AMeetingWithAFriend
import com.megacrit.cardcrawl.dungeons.Exordium

class Events {

    init {
        BaseMod.addEvent(AMeetingWithAFriend.ID, AMeetingWithAFriend::class.java, Exordium.ID)
    }

}