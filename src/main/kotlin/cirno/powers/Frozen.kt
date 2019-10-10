package cirno.powers

import cirno.Cirno
import cirno.abstracts.CirnoPower
import cirno.patches.FrozenIntentPatches.Enum.FrozenIntentEnum.FROZEN
import basemod.ReflectionHacks
import basemod.interfaces.CloneablePowerInterface
import cirno.interfaces.Helper
import com.badlogic.gdx.Gdx
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.GameActionManager
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.PowerStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo
import com.megacrit.cardcrawl.powers.AbstractPower
import javassist.CannotCompileException
import javassist.expr.ExprEditor
import javassist.expr.MethodCall


/**
 * Basically stolen from
 * @see com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower
 */
public class Frozen : CirnoPower, CloneablePowerInterface, InvisiblePower, Helper {
    public var shaderTimer: Float = 0.toFloat()
    private var moveByte: Byte = 0
    private var moveIntent: AbstractMonster.Intent? = null
    private var move: EnemyMoveInfo? = null

    constructor(owner: AbstractCreature) {
        this.owner = owner
    }

    constructor(owner: AbstractMonster) : super() {
        this.owner = owner
        type = AbstractPower.PowerType.DEBUFF
        shaderTimer = 0.0f
    }

    override fun updateDescription() {
        description = DESCRIPTIONS!![0]
    }

    companion object {
        var NAME: String? = null
        var DESCRIPTIONS: Array<String>? = null
    }

    init {
        val POWER_ID : String = Cirno.makeID(Frozen::class.java.simpleName)
        val powerStrings: PowerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID)
        ID = POWER_ID
        NAME = powerStrings.NAME
        DESCRIPTIONS = powerStrings.DESCRIPTIONS
        name = NAME
        updateDescription()
        img = Cirno.textureLoader.getTexture(Cirno.makePowerPath(this.javaClass.simpleName))
    }

    override fun onDeath() {
        ReflectionHacks.setPrivate(owner, AbstractCreature::class.java, "animationTimer", 0.0f)
    }

    override fun atEndOfRound() {
        act(RemoveSpecificPowerAction(owner, owner, this))
    }

    override fun update(slot: Int) {
        super.update(slot)

        if (shaderTimer < 1.0f) {
            shaderTimer += Gdx.graphics.deltaTime
            if (shaderTimer > 1.0f) {
                shaderTimer = 1.0f
            }
        }

        if (owner is AbstractMonster) {
            (owner as AbstractMonster).state?.timeScale = 0f
        }
    }

    override fun onInitialApplication() {
        // Dumb action to delay grabbing monster's intent until after it's actually set
        act(object : AbstractGameAction() {
            override fun update() {
                if (owner is AbstractMonster) {
                    moveByte = (owner as AbstractMonster).nextMove
                    moveIntent = (owner as AbstractMonster).intent
                    try {
                        val f = AbstractMonster::class.java.getDeclaredField("move")
                        f.isAccessible = true
                        move = f.get(owner) as EnemyMoveInfo
                        move!!.intent = FROZEN
                        (owner as AbstractMonster).createIntent()
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    } catch (e: NoSuchFieldException) {
                        e.printStackTrace()
                    }

                }
                isDone = true
            }
        })
    }

    override fun onRemove() {
        if (owner is AbstractMonster) {
            val m = owner as AbstractMonster
            if (move != null) {
                m.setMove(moveByte, moveIntent, move!!.baseDamage, move!!.multiplier, move!!.isMultiDamage)
            } else {
                m.setMove(moveByte, moveIntent)
            }
            m.createIntent()
            m.applyPowers()
        }
    }

    override fun makeCopy(): AbstractPower {
        return Frozen(owner)
    }

    /**
     * Straight nabbed and improved from
     * @see com.evacipated.cardcrawl.mod.stslib.patches.StunMonsterPatch
     * Ty Papa Kio!
     */
    @SpirePatch(clz = GameActionManager::class, method = "getNextAction")
    object FrozenPowerPatch {
        @JvmStatic
        fun Instrument(): ExprEditor {
            return object : ExprEditor() {
                @Throws(CannotCompileException::class)
                override fun edit(m: MethodCall?) {
                    if (m!!.className == AbstractMonster::class.java.name && m.methodName == "takeTurn") {
                        m.replace("if (!m.hasPower(\"Cirno:Frozen\")) {" +
                                "\$_ = \$proceed($$);" +
                                "}");
                    }
                }
            }
        }
    }


}
