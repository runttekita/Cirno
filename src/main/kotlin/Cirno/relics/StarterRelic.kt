package Cirno.relics

import Cirno.Cirno
import Cirno.abstracts.PiruruRelic
import Cirno.actions.MoveNCostToDiscard
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.helpers.FontHelper
import com.megacrit.cardcrawl.helpers.PowerTip
import com.megacrit.cardcrawl.relics.AbstractRelic

class StarterRelic : PiruruRelic(ID, TIER, SFX), ClickableRelic {
    init {
        setCounter(0)
    }

    override fun atBattleStartPreDraw() {
        act(MoveNCostToDiscard(counter))
    }

    override fun onRightClick() {
        if (!AbstractDungeon.actionManager.turnHasEnded) {
            if (counter < 3) {
                counter++
            } else {
                counter = -1
            }
        }
    }

    override fun update() {
        super.update()
        tips.clear()
        description = updatedDescription
        tips.add(PowerTip(name, description))
        initializeTips()
    }

    override fun renderCounter(sb: SpriteBatch, inTopPanel: Boolean) {
        super.renderCounter(sb, inTopPanel)
        if (counter == -1) {
            if (inTopPanel) {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, "X", this.currentX + 30.0f * Settings.scale, this.currentY - 7.0f * Settings.scale, Color.WHITE)
            } else {
                FontHelper.renderFontRightTopAligned(sb, FontHelper.topPanelInfoFont, "X", this.currentX + 30.0f * Settings.scale, this.currentY - 7.0f * Settings.scale, Color.WHITE)
            }
        }
    }

    override fun getUpdatedDescription(): String {
        return if (counter >= 0) {
            DESCRIPTIONS[0] + counter + DESCRIPTIONS[1]
        } else {
            DESCRIPTIONS[0] + "X" + DESCRIPTIONS[1]
        }
    }

    companion object {
        val ID = Cirno.makeID(StarterRelic::class.java)
        val SFX: AbstractRelic.LandingSound = AbstractRelic.LandingSound.FLAT
        private val TIER = AbstractRelic.RelicTier.STARTER
    }
}
