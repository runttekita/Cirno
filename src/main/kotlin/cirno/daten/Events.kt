package cirno.daten

import basemod.BaseMod
import cirno.characters.CirnoChar
import cirno.events.AMeetingWithAFriend
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.dungeons.Exordium

class Events {

    init {
        BaseMod.addEvent(AMeetingWithAFriend.ID, AMeetingWithAFriend::class.java, Exordium.ID)
    }

    @SpirePatch (
        clz = AbstractDungeon::class,
        method = "initializeCardPools"
    )
    public class CirnoONLY {
        public companion object {
            @JvmStatic
            fun Prefix(__instance: AbstractDungeon) {
                if (AbstractDungeon.player !is CirnoChar) {
                    AbstractDungeon.eventList.remove(AMeetingWithAFriend.ID)
                }
            }
        }
    }

}