package Piruru.stances

import Piruru.abstracts.PiruruStance
import Piruru.powers.Cold
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.actions.GameActionManager
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.stances.WrathStance
import com.megacrit.cardcrawl.vfx.BorderFlashEffect
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect

class Remember : PiruruStance() {

    override fun updateDescription() {

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

    override fun onEndOfTurn() {
        for (m in AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(m, AbstractDungeon.player, Cold(m), 1))
        }
    }

    @SpirePatch(clz = GameActionManager::class, method = "callEndOfTurnActions")
    object TempFixBecauseWtfMegacrit {
        @JvmStatic
        fun Postfix(__instance: GameActionManager) {
            AbstractDungeon.player.stance.onEndOfTurn()
        }
    }

    companion object {
        private var sfxId = -1L
    }
}
