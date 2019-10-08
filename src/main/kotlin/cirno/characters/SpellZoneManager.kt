package cirno.characters

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.orbs.AbstractOrb
import com.megacrit.cardcrawl.vfx.SpeechBubble
import reina.yui.Yui
import kotlin.reflect.KClass
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn
import java.nio.file.Files.move




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
                        when (AbstractDungeon.player.spellZones.zones.indexOf(zone)) {
                            0 -> Yui.autoPlaceSamePosition(AbstractDungeon.player.hb, zone, AbstractDungeon.player.hb.width / 4f, AbstractDungeon.player.hb.height + 20 * Settings.scale)
                            1 -> Yui.autoPlaceSamePosition(AbstractDungeon.player.hb, zone, -zone.getWidth() - 20 * Settings.scale, 0f)
                            2 -> Yui.autoPlaceHorizontally(AbstractDungeon.player.hb, zone, 20 * Settings.scale)
                        }
                        zone.render(sb)
                        zone.update()
                    }
                }
            }
        }
    }

    public fun addZone() {
        if (zones.size < 3) {
            zones.add(SpellZone())
        } else {
            AbstractDungeon.effectList.add(SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f, "TODO", true))
        }
    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = SpirePatch.CONSTRUCTOR
    )
    public class GiveSpellZones {
        public companion object {
            @JvmStatic
            fun Postfix(__instance: AbstractPlayer) {
                if (__instance is CirnoChar) {
                    __instance.spellZones.addZone()
                    __instance.spellZones.addZone()
                    __instance.spellZones.addZone()
                }
            }
        }
    }

    @SpirePatch(
            clz= AbstractOrb::class,
            method="setSlot",
            paramtypez = [
                Int::class,
                Int::class
            ]
    )
    public class OrbPositionPatch {
        public companion object {
            @JvmStatic
            fun Prefix(__instance: AbstractOrb, slotNum: Int, maxOrbs: Int): SpireReturn<Any> {
                if (AbstractDungeon.player is CirnoChar) {
                    __instance.tX = (AbstractDungeon.player as CirnoChar).orbPositionsX[slotNum]
                    __instance.tY = (AbstractDungeon.player as CirnoChar).orbPositionsY[slotNum]

                    __instance.hb.move(__instance.tX, __instance.tY)
                    return SpireReturn.Return<Any>(null)
                } else {

                    return SpireReturn.Continue<Any>()

                }
            }
        }
    }

}

val AbstractPlayer.spellZones: SpellZoneManager
    get() = SpellZoneManager.SpellZones.spellZones.get(this)

