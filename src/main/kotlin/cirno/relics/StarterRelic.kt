package cirno.relics

import cirno.Cirno.Statics.makeID
import cirno.abstracts.CirnoRelic
import cirno.powers.AttackBackFreeze
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.relics.AbstractRelic

class StarterRelic : CirnoRelic(id, rarity, landingSound) {

    companion object {
        private const val times = 1
        private val id = makeID(StarterRelic::class.java)
        private val rarity = AbstractRelic.RelicTier.STARTER
        private val landingSound = LandingSound.MAGICAL
    }

    override fun atBattleStart() {
        act(ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, AttackBackFreeze(AbstractDungeon.player, times), times))
    }

    override fun getUpdatedDescription(): String {
        return DESCRIPTIONS[0]
    }
}