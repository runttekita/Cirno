package cirno.monsters

import cirno.Cirno.Statics.makeID
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

class FrostBoy(private val turns: Int) : AbstractMonster(monsterStrings.NAME, ID, turns, AbstractDungeon.player.drawX - 300f, AbstractDungeon.player.drawY, 250f, 250f, "cirno/images/monsters/frostBoy.png") {

    public companion object {
        val ID = makeID(FrostBoy::class.java)
        val monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID)
    }

    override fun getMove(move: Int) {
    }

    override fun takeTurn() {
    }

}
