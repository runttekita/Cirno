package Piruru.relics;

import Piruru.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class PiruruRelic extends CustomRelic {

    public PiruruRelic(String id, RelicTier tier, LandingSound sfx) {
        super(id, getTexture(id), tier, sfx);
    }

    private static Texture getTexture(String id) {
        String imgName = id.substring((id.indexOf(":"+1))).trim();
        return TextureLoader.getTexture(imgName);
    }

    void act(AbstractGameAction a) {
        AbstractDungeon.actionManager.addToBottom(a);
    }

}
