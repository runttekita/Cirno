package cirno.powers

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoPower
import cirno.actions.FreezeMonsterAction
import com.badlogic.gdx.Game
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.actions.GameActionManager
import com.megacrit.cardcrawl.actions.common.ReducePowerAction
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import javassist.CtBehavior

class FreezeNextTurn(target: AbstractCreature, turns: Int) : CirnoPower() {

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
        private const val PROC_AMOUNT = 3
    }

    init {
        val POWER_ID : String = Cirno.makeID(this::class.java.simpleName)
        val powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        ID = POWER_ID
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        updateDescription()
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
        owner = target
        amount = turns
    }

    override fun atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(FreezeMonsterAction(owner as AbstractMonster, AbstractDungeon.player))
        AbstractDungeon.actionManager.addToBottom(ReducePowerAction(owner, owner, this, 1))
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0]
    }

    @SpirePatch(
            clz = GameActionManager::class,
            method = "getNextAction"
    )
    public class ProcThisAtStartOfPlayerTurn {

        public companion object {
            @SpireInsertPatch(
                    locator = Locator::class
            )
            fun Insert(__instance: GameActionManager) {
                for (m in AbstractDungeon.getMonsters().monsters) {
                    if (m.hasPower(makeID(FreezeNextTurn::class.java))) {
                        m.getPower(makeID(FreezeNextTurn::class.java)).atStartOfTurn()
                    }
                }
            }
        }

    }

    public class Locator : SpireInsertLocator() {
        override fun Locate(ctMethodToPatch: CtBehavior?): IntArray {
            val matcher = Matcher.MethodCallMatcher(AbstractPlayer::class.java, "applyStartOfTurnPowers")
            return LineFinder.findInOrder(ctMethodToPatch, matcher)
        }

    }

}