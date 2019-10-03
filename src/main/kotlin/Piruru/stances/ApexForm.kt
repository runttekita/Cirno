package Piruru.stances

import Piruru.Piruluk.Statics.makeID
import Piruru.abstracts.PiruruStance
import Piruru.actions.RecoverAction
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.localization.CardStrings
import com.megacrit.cardcrawl.stances.WrathStance
import com.megacrit.cardcrawl.vfx.BorderFlashEffect
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect


class ApexForm : PiruruStance() {

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

    override fun atStartOfTurn() {
        AbstractDungeon.actionManager.addToTop(RecoverAction(RECOVER_AMT
        ) { list ->
            list.forEach { c ->
                c.exhaust = true
                c.rawDescription += cardStrings.DESCRIPTION
                c.initializeDescription()
                c.cost = 0
                c.costForTurn = 0
            }
        })
    }

    override fun updateDescription() {
        description = stanceStrings.DESCRIPTION[0] + RECOVER_AMT + stanceStrings.DESCRIPTION[1]
    }

    companion object {
        private val RECOVER_AMT = 2
        private val cardStrings = CardCrawlGame.languagePack.getCardStrings(makeID("LiterallyJustTheWordExhaust"))
        private var sfxId = -1L
    }
}
