package cirno.characters

import cirno.Cirno
import cirno.abstracts.CirnoCard
import cirno.cards.BlankSpellZone
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster
import com.megacrit.cardcrawl.vfx.cardManip.CardFlashVfx
import reina.yui.YuiClickableObject

class SpellZone() : YuiClickableObject(Cirno.textureLoader.getTexture("uwu"), 0f, 0f) {
    lateinit var storedCard: AbstractCard
    private val scale = 0.50f

    init {
        storeCard(BlankSpellZone())
    }

    public fun storeCard(spellCard: AbstractCard) {
        if (spellCard is CirnoCard) {
            spellCard.drawScale = scale
            storedCard = spellCard
        } else {
            println("wtf?")
        }
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

    public fun triggerEffect(info: DamageInfo? = null) {
        AbstractDungeon.effectList.add(CardFlashVfx(storedCard))
        if (info != null && info.owner is AbstractMonster) {
            (storedCard as CirnoCard).spellEffect(info)
        } else {
            (storedCard as CirnoCard).spellEffect()
        }
        storedCard.moveToDiscardPile()
        AbstractDungeon.player.discardPile.addToBottom(storedCard)
        storeCard(BlankSpellZone())
    }

    override fun render(sb: SpriteBatch) {
        super.render(sb)
        storedCard.current_x = x + hitbox.width / 2
        storedCard.current_y = y + hitbox.height / 2
        storedCard.render(sb)
    }

}