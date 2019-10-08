package cirno.daten

import basemod.BaseMod
import cirno.events.AMeetingWithAFriend

class Events {

    init {
        BaseMod.addEvent(AMeetingWithAFriend.ID, AMeetingWithAFriend::class.java)
    }

}