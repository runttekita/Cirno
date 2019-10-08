package cirno.actions

import cirno.characters.spellZones
import cirno.interfaces.Spell
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.utility.UseCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import javassist.CtBehavior

class SetSpellAction : AbstractGameAction() {

    override fun update() {
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
                    val tickDuration = UseCardAction::class.java.javaClass.getDeclaredMethod("tickDuration")
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
}