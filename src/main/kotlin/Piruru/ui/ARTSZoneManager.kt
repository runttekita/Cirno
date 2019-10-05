package Piruru.ui

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class ARTSZoneManager(private val sb: SpriteBatch) {
    private var artsZones = ArrayList<ARTSZone>()
/*
x: 2.000041
y: 249.0
width: 125
height: 175
 */
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
        }
    }

}