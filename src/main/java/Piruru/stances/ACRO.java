package Piruru.stances;

import Piruru.abstracts.PiruruStance;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.HitboxRightClick;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;

public class ACRO extends PiruruStance {
    private static final int DRAW_CAP = 6;
    public static boolean usedDraw = false;
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
        usedDraw = false;
    }

    @Override
    public void onExitStance() {
        usedDraw = false;
    }

    @Override
    public void updateDescription() {
        description = getStanceStrings().DESCRIPTION[0] + DRAW_CAP + getStanceStrings().DESCRIPTION[1] + DRAW_CAP + getStanceStrings().DESCRIPTION[2];
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "combatUpdate"
    )
    public static class AcroDrawPatch {
        public static void Postfix(AbstractPlayer __instance) {
            if (AbstractDungeon.player.stance instanceof ACRO) {
                if (HitboxRightClick.rightClicked.get(__instance.hb) && !usedDraw && AbstractDungeon.actionManager.phase == GameActionManager.Phase.WAITING_ON_USER) {
                    AbstractDungeon.actionManager.addToBottom(new ExpertiseAction(__instance, DRAW_CAP));
                    usedDraw = true;
                }
            }
        }
    }
}
