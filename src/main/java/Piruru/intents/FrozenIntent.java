package Piruru.intents;

import Piruru.powers.Frozen;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;
import javassist.CtBehavior;

import java.util.Arrays;

import static Piruru.Piruru.textureLoader;

// *************************
// Shader logic stolen and modified from:
// https://github.com/kiooeht/Hubris/blob/29d2f37cfc4035e29e3567671fbde158833004b2/src/main/java/com/evacipated/cardcrawl/mod/hubris/patches/UndeadRenderPatch.java
// Thank you papa Kio!
// *************************
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
                return SpireReturn.Return(textureLoader.getTexture(
                        "Piruru/images/intents/Frozen.png"
                ));
            }
            ;
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
        tip.header = strings.TEXT[0];
        tip.body = strings.TEXT[1];
        tip.img = textureLoader.getTexture("Piruru/images/FrozenIntent.png");
        return tip;
    }

    @SpirePatch(
            clz = AbstractMonster.class,
            method = "render"
    )
    public static class FrozenIntentShading {
        private static ShaderProgram shader = new ShaderProgram(
                Gdx.files.internal("Piruru/shaders/frozen/vertexShader.vs"),
                Gdx.files.internal("Piruru/shaders/frozen/fragShader.fs")
                );

       private static ShapeRenderer renderer = new ShapeRenderer();


        @SpireInsertPatch(
                locator = LocatorImageStart.class,
                localvars = {"atlas"}
        )
        public static void InsertImageStart(AbstractMonster __instance, SpriteBatch sb, TextureAtlas atlas) {
            if (atlas == null && !(__instance instanceof Hexaghost)) {
                Frozen frozen = (Frozen) __instance.getPower(Frozen.POWER_ID);
                if (frozen != null) {
                    shader.begin();
                    shader.setUniformf("shadeTimer", frozen.shaderTimer);
                    sb.setShader(shader);
                }
            }
        }


        @SpireInsertPatch(
                locator=LocatorImageEnd.class,
                localvars={"atlas"}
        )
        public static void InsertImageEnd(AbstractMonster __instance, SpriteBatch sb, TextureAtlas atlas)
        {
            if (atlas == null && __instance.hasPower(Frozen.POWER_ID) && !(__instance instanceof Hexaghost)) {
                sb.setShader(null);
                shader.end();
            }
        }

        @SpireInsertPatch(
                locator=LocatorSkeletonStart.class
        )
        public static void InsertSkeletonStart(AbstractMonster __instance, SpriteBatch sb)
        {
            Frozen frozen = (Frozen) __instance.getPower(Frozen.POWER_ID);
            if (frozen != null && !(__instance instanceof Hexaghost)) {
                shader.begin();
                shader.setUniformf("shadeTimer", frozen.shaderTimer);
                CardCrawlGame.psb.setShader(shader);
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.setColor(Color.BLUE);
                float x = __instance.drawX;
                float y = __instance.drawY;
                renderer.rect(x, y, __instance.hb_w, __instance.hb_h);
            }
        }

        @SpireInsertPatch(
                locator=LocatorSkeletonEnd.class
        )
        public static void InsertSkeletonEnd(AbstractMonster __instance, SpriteBatch sb)
        {
            if (__instance.hasPower(Frozen.POWER_ID) && !(__instance instanceof Hexaghost)) {
                CardCrawlGame.psb.setShader(null);
                shader.end();
                renderer.end();
            }
        }


        private static class LocatorImageStart extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "damageFlash");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        private static class LocatorImageEnd extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "damageFlash");
                return Arrays.copyOfRange(LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher), 1, 2);
            }
        }

        private static class LocatorSkeletonStart extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SkeletonMeshRenderer.class, "draw");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        private static class LocatorSkeletonEnd extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(PolygonSpriteBatch.class, "end");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
