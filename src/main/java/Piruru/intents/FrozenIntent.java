package Piruru.intents;

import Piruru.TextureLoader;
import basemod.BaseMod;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrozenIntent {
    @SpireEnum
    public static AbstractMonster.Intent FROZEN;

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "getIntentImg"
    )
    public static class FrozenIntentImage {
        public static SpireReturn<Texture> Prefix(AbstractMonster __instance) {
            if (__instance.intent == FROZEN) {
                return SpireReturn.Return(TextureLoader.getTexture(
                        "Piruru/images/intents/Frozen.png"
                ));
            };
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "updateIntentTip"
    )
    public static class FrozenIntentTip {
        public static SpireReturn Prefix(AbstractMonster __instance) {
            if (__instance.intent == FROZEN) {
                ReflectionHacks.setPrivate(__instance, AbstractMonster.class, "intentTip", getTip());
                return SpireReturn.Return(null);
            }
        return SpireReturn.Continue();
        }
    }

    private static PowerTip getTip() {
        PowerTip tip = new PowerTip();
        UIStrings strings = CardCrawlGame.languagePack.getUIString("Piruru:FrozenIntent");
        tip.header =  strings.TEXT[0];
        tip.body = strings.TEXT[1];
        tip.img = TextureLoader.getTexture("Piruru/images/FrozenIntent.png");
        return tip;
    }
}
