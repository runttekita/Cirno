package cirno.actions

import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.ChemicalX
import com.megacrit.cardcrawl.ui.panels.EnergyPanel

class XCostAction(private var energy: Int, private val callback: () -> Unit): AbstractGameAction() {

    override fun update() {
        if (AbstractDungeon.player.hasRelic(ChemicalX.ID)) {
            energy += ChemicalX.BOOST
        }
        for (i in 0 until energy) {
            callback()
        }
        AbstractDungeon.player.energy.use(EnergyPanel.totalCount)
        isDone = true
    }

}