package Piruru.actions;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.cards.blue.Consume;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Please help I'm getting obsessed
 */
public class DrawActionButWithACallback extends AbstractGameAction {
    private Consumer<AbstractCard> callback = null;
    private boolean shuffleCheck = false;

    public DrawActionButWithACallback(int amount, Consumer<AbstractCard> callback) {
        this.callback = callback;
        if (Settings.FAST_MODE) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FASTER;
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
            AbstractDungeon.player.getPower(NoDrawPower.POWER_ID).flash();
            isDone = true;
            return;
        }
        int deckSize = AbstractDungeon.player.drawPile.size();
        int discardSize = AbstractDungeon.player.discardPile.size();
        if (!SoulGroup.isActive()) {
            if (deckSize + discardSize == 0) {
                isDone = true;
            } else if (AbstractDungeon.player.hand.size() == BaseMod.MAX_HAND_SIZE) {
                AbstractDungeon.player.createHandIsFullDialog();
                isDone = true;
            } else {
                if (!shuffleCheck) {
                    int tmp;
                    if (amount + AbstractDungeon.player.hand.size() > 10) {
                        tmp = 10 - (amount + AbstractDungeon.player.hand.size());
                        amount += tmp;
                        AbstractDungeon.player.createHandIsFullDialog();
                    }

                    if (amount > deckSize) {
                        tmp = amount - deckSize;
                        addToTop(new DrawActionButWithACallback(tmp, callback));
                        addToTop(new EmptyDeckShuffleAction());
                        if (deckSize != 0) {
                            addToTop(new DrawActionButWithACallback(deckSize, callback));
                        }
                        amount = 0;
                        isDone = true;
                    }
                    shuffleCheck = true;
                }
                duration -= Gdx.graphics.getDeltaTime();
                if (amount != 0 && duration < 0.0F) {
                    if (Settings.FAST_MODE) {
                        duration = Settings.ACTION_DUR_XFAST;
                    } else {
                        duration = Settings.ACTION_DUR_FASTER;
                    }

                    --amount;
                    if (!AbstractDungeon.player.drawPile.isEmpty()) {
                        AbstractDungeon.player.draw();
                        callback.accept(AbstractDungeon.player.drawPile.getTopCard());
                        AbstractDungeon.player.hand.refreshHandLayout();
                    } else {
                        isDone = true;
                    }
                    if (amount == 0) {
                        isDone = true;
                    }
                }
            }
        }
    }
}