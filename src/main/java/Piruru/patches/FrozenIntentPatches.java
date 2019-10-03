package Piruru.patches;

import Piruru.Piruluk;
import Piruru.intents.FrozenIntent;
import Piruru.powers.Frozen;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;
import javassist.CtBehavior;

public class FrozenIntentPatches {

    public static class FrozenIntentEnum {
        @SpireEnum
        public static AbstractMonster.Intent FROZEN;
    }


    @SpirePatch(
            clz = AbstractMonster.class,
            method = "getIntentImg"
    )
    public static class FrozenIntentImage {
        public static SpireReturn<Texture> Prefix(AbstractMonster __instance) {
            if (__instance.intent == FrozenIntentEnum.FROZEN) {
                return SpireReturn.Return(Piruluk.Statics.getTextureLoader().getTexture(
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
            if (__instance.intent == FrozenIntentEnum.FROZEN) {
                ReflectionHacks.setPrivate(__instance, AbstractMonster.class, "intentTip", FrozenIntent.INSTANCE.getTip());
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
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
                Frozen frozen = (Frozen) __instance.getPower(Piruluk.Statics.makeID(Frozen.class));
                if (frozen != null) {
                    shader.begin();
                    shader.setUniformf("shadeTimer", frozen.getShaderTimer());
                    sb.setShader(shader);
                }
            }
        }


        @SpireInsertPatch(
                locator = LocatorImageEnd.class,
                localvars = {"atlas"}
        )
        public static void InsertImageEnd(AbstractMonster __instance, SpriteBatch sb, TextureAtlas atlas) {
            if (atlas == null && __instance.hasPower(Piruluk.Statics.makeID(Frozen.class)) && !(__instance instanceof Hexaghost)) {
                sb.setShader(null);
                shader.end();
            }
        }

        @SpireInsertPatch(
                locator = LocatorSkeletonStart.class
        )
        public static void InsertSkeletonStart(AbstractMonster __instance, SpriteBatch sb) {
            Frozen frozen = (Frozen) __instance.getPower(Piruluk.Statics.makeID(Frozen.class));
            if (frozen != null && !(__instance instanceof Hexaghost)) {
                shader.begin();
                shader.setUniformf("shadeTimer", frozen.getShaderTimer());
                CardCrawlGame.psb.setShader(shader);
                renderer.begin(ShapeRenderer.ShapeType.Filled);
                renderer.setColor(Color.BLUE);
                float x = __instance.drawX;
                float y = __instance.drawY;
                renderer.rect(x, y, __instance.hb_w, __instance.hb_h);
            }
        }

        @SpireInsertPatch(
                locator = LocatorSkeletonEnd.class
        )
        public static void InsertSkeletonEnd(AbstractMonster __instance, SpriteBatch sb) {
            if (__instance.hasPower(Piruluk.Statics.makeID(Frozen.class)) && !(__instance instanceof Hexaghost)) {
                CardCrawlGame.psb.setShader(null);
                shader.end();
                renderer.end();
            }
        }


        private static class LocatorImageStart extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "atlas");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        private static class LocatorImageEnd extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "isDying");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        private static class LocatorSkeletonStart extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SkeletonMeshRenderer.class, "draw");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        private static class LocatorSkeletonEnd extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(PolygonSpriteBatch.class, "end");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
