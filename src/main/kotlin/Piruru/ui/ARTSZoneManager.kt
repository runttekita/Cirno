package Piruru.ui

import Piruru.characters.PiruruChar
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpireField.DefaultValue
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import reina.yui.*

public val AbstractPlayer.artsZones: ARTSZoneManager
    get() = ARTSZoneManager.ArtsZonePatch.artsZones.get(this)

class ARTSZoneManager() {
    public var zones = ArrayList<ARTSZone>()
    private val maxSize = 4

    init {

    }

    public fun addZone() {
        if (zones.isEmpty()) {
            zones.add(ARTSZone())
        } else if (zones.size < maxSize) {
            val lastZone = zones[zones.size - 1]
            val newZone = ARTSZone()
            Yui.autoPlaceVertically(lastZone, newZone)
            zones.add(newZone)
        } else {
            return
        }
    }

    public fun removeZone() {
        zones.removeAt(zones.size - 1)
    }

    @SpirePatch(clz = AbstractPlayer::class, method = SpirePatch.CLASS)
    public class ArtsZonePatch {
        public companion object {
            @JvmField
            public var artsZones = SpireField{ARTSZoneManager()}
        }
    }

    @SpirePatch(clz = AbstractPlayer::class, method = SpirePatch.CONSTRUCTOR)
    public class GivePiruArtsZones {
        public companion object {
            @JvmStatic
            public fun Postfix(__instance: AbstractPlayer) {
                if (__instance is PiruruChar) {
                    __instance.artsZones.addZone()
                    __instance.artsZones.addZone()
                    __instance.artsZones.addZone()
                    __instance.artsZones.addZone()
                }
            }
        }
    }

    @SpirePatch(clz = AbstractPlayer::class, method = "renderHand")
    public class renderZones {
        public companion object {
            @JvmStatic
            public fun Prefix(__instance: AbstractPlayer, sb: SpriteBatch) {
                if (CardCrawlGame.dungeon != null && AbstractDungeon.player != null && AbstractDungeon.player.artsZones.zones.isNotEmpty()) {
                    for (zone in AbstractDungeon.player.artsZones.zones) {
                        zone.render(sb)
                        zone.update()
                    }
                }
            }
        }
    }
}