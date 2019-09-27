package Piruru.actions;

import Piruru.powers.Frozen;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FreezeMonsterAction extends AbstractGameAction {

    public FreezeMonsterAction(AbstractMonster target, AbstractCreature source) {
        this(target, source, 1);
    }

    public FreezeMonsterAction(AbstractMonster target, AbstractCreature source, int amount) {
        target = target;
        source = source;
        amount = amount;
        actionType = AbstractGameAction.ActionType.DEBUFF;
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, source, new Frozen((AbstractMonster) target)));
        }
        tickDuration();
    }
}
