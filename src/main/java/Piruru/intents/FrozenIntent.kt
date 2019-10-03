package Piruru.intents

import Piruru.Piruluk.Statics.makeID
import Piruru.powers.Frozen
import basemod.ReflectionHacks
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.esotericsoftware.spine.SkeletonMeshRenderer
import com.evacipated.cardcrawl.modthespire.lib.*
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.PowerTip
import com.megacrit.cardcrawl.localization.UIStrings
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost
import javassist.CtBehavior


import Piruru.Piruluk.Statics.textureLoader

/**
 * Shader logic stolen and modified from
 * https://github.com/kiooeht/Hubris/blob/29d2f37cfc4035e29e3567671fbde158833004b2/src/main/java/com/evacipated/cardcrawl/mod/hubris/patches/UndeadRenderPatch.java
 * Thank you papa Kio!
 */
object FrozenIntent {

    public val tip: PowerTip
        get() {
            val tip = PowerTip()
            val strings = CardCrawlGame.languagePack.getUIString("Piruru:FrozenIntent")
            tip.header = strings.TEXT[0]
            tip.body = strings.TEXT[1]
            tip.img = textureLoader.getTexture("Piruru/images/FrozenIntent.png")
            return tip
        }

}
