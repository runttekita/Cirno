package Piruru.actions

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import java.util.function.Consumer
import java.util.function.Predicate

class ReturnAction : MoveCardsAction {

    constructor(predicate: Predicate<AbstractCard>, amount: Int) : super(AbstractDungeon.player.exhaustPile, AbstractDungeon.player.discardPile, predicate, amount) {}

    constructor(predicate: Predicate<AbstractCard>, amount: Int, callback: Consumer<List<AbstractCard>>) : super(AbstractDungeon.player.exhaustPile, AbstractDungeon.player.discardPile, predicate, amount, callback) {}
}
