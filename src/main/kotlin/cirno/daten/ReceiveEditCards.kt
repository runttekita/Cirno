package cirno.daten

import basemod.BaseMod
import cirno.cards.*

/**
 * But Reina, I can hear you ask, why aren't you using autoAddCards from Kio? Well the answer is simple
 * I'm too lazy to copy paste all that stuff and make like 90 classes and make.sh does this for me.
 */
class ReceiveEditCards {

    init {
		BaseMod.addCard(AoECold());//delete
		BaseMod.addCard(AoEDamageToFrozenEnemies());//delete
		BaseMod.addCard(AoEFreeze());//delete
		BaseMod.addCard(AoESlow());//delete
		BaseMod.addCard(AttackBlock());//delete
		BaseMod.addCard(AttackDrawDamage());//delete
		BaseMod.addCard(AttackPlaysItselfMaybe());//delete
		BaseMod.addCard(BlankSpellZone());//delete
		BaseMod.addCard(BlockReduceCost());//delete
		BaseMod.addCard(CardDrawReduce());//delete
		BaseMod.addCard(CardsDrawnThisCombatDamage());//delete
		BaseMod.addCard(ClashForSpells());//delete
		BaseMod.addCard(ColdBane());//delete
		BaseMod.addCard(ColdDraw());//delete
		BaseMod.addCard(ColdEchoForm());//delete
		BaseMod.addCard(ColdScalingAttack());//delete
		BaseMod.addCard(DamageAndCold());//delete
		BaseMod.addCard(DamageForEachSpell());//delete
		BaseMod.addCard(DealDamageToColdEnemiesOnDraw());//delete
		BaseMod.addCard(Defend());//delete
		BaseMod.addCard(DrawFreezeAll());//delete
		BaseMod.addCard(DrawHeal());//delete
		BaseMod.addCard(DrawThenPutBack());//delete
		BaseMod.addCard(DrawXReduceX());//delete
		BaseMod.addCard(FlameBarrierButCold());//delete
		BaseMod.addCard(FreezeEnemy());//delete
		BaseMod.addCard(FreezeSpell());//delete
		BaseMod.addCard(FrozenDiscard());//delete
		BaseMod.addCard(GlacialForm());//delete
		BaseMod.addCard(IncreaseAttackDamageDraw());//delete
		BaseMod.addCard(MultiTurnFreeze());//delete
		BaseMod.addCard(RemoveColdEnergy());//delete
		BaseMod.addCard(ShiverAttackCold());//delete
		BaseMod.addCard(ShiverScaling());//delete
		BaseMod.addCard(SpellAttackedCold());//delete
		BaseMod.addCard(SpellAttackedFreeze());//delete
		BaseMod.addCard(SpellCardDrawDamage());//delete
		BaseMod.addCard(SpellEnergyFreeze());//delete
		BaseMod.addCard(SpreadCold());//delete
		BaseMod.addCard(Strike());//delete
		BaseMod.addCard(SummonColdDamage());//delete
		BaseMod.addCard(SwapPilesGainBlock());//delete
		BaseMod.addCard(UpgradeShiveringCards());//delete
		BaseMod.addCard(XCostHitRandomCold());//delete
		//autoAddCards
    }

}