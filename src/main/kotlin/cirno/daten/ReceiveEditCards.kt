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
		BaseMod.addCard(AttackPlaysItselfMaybe());//delete
		BaseMod.addCard(BlankSpellZone());//delete
		BaseMod.addCard(ColdBane());//delete
		BaseMod.addCard(ColdDraw());//delete
		BaseMod.addCard(ColdScalingAttack());//delete
		BaseMod.addCard(DamageAndCold());//delete
		BaseMod.addCard(Defend());//delete
		BaseMod.addCard(FlameBarrierButCold());//delete
		BaseMod.addCard(FreezeEnemy());//delete
		BaseMod.addCard(FreezeSpell());//delete
		BaseMod.addCard(FrozenDiscard());//delete
		BaseMod.addCard(GlacialForm());//delete
		BaseMod.addCard(MultiTurnFreeze());//delete
		BaseMod.addCard(SpellAttackedFreeze());//delete
		BaseMod.addCard(SpreadCold());//delete
		BaseMod.addCard(Strike());//delete
		BaseMod.addCard(SummonColdDamage());//delete
		BaseMod.addCard(XCostHitRandomCold());//delete
		//autoAddCards
    }

}