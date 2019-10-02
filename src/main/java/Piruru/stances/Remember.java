package Piruru.stances;

import Piruru.abstracts.PiruruStance;
import Piruru.powers.Cold;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;

public class Remember extends PiruruStance {
    private static long sfxId = -1L;

    @Override
    public void updateDescription() {

    }

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
    public void onEndOfTurn() {
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new Cold(m), 1));
        }
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method = "callEndOfTurnActions"
    )
    public static class TempFixBecauseWtfMegacrit {
        public static void Postfix(GameActionManager __instance) {
            AbstractDungeon.player.stance.onEndOfTurn();
        }
    }
}
