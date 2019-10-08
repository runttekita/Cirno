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
		BaseMod.addCard(AoEFreeze());//delete
		BaseMod.addCard(AttackBlock());//delete
		BaseMod.addCard(ColdBane());//delete
		BaseMod.addCard(ColdDraw());//delete
		BaseMod.addCard(DamageAndCold());//delete
		BaseMod.addCard(Defend());//delete
		BaseMod.addCard(FlameBarrierButCold());//delete
		BaseMod.addCard(FreezeEnemy());//delete
		BaseMod.addCard(SpreadCold());//delete
		BaseMod.addCard(Strike());//delete
		//autoAddCards
    }

}