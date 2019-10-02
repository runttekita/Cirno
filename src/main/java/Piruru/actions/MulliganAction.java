package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class MulliganAction extends AbstractGameAction {
    private int amount;

    public MulliganAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        for (int i = 0; i < amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
        isDone = true;
    }
}
