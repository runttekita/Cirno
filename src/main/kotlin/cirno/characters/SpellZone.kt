package cirno.characters

import cirno.Cirno
import cirno.cards.BlankSpellZone
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.cards.AbstractCard
import reina.yui.YuiClickableObject

class SpellZone() : YuiClickableObject(Cirno.textureLoader.getTexture("uwu"), 0f, 0f) {
    lateinit var storedCard: AbstractCard
    private val scale = 0.25f

    init {
        val card = BlankSpellZone()
        card.current_x = x
        card.current_y = y
        card.drawScale = scale
        storedCard = card
    }

    fun storeCard(spellCard: AbstractCard) {
        spellCard.current_x = x
        spellCard.current_y = y
        spellCard.drawScale = scale
        storedCard = spellCard
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