package cirno.characters

import cirno.abstracts.CirnoCard
import cirno.cards.BlankSpellZone
import cirno.interfaces.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.orbs.AbstractOrb
import com.megacrit.cardcrawl.vfx.SpeechBubble
import reina.yui.Yui
import kotlin.reflect.KClass
import com.megacrit.cardcrawl.actions.utility.UseCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.helpers.Hitbox
import com.megacrit.cardcrawl.monsters.AbstractMonster
import javassist.CtBehavior
import java.nio.file.Files.move




class SpellZoneManager : NotShittyTookDamage, OnApplyColdSpell, OnCardDrawSpell, NotShittyOnAttackedSpell {
    var zones = ArrayList<SpellZone>()

    public fun addZone() {
        if (zones.size < 3) {
            zones.add(SpellZone())
        } else {
            AbstractDungeon.effectList.add(SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0f, "TODO", true))
        }
    }

    public fun isEmpty(): Boolean {
        for (zone in zones) {
            if (zone.storedCard is BlankSpellZone) {
                return false
            }
        }
        return true
    }

    public fun storedCardsSize(): Int {
        var retVal = 0
        for (zone in zones) {
            if (zone.storedCard !is BlankSpellZone)  {
                retVal++
            }
        }
        return retVal
    }

    public fun setSpell(card: AbstractCard) {
        for (zone in zones) {
            if (zone.storedCard is BlankSpellZone) {
                zone.storeCard(card)
                zone.storedCard.isGlowing = false
                return;
            }
        }
        zones[2].storeCard(zones[1].storedCard)
        zones[1].storeCard(zones[0].storedCard)
        zones[0].storeCard(card)
    }

    override fun notShittyTookDamage(i: DamageInfo) {
        for (zone in zones) {
            if (zone.storedCard is TookDamageSpell) {
                zone.triggerEffect(i)
            }
        }
    }

    override fun notShittyOnAttacked(info: DamageInfo) {
        for (zone in zones) {
            if (zone.storedCard is NotShittyOnAttackedSpell) {
                zone.triggerEffect(info)
            }
        }
    }

    override fun onApplyCold(m: AbstractMonster) {
        for (zone in zones) {
            if (zone.storedCard is OnApplyColdSpell) {
                zone.triggerEffect(null, m)
            }
        }
    }

    override fun onDraw() {
        for (zone in zones) {
            if (zone.storedCard is OnCardDrawSpell) {
                zone.triggerEffect()
            }
        }
    }

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
            clz = AbstractCreature::class,
            method = "renderHealth"
    )
    public class RenderZones {
        public companion object {
            @JvmStatic
            @SpireInsertPatch(
                    locator = RenderLocator::class
            )
            public fun Insert(__instance: AbstractCreature, sb: SpriteBatch) {
                if (CardCrawlGame.dungeon != null && AbstractDungeon.player != null && AbstractDungeon.player.spellZones.zones.isNotEmpty()) {
                    for (zone in AbstractDungeon.player.spellZones.zones) {
                        when (AbstractDungeon.player.spellZones.zones.indexOf(zone)) {
                            0 -> Yui.autoPlaceSamePosition(AbstractDungeon.player.hb, zone, AbstractDungeon.player.hb.width / 4f, AbstractDungeon.player.hb.height + 50 * Settings.scale)
                            1 -> Yui.autoPlaceSamePosition(AbstractDungeon.player.hb, zone, -zone.getWidth() - 50 * Settings.scale, 0f)
                            2 -> Yui.autoPlaceHorizontally(AbstractDungeon.player.hb, zone, 50 * Settings.scale)
                        }
                        zone.render(sb)
                        zone.update()
                    }
                }
            }
        }
    }

    public class RenderLocator : SpireInsertLocator() {
        override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
            val matcher = Matcher.MethodCallMatcher(AbstractCreature::class.java, "renderHealthBg")
            return LineFinder.findInOrder(ctMethodToPatch, matcher)
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

    @SpirePatch(
            clz = UseCardAction::class,
            method = "update"
    )
    public class DontGoToDiscardOnUse {
        public companion object {
            @SpireInsertPatch(
                    locator = Locator::class
            )
            @JvmStatic
            fun Insert(__instance: UseCardAction, ___targetCard: AbstractCard) : SpireReturn<Any> {
                if (___targetCard is Spell) {
                    AbstractDungeon.player.hand.removeCard(___targetCard)
                    AbstractDungeon.player.spellZones.setSpell(___targetCard)
                    val tickDuration = AbstractGameAction::class.java.getDeclaredMethod("tickDuration")
                    tickDuration.isAccessible = true
                    tickDuration.invoke(__instance)
                    return SpireReturn.Return(null)
                }
                return SpireReturn.Continue()
            }
        }
    }

    public class Locator : SpireInsertLocator() {
        override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
            val matcher = Matcher.FieldAccessMatcher(UseCardAction::class.java, "reboundCard")
            return LineFinder.findInOrder(ctMethodToPatch, matcher)
        }
    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "onVictory"
    )
    public class ClearSpellsAtEndOfCombat {
        public companion object {
            @JvmStatic
            fun Prefix(__instance: AbstractPlayer) {
                for (zone in __instance.spellZones.zones) {
                    __instance.spellZones.setSpell(BlankSpellZone())
                    __instance.spellZones.setSpell(BlankSpellZone())
                    __instance.spellZones.setSpell(BlankSpellZone())
                }
            }
        }
    }

}

val AbstractPlayer.spellZones: SpellZoneManager
    get() = SpellZoneManager.SpellZones.spellZones.get(this)

