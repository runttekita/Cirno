package Piruru.relics;

import Piruru.Piruluk;
import Piruru.abstracts.PiruruRelic;
import Piruru.actions.PeepingAnalyzeAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;

public class StarterRelic extends PiruruRelic implements ClickableRelic {
    public static final String ID = Piruluk.makeID(StarterRelic.class);
    public static final LandingSound SFX = LandingSound.FLAT;
    private static final RelicTier TIER = RelicTier.STARTER;

    public StarterRelic() {
        super(ID, TIER, SFX);
        setCounter(0);
    }

    @Override
    public void atBattleStartPreDraw() {
        act(new PeepingAnalyzeAction(counter));
    }

    @Override
    public void onRightClick() {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            if (counter < 3) {
                counter++;
            } else {
                counter = -1;
            }
        }
    }

    @Override
    public void update() {
        super.update();
        tips.clear();
        description = getUpdatedDescription();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public void renderCounter(SpriteBatch sb, boolean inTopPanel) {
        super.renderCounter(sb, inTopPanel);
        if (counter == -1) {
            if (inTopPanel) {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, "X", this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
            } else {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, "X", this.currentX + 30.0F * Settings.scale, this.currentY - 7.0F * Settings.scale, Color.WHITE);
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        if (counter >= 0) {
            return DESCRIPTIONS[0] + counter + DESCRIPTIONS[1];
        } else {
            return DESCRIPTIONS[0] + "X" + DESCRIPTIONS[1];
        }
    }
}
