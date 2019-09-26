package Piruru.relics;

import Piruru.Piruru;
import Piruru.actions.PeepingAnalyzeAction;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class StarterRelic extends PiruruRelic implements ClickableRelic {
    public static final String ID = Piruru.makeID(StarterRelic.class.getSimpleName());
    private static final RelicTier TIER = RelicTier.STARTER;
    public static final LandingSound SFX = LandingSound.FLAT;


    public StarterRelic() {
        super(ID, TIER, SFX);
        setCounter(0);
    }

    @Override
    public void atBattleStartPreDraw() {
        act(new PeepingAnalyzeAction(cost));
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
    public String getUpdatedDescription() {
        if (counter >= 0) {
            return DESCRIPTIONS[0] + counter + DESCRIPTIONS[1];
        } else {
            return DESCRIPTIONS[0] + "X" + DESCRIPTIONS[1];
        }
    }
}
