package Cirno.ui

import Cirno.Cirno
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import reina.yui.YuiClickableObject;

class ARTSZone : YuiClickableObject(Cirno.textureLoader.getTexture("Piruru/images/ui/ARTS.jpg"), 1.32f, 86.5f) {
    public var storedCard: AbstractCard? = null

    override fun onClick() {
    }

    override fun onUnhover() {
    }

    override fun onHover() {
        super.onHover()
        if (AbstractDungeon.player.hoveredCard != null) {
            storedCard = AbstractDungeon.player.hoveredCard
            AbstractDungeon.player.hand.removeCard(AbstractDungeon.player.hoveredCard)
            AbstractDungeon.player.isDraggingCard = false
            AbstractDungeon.player.isHoveringDropZone = false
            AbstractDungeon.player.inSingleTargetMode = false
            AbstractDungeon.player.cardInUse = null
            AbstractDungeon.player.hoveredCard = null
            storedCard?.drawScale = 0.25f
        }
    }

    override fun render(sb: SpriteBatch) {
        super.render(sb)
        if (storedCard != null) {
            storedCard!!.current_x = x
            storedCard!!.current_y = y
            storedCard!!.render(sb)
            storedCard!!.update()
        }
    }
}