package Piruru.ui

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import reina.yui.*

class ARTSZoneManager(private val sb: SpriteBatch) {
    private var artsZones = ArrayList<ARTSZone>()
    private val maxSize = 4

    init {

    }

    public fun render(sb) {
        if (AbstractDungeon.player != null && artsZones.isNotEmpty()) {
            for (zone in artsZones) {
                zone.render(sb)
                zone.update()
            }
        }
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

}