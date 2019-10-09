package cirno.actions

import cirno.monsters.FrostBoy
import cirno.monsters.frostKing
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class SummonFrostGuy(private val turns: Int) : AbstractGameAction() {

    override fun update() {
        val monster = AbstractDungeon.player.frostKing
        if (monster != null) {
            if (monster.currentHealth + turns > monster.maxHealth) {
                monster.maxHealth += turns
            }
            monster.heal(turns)
            isDone = true
            return
        }
        AbstractDungeon.player.frostKing = FrostBoy(turns)
        isDone = true
    }

}