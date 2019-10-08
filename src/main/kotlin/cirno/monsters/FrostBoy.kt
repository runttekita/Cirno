package cirno.monsters

import cirno.Cirno.Statics.makeID
import cirno.powers.Cold
import com.megacrit.cardcrawl.actions.AbstractGameAction
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction
import com.megacrit.cardcrawl.actions.common.DamageAction
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.monsters.AbstractMonster

class FrostBoy(private val turns: Int) : AbstractMonster(monsterStrings.NAME, ID, turns, -8f, 105f, 200f, 250f, "cirno/images/monsters/frostBoy.png", -1250f, 100f) {

    init {
        damage.add(DamageInfo(this, damageAmt, DamageInfo.DamageType.NORMAL))
        setHp(turns)
        init()
        applyPowers()
        showHealthBar()
        createIntent()
    }

    public companion object {
        val ID = makeID(FrostBoy::class.java)
        val monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID)
        val damageAmt = 12
    }

    enum class MOVES(public val value: Byte) {
        ATTACK(0)
    }

    override fun getMove(move: Int) {
        setMove(FrostBoy.MOVES.ATTACK.value, AbstractMonster.Intent.ATTACK_DEBUFF, damage[0].base)
    }

    override fun takeTurn() {
        when (nextMove) {
            0.toByte() -> {
                val target = AbstractDungeon.getRandomMonster()
                AbstractDungeon.actionManager.addToBottom(AnimateSlowAttackAction(this))
                AbstractDungeon.actionManager.addToBottom(DamageAction(target, damage[FrostBoy.MOVES.ATTACK.value.toInt()], AbstractGameAction.AttackEffect.NONE))
                AbstractDungeon.actionManager.addToBottom(ApplyPowerAction(target, this, Cold(target), 1))
                AbstractDungeon.actionManager.addToBottom(DamageAction(this, DamageInfo(this, 1, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE))
            }
        }
        rollMove()
    }

}
