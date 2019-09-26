package Piruru.relics;

import Piruru.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;

public class PiruruRelic extends CustomRelic {

    public PiruruRelic(String id, RelicTier tier, LandingSound sfx) {
        super(id, getTexture(id), tier, sfx);
    }

    private static Texture getTexture(String id) {
        String imgName = id.substring((id.indexOf(":"+1)));
        imgName.trim();
        return TextureLoader.getTexture(imgName);
    }

}
