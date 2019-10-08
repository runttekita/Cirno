package cirno.actions

import cirno.monsters.FrostBoy
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon

class SummonFrostGuy(private val turns: Int) : AbstractGameAction() {

    override fun update() {
        for (monster in AbstractDungeon.getMonsters().monsters) {
            if (monster.id == FrostBoy.ID) {
                if (monster.currentHealth + turns > monster.maxHealth) {
                    monster.maxHealth += turns
                }
                monster.heal(turns)
                isDone = true
                return
            }
        }
        AbstractDungeon.getMonsters().addMonster(AbstractDungeon.getMonsters().monsters.size, FrostBoy(turns))
        isDone = true
    }

}