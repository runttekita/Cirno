package Piruru.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * This is literally just Hologram's action.
 * Why?
 * I can't stand the fucking name.
 *
 * @see com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction
 * Also added a callback functionality I guess.
 */
public class RecoverAction extends AbstractGameAction {
    private int amount;
    private Consumer<ArrayList<AbstractCard>> callback = null;
    private AbstractPlayer player;
    private AbstractCard card = null;

    public RecoverAction(int amount) {
        this.amount = amount;
        this.player = AbstractDungeon.player;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public RecoverAction(int amount, Consumer<ArrayList<AbstractCard>> callback) {
        this(amount);
        this.callback = callback;
    }

    public RecoverAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        if (player.discardPile.isEmpty()) {
            isDone = true;
            return;
        }
        if (card != null) {
            player.hand.addToHand(card);
            player.discardPile.removeCard(card);
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
                // TODO learn why this doesnt work with the last 2 params swapped later
                AbstractDungeon.gridSelectScreen.open(player.discardPile, player.discardPile.size(), "", false);
            }

            AbstractDungeon.gridSelectScreen.open(player.discardPile, amount, "", false);
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
