package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DrawPileToDiscardPileAction extends AbstractGameAction {
    private AbstractCard c;

    DrawPileToDiscardPileAction(AbstractCard c) {
        this.c = c;
    }

    @Override
    public void update() {
        AbstractDungeon.player.drawPile.removeCard(c);
        AbstractDungeon.player.discardPile.addToBottom(c);
        c.target_x = Settings.WIDTH / 2.0F + 200.0F * Settings.scale;
        c.current_y = -200.0F * Settings.scale;
        c.target_y = Settings.HEIGHT / 2.0F;
        c.targetAngle = 0.0F;
        c.lighten(false);
        c.drawScale = 0.12F;
        c.targetDrawScale = 0.75F;;
        isDone = true;
    }
}
