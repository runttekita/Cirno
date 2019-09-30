package Piruru.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * This is literally just Hologram's action.
 * Why?
 * I can't stand the fucking name.
 * @see com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction
 * Also added a callback functionality I guess.
 */
public class RecoverAction extends AbstractGameAction {
    private int amount;
    private Consumer<ArrayList<AbstractCard>> callback = null;
    private AbstractPlayer player;

    public RecoverAction(int amount) {
        this.amount = amount;
        this.player = AbstractDungeon.player;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public RecoverAction(int amount, Consumer<ArrayList<AbstractCard>> callback) {
        this(amount);
        this.callback = callback;
    }

    @Override
    public void update() {
        if (player.discardPile.isEmpty()) {
            isDone = true;
            return;
        }
        if (duration == Settings.ACTION_DUR_FASTER) {
            if (player.discardPile.size() < amount && player.hand.size() + player.discardPile.size() <= BaseMod.MAX_HAND_SIZE) {
                if (callback != null) {
                    callback.accept(player.discardPile.group);
                }
                for (AbstractCard c : player.discardPile.group) {
                    if (player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                        player.hand.addToHand(c);
                        player.discardPile.removeCard(c);
                    }
                }
                player.hand.refreshHandLayout();
                isDone = true;
                return;
            }

            if (player.discardPile.size() < amount) {
                AbstractDungeon.gridSelectScreen.open(player.discardPile, player.discardPile.size(), false, "");
            }

            AbstractDungeon.gridSelectScreen.open(player.discardPile, amount, false, "");
            System.out.println("asf;as;lfkas" + amount);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            if (callback != null) {
                callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
            }
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                player.hand.addToHand(c);
                player.discardPile.removeCard(c);
            }
            player.hand.refreshHandLayout();
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        isDone = true;
    }
}
