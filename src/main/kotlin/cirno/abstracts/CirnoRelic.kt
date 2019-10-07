package cirno.abstracts

import basemod.abstracts.CustomRelic
import com.badlogic.gdx.graphics.Texture
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

import cirno.Cirno.Statics.textureLoader
import com.megacrit.cardcrawl.relics.AbstractRelic

open class CirnoRelic(id: String, tier: AbstractRelic.RelicTier, sfx: AbstractRelic.LandingSound) : CustomRelic(id, getTexture(id), tier, sfx) {

    fun act(a: AbstractGameAction) {
        AbstractDungeon.actionManager.addToBottom(a)
    }

}


private fun getTexture(id: String): Texture {
    val imgName = id.substring(id.indexOf(":") + 1).trim { it <= ' ' }
    return textureLoader.getTexture(imgName)
}