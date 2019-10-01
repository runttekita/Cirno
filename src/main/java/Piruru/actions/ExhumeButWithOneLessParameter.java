package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhumeButWithOneLessParameter extends AbstractGameAction {

    @Override
    public void update() {
        AbstractDungeon.actionManager.addToBottom(new ExhumeAction(false));
        isDone = true;
    }
    
}
