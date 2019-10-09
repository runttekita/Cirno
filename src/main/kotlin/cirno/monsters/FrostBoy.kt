package cirno.monsters

import cirno.Cirno.Statics.makeID
import cirno.powers.Cold
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Interpolation
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.GameActionManager
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.monsters.MonsterGroup
import com.megacrit.cardcrawl.rooms.AbstractRoom
import javassist.CtBehavior

class FrostBoy(private val turns: Int) : AbstractMonster(monsterStrings.NAME, ID, turns, -8f, 105f, 200f, 250f, "cirno/images/monsters/frostBoy.png", -1250f, 100f) {
    private val aniTarget = -300f

    init {
        damage.add(DamageInfo(this, damageAmt, DamageInfo.DamageType.NORMAL))
        setHp(turns)
        init()
        applyPowers()
        showHealthBar()
        createIntent()
    }

    public companion object {
        val ID = makeID(FrostBoy::class.java)
        val monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID)
        val damageAmt = 12
    }

    enum class MOVES(public val value: Byte) {
        ATTACK(0)
    }

    override fun getMove(move: Int) {
        setMove(FrostBoy.MOVES.ATTACK.value, AbstractMonster.Intent.ATTACK_DEBUFF, damage[0].base)
    }

    override fun takeTurn() {
        when (nextMove) {
            0.toByte() -> {
                val target = AbstractDungeon.getRandomMonster()
                AbstractDungeon.actionManager.addToBottom(AnimateSlowAttackAction(this))
                AbstractDungeon.actionManager.addToBottom(DamageAction(target, damage[FrostBoy.MOVES.ATTACK.value.toInt()], AbstractGameAction.AttackEffect.NONE))
                AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(target, this, Cold(target), 1))
                AbstractDungeon.actionManager.addToBottom(DamageAction(this, DamageInfo(this, 1, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE))
            }
        }
        rollMove()
    }

    override fun updateFastAttackAnimation() {
        this.animationTimer -= Gdx.graphics.deltaTime
        var targetPos = 90.0f * Settings.scale

        when {
            animationTimer > 0.5f -> animX = Interpolation.exp5In.apply(0.0f, targetPos, (1.0f - animationTimer / 1.0f) * 2.0f)
            animationTimer < 0.0f -> {
                animationTimer = 0.0f
                animX = 0.0f
            }
            else -> animX = Interpolation.fade.apply(0.0f, targetPos, animationTimer / 1.0f * 2.0f)
        }
    }

    override fun updateSlowAttackAnimation() {
        animationTimer -= Gdx.graphics.deltaTime
        var targetPos = 90.0f * Settings.scale

        when {
            animationTimer > 0.5f -> animX = Interpolation.exp10In.apply(0.0f, targetPos, (1.0f - animationTimer / 1.0f) * 2.0f)
            animationTimer < 0.0f -> {
                animationTimer = 0.0f
                animX = 0.0f
            }
            else -> animX = Interpolation.fade.apply(0.0f, targetPos, animationTimer / 1.0f * 2.0f)
        }
    }
    

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = SpirePatch.CLASS
    )
    public class MonsterField {
        public companion object {
            @JvmField
            public var frostKing: SpireField<FrostBoy?> = SpireField{null}
        }
    }

    @SpirePatch(
            clz = AbstractRoom::class,
            method = "render"
    )
    public class RenderFrostKing {
        public companion object {
            @SpireInsertPatch(
                    locator = Locator::class
            )
            @JvmStatic
            fun Insert(__instance: AbstractRoom, sb: SpriteBatch) {
                AbstractDungeon.player.frostKing?.render(sb)
                AbstractDungeon.player.frostKing?.update()
            }
        }
    }

    public class Locator : SpireInsertLocator() {
        override fun Locate(ctMethodToPatch: CtBehavior): IntArray {
            val matcher = Matcher.MethodCallMatcher(MonsterGroup::class.java, "render")
            return LineFinder.findInOrder(ctMethodToPatch, matcher)
        }
    }

    @SpirePatch(
            clz = GameActionManager::class,
            method = "callEndOfTurnActions"
    )
    public class CallFrostBoyActions {
        public companion object {
            @JvmStatic
            fun Prefix(__instance: GameActionManager) {
                AbstractDungeon.player.frostKing?.takeTurn()
            }
        }
    }

    @SpirePatch(
            clz = AbstractCreature::class,
            method = "applyStartOfTurnPowers"
    )
    public class MakeFrostBoyIntent {
        public companion object {
            @JvmStatic
            fun Prefix(__instance: AbstractCreature) {
                AbstractDungeon.player.frostKing?.createIntent()
            }
        }
    }

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = "onVictory"
    )
    public class RemoveKingOnWin {
        public companion object  {
            @JvmStatic
            fun Prefix(__instance: AbstractPlayer) {
                if (__instance.frostKing != null) {
                    __instance.frostKing = null
                }
            }
        }
    }

}

var AbstractPlayer.frostKing: FrostBoy?
    get() = FrostBoy.MonsterField.frostKing.get(this)
    set(value) = FrostBoy.MonsterField.frostKing.set(this, value)