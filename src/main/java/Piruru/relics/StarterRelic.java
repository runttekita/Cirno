package Piruru.relics;

import Piruru.Piruru;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class StarterRelic extends PiruruRelic {
    public static final String ID = Piruru.makeID(StarterRelic.class.getSimpleName());
    private static final RelicTier TIER = RelicTier.STARTER;
    public static final LandingSound SFX = LandingSound.FLAT;


    public StarterRelic() {
        super(ID, TIER, SFX);
    }

}
