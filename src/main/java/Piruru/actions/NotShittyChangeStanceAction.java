package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.CannotChangeStancePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class NotShittyChangeStanceAction extends AbstractGameAction {
    private AbstractStance newStance;

    public NotShittyChangeStanceAction(AbstractStance newStance) {
        this.newStance = newStance;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower(CannotChangeStancePower.POWER_ID)) {
            isDone = true;
            return;
        }

        AbstractStance oldStance = AbstractDungeon.player.stance;
        if (!oldStance.ID.equals(newStance.ID)) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                p.onChangeStance(oldStance, newStance);
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                r.onChangeStance(oldStance, newStance);
            }
            oldStance.onExitStance();
            AbstractDungeon.player.stance = newStance;
            newStance.onEnterStance();
            AbstractDungeon.actionManager.stancesSwitchedThisTurn.add(newStance);
            AbstractDungeon.player.switchedStance();
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                c.triggerExhaustedCardsOnStanceChange(newStance);
            }
           AbstractDungeon.player.onStanceChange(newStance.ID);
        }
        AbstractDungeon.onModifyPower();
        isDone = true;
    }
}
