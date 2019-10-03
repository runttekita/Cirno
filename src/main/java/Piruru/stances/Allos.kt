package Piruru.stances

import Piruru.abstracts.PiruruStance
import Piruru.interfaces.OnRefreshHand
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.characters.AbstractPlayer
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.stances.WrathStance
import com.megacrit.cardcrawl.vfx.BorderFlashEffect
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect
import javassist.CannotCompileException
import javassist.expr.ExprEditor
import javassist.expr.MethodCall

public class Allos : PiruruStance(), OnRefreshHand {

    override fun updateDescription() {
        description = stanceStrings.DESCRIPTION[0]
    }

    override fun onEnterStance() {
        CardCrawlGame.sound.play("STANCE_ENTER_WRATH")
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH")
        AbstractDungeon.effectsQueue.add(BorderFlashEffect(Color.SCARLET, true))
        AbstractDungeon.effectsQueue.add(StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, WrathStance.STANCE_ID))
    }

    override fun stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId)
            sfxId = -1L
        }
    }

    override fun updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            particleTimer -= Gdx.graphics.deltaTime
            if (particleTimer < 0.0f) {
                particleTimer = 0.05f
                AbstractDungeon.effectsQueue.add(WrathParticleEffect())
            }
        }
        particleTimer2 -= Gdx.graphics.deltaTime
        if (particleTimer2 < 0.0f) {
            particleTimer2 = MathUtils.random(0.3f, 0.4f)
            AbstractDungeon.effectsQueue.add(StanceAuraEffect(WrathStance.STANCE_ID))
        }
    }

    override fun atDamageReceive(damage: Float, damageType: DamageInfo.DamageType?): Float {
        return if (AbstractDungeon.player.hand.isEmpty) {
            0f
        } else damage
    }

    override fun onRefreshHand() {
        AbstractDungeon.onModifyPower()
    }

    @SpirePatch(clz = DiscardAtEndOfTurnAction::class, method = "update")
    object NoDiscardHandAllosPatch {
        @JvmStatic
        fun Instrument(): ExprEditor {
            return object : ExprEditor() {
                @Throws(CannotCompileException::class)
                override fun edit(m: MethodCall?) {
                    if (m!!.methodName == "hasPower") {
                        m.replace("\$_ = \$proceed($$) || " + AbstractDungeon::class.java.name + ".player.stance instanceof " + Allos::class.java.name + ";")
                    }
                }
            }
        }
    }

    companion object {
        private var sfxId = -1L
    }

}
