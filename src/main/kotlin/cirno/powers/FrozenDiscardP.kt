package cirno.powers

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoPower
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction
import com.megacrit.cardcrawl.actions.utility.UseCardAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.daily.TimeLookup.isDone
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import javassist.CtBehavior
import javax.jws.soap.SOAPBinding

class FrozenDiscardP() : CirnoPower() {
    var frozenDiscard = true

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
    }

    init {
        val POWER_ID : String = Cirno.makeID(this::class.java.simpleName)
        val powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        ID = POWER_ID
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        this.owner = AbstractDungeon.player
        updateDescription()
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0]
    }

    override fun stackPower(amount: Int) {
    }

    @SpirePatch(
            clz = UseCardAction::class,
            method = "update"
    )
    public class FrozenDiscardPile {
        public companion object {
            @JvmStatic
            fun Prefix(__instance: UseCardAction, ___targetCard: AbstractCard) {
                if (AbstractDungeon.player.hasPower(makeID(FrozenDiscardP::class.java))) {
                    ___targetCard.shuffleBackIntoDrawPile = true
                }
            }
        }
    }

    @SpirePatch(
            clz = DiscardAtEndOfTurnAction::class,
            method = "update"
    )
    public class ShuffleBackIntoDeckAtEndOfTurn {
        public companion object {
            @JvmStatic
            @SpireInsertPatch(
                    locator = Locator::class
            )
            fun Insert(__instance: DiscardAtEndOfTurnAction) : SpireReturn<Any> {
                if (AbstractDungeon.player.hasPower(makeID(FrozenDiscardP::class.java))) {
                    for (card in AbstractDungeon.player.hand.group) {
                        AbstractDungeon.player.hand.moveToDeck(card, true)
                    }
                    isDone = true
                    return SpireReturn.Return(null)
                } else {
                    return SpireReturn.Continue()
                }
            }
        }
    }

    public class Locator : SpireInsertLocator() {
        override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
            val matcher = Matcher.MethodCallMatcher(CardGroup::class.java, "size")
            return LineFinder.findInOrder(ctMethodToPatch, matcher)
        }

    }

}
