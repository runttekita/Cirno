package Piruru.ui

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpireField.DefaultValue
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import reina.yui.*

class ARTSZoneManager() {
    private var artsZones = ArrayList<ARTSZone>()
    private val maxSize = 4

    init {

    }

    public fun addZone() {
        if (artsZones.isEmpty()) {
            artsZones.add(ARTSZone())
        } else if (artsZones.size < maxSize) {
            val lastZone = artsZones[artsZones.size - 1]
            Yui.autoPlaceVertically(lastZone, ARTSZone())
        } else {
            return
        }
    }

    public fun removeZone() {
        artsZones.removeAt(artsZones.size - 1)
    }

    @SpirePatch(clz = AbstractPlayer::class, method = SpirePatch.CLASS)
    public class ArtsZonePatch {

        companion object {
            @JvmStatic
            public val artsZones = SpireField{ARTSZoneManager()}
        }

    }

}