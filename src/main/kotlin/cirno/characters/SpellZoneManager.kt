package cirno.characters

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class SpellZoneManager {
    var zones = ArrayList<SpellZone>()

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = SpirePatch.CLASS
    )
    public class SpellZones {
        public companion object {
            @JvmField
            public var spellZones = SpireField {SpellZoneManager()}
        }
    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "renderHand"
    )
    public class RenderZones {
        public companion object {
            @JvmStatic
            public fun Prefix(__instance: AbstractPlayer, sb: SpriteBatch) {
                if (CardCrawlGame.dungeon != null && AbstractDungeon.player != null && AbstractDungeon.player.spellZones.zones.isNotEmpty()) {
                    for (zone in AbstractDungeon.player.spellZones.zones) {
                        zone.render(sb)
                        zone.update()
                    }
                }
            }
        }
    }

}

val AbstractPlayer.spellZones: SpellZoneManager
    get() = SpellZoneManager.SpellZones.spellZones.get(this)

