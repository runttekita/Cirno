package Piruru.actions;

import Piruru.powers.Cold;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpreadColdAction extends AbstractGameAction {
    private AbstractCreature target;
    private int amount;

    public SpreadColdAction(AbstractCreature target, int amount) {
        this.target = target;
        this.amount = amount;
    }

    //TODO Refactor this so it isn't ugly as sin
    @Override
    public void update() {
        if (target.hasPower(Cold.POWER_ID) && target instanceof AbstractMonster) {
            int slot = AbstractDungeon.getMonsters().monsters.indexOf((AbstractMonster) target);
            AbstractMonster left = null;
            if (slot > 0) left = AbstractDungeon.getMonsters().monsters.get(slot - 1);
            AbstractMonster right = null;
            if (slot < AbstractDungeon.getMonsters().monsters.size() - 1)
                right = AbstractDungeon.getMonsters().monsters.get(slot + 1);
            if (left != null && !left.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                        (left, AbstractDungeon.player, new Cold(left, amount)));
            }
            if (right != null && !right.isDeadOrEscaped()) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction
                        (right, AbstractDungeon.player, new Cold(right, amount)));
            }
        }
        isDone = true;
    }
}
