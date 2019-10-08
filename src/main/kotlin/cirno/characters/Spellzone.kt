package cirno.characters

import cirno.Cirno
import cirno.cards.BlankSpellZone
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.cards.AbstractCard
import reina.yui.YuiClickableObject

class Spellzone(x: Float, y: Float) : YuiClickableObject(Cirno.textureLoader.getTexture("uwu"), x, y) {
    lateinit var storedCard: AbstractCard

    init {
        val card = BlankSpellZone()
        card.current_x = x
        card.current_y = y
        card.drawScale = 0.25f
        storedCard = card
    }

    override fun onUnhover() {
    }

    override fun onClick() {
    }



    override fun render(sb: SpriteBatch) {
        super.render(sb)
        storedCard.render(sb)
        storedCard.update()
    }

}