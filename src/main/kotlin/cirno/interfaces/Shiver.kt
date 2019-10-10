package cirno.interfaces

import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.actions.GameActionManager
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import javassist.CtBehavior
import com.megacrit.cardcrawl.actions.AbstractGameAction

interface Shiver {

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = SpirePatch.CLASS
    )
    public class ShiverActive {
        public companion object {
            @JvmField
            public var isShivering =  SpireField<Boolean> {false}
            @JvmField
            public var amountOfShiveringCards = SpireField<Int> {0}
        }
    }

    @SpirePatch(
            clz = GameActionManager::class,
            method = "getNextAction"
    )
    public class SetShivering {
        public companion object {
            @SpireInsertPatch(
                    locator = Locator::class
            )
            @JvmStatic
            fun Insert(__instance: GameActionManager) {
                AbstractDungeon.actionManager.addToBottom(object : AbstractGameAction() {
                    override fun update() {
                        AbstractDungeon.player.isShivering = true
                        isDone = true
                    }

                })
            }
        }
    }

    public class Locator : SpireInsertLocator() {
        override fun Locate(p0: CtBehavior?): IntArray {
            val matcher = Matcher.MethodCallMatcher(AbstractPlayer::class.java, "applyStartOfTurnPostDrawRelics")
            return LineFinder.findInOrder(p0, matcher)
        }

    }

    @SpirePatch(
            clz = GameActionManager::class,
            method = "endTurn"
    )
    public companion object {
        @JvmStatic
        fun Prefix(__instance: GameActionManager) {
            AbstractDungeon.player.isShivering = false
        }
    }

}

var AbstractPlayer.isShivering: Boolean
    get() = Shiver.ShiverActive.isShivering.get(this)
    set(value) = Shiver.ShiverActive.isShivering.set(this, value)

var AbstractPlayer.shiveredCards: Int
    get() = Shiver.ShiverActive.amountOfShiveringCards.get(this)
    set(value) = Shiver.ShiverActive.amountOfShiveringCards.set(this, value)