package Piruru.powers;

import Piruru.abstracts.PiruruStance;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;

public class ApexForm extends PiruruStance {
    private static long sfxId = -1L;

    public void onEnterStance() {
        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
        sfxId = CardCrawlGame.sound.playAndLoop("STANCE_LOOP_WRATH");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, WrathStance.STANCE_ID));
    }

    public void stopIdleSfx() {
        if (sfxId != -1L) {
            CardCrawlGame.sound.stop("STANCE_LOOP_WRATH", sfxId);
            sfxId = -1L;
        }
    }
 public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            particleTimer -= Gdx.graphics.getDeltaTime();
            if (particleTimer < 0.0F) {
                particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect());
            }
        }
        particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (particleTimer2 < 0.0F) {
            particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect(WrathStance.STANCE_ID));
        }
    }

    @Override
    public void atStartOfTurn() {
       AbstractDungeon.actionManager.addToBottom(new ApexAction());
    }

    @Override
    public void updateDescription() {

    }
}
