package cirno.vfx

import basemod.ReflectionHacks
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.MathUtils
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.vfx.AbstractGameEffect
import com.megacrit.cardcrawl.vfx.ShineSparkleEffect

class MoveImageWithSparkleEffect(private val sX: Float, private val sY: Float, private val dX: Float, private val dY: Float, private val img: Texture, col: Color, private val soundKey: String?, private val finalEffect: AbstractGameEffect?) : AbstractGameEffect() {
    private var cX: Float = 0.toFloat()
    private var cY: Float = 0.toFloat()
    private var yOffset: Float = 0.toFloat()
    private var bounceHeight: Float = 0.toFloat()
    private var playedSfx = false
    private var sparkleTimer = 0.0f

    init {
        cX = sX
        cY = sY
        rotation = 0.0f
        duration = DUR
        color = col
        if (sY > dY) {
            bounceHeight = 600.0f * Settings.scale
        } else {
            bounceHeight = dY - sY + 600.0f * Settings.scale
        }
    }

    override fun update() {
        if (!playedSfx) {
            playedSfx = true
            if (soundKey != null) {
                CardCrawlGame.sound.playA(soundKey, MathUtils.random(-0.5f, 1.0f))
            }
        }
        sparkleTimer -= Gdx.graphics.deltaTime
        if (duration < 0.4f && sparkleTimer < 0.0f) {
            var tmp: ShineSparkleEffect
            for (i in 0 until MathUtils.random(2, 5)) {
                tmp = ShineSparkleEffect(cX, cY + yOffset)
                val tmp2 = color.cpy()
                tmp2.a = 0.0f
                ReflectionHacks.setPrivateInherited(tmp, ShineSparkleEffect::class.java, "color", tmp2)
                AbstractDungeon.effectsQueue.add(tmp)
            }
            sparkleTimer = MathUtils.random(0.005f, 0.01f)
        }
        cX = Interpolation.linear.apply(dX, sX, duration / 0.5f)
        cY = Interpolation.linear.apply(dY, sY, duration / 0.5f)
        if (dX > sX) {
            rotation -= Gdx.graphics.deltaTime * 1000.0f
        } else {
            rotation += Gdx.graphics.deltaTime * 1000.0f
        }
        if (duration > 0.25f) {
            color.a = Interpolation.exp5In.apply(1.0f, 0.0f, (duration - 0.25f) / 0.2f) * Settings.scale
            yOffset = Interpolation.circleIn.apply(bounceHeight, 0.0f, (duration - 0.25f) / 0.25f) * Settings.scale
        } else {
            yOffset = Interpolation.circleOut.apply(0.0f, bounceHeight, duration / 0.25f) * Settings.scale
        }
        duration -= Gdx.graphics.deltaTime
        if (duration < 0.0f) {
            if (finalEffect != null) {
                AbstractDungeon.effectsQueue.add(finalEffect)
            }
            isDone = true
        }
    }

    override fun render(sb: SpriteBatch) {
        sb.setBlendFunction(770, 1)
        sb.color = Color(0.4f, 1.0f, 1.0f, color.a / 5.0f)
        sb.color = color
        sb.draw(img, cX, cY + yOffset)
        sb.setBlendFunction(770, 771)
    }

    override fun dispose() {}

    companion object {
        private val DUR = 0.5f
    }
}