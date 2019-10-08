package cirno.characters

import cirno.Cirno
import cirno.cards.BlankSpellZone
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.cards.AbstractCard
import reina.yui.YuiClickableObject

class SpellZone() : YuiClickableObject(Cirno.textureLoader.getTexture("uwu"), 0f, 0f) {
    lateinit var storedCard: AbstractCard
    private val scale = 0.50f

    init {
        storeCard(BlankSpellZone())
    }

    private fun storeCard(spellCard: AbstractCard) {
        spellCard.drawScale = scale
        storedCard = spellCard
    }

    override fun onHover() {
        super.onHover()
        storedCard.drawScale = 0.8f
    }
    override fun onUnhover() {
        storedCard.drawScale = scale
    }

    override fun onClick() {
    }



    override fun render(sb: SpriteBatch) {
        super.render(sb)
        storedCard.current_x = x + hitbox.width / 2
        storedCard.current_y = y + hitbox.height / 2
        storedCard.render(sb)
    }

}