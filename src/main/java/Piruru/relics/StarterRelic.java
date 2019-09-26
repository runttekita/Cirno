package Piruru.relics;

import Piruru.Piruru;
import Piruru.actions.PeepingAnalyzeAction;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class StarterRelic extends PiruruRelic implements ClickableRelic {
    public static final String ID = Piruru.makeID(StarterRelic.class.getSimpleName());
    private static final RelicTier TIER = RelicTier.STARTER;
    public static final LandingSound SFX = LandingSound.FLAT;
    private int cost = 0;


    public StarterRelic() {
        super(ID, TIER, SFX);
        setCounter(cost);
    }

    @Override
    public void atBattleStartPreDraw() {
        act(new PeepingAnalyzeAction(cost));
    }

    @Override
    public void onRightClick() {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            if (cost < 4) {
                cost++;
            } else {
                cost = 0;
            }
            if (cost < 4) {
                setCounter(cost);
            } else {
                setCounter(-1);
            }
        }
    }
}
