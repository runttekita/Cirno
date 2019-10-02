package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

import java.util.ArrayList;
import java.util.function.Consumer;

import static Piruru.Piruru.makeID;

public class BanishAction extends AbstractGameAction {
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID(BanishAction.class));
    private int amount;
    private Consumer<ArrayList<AbstractCard>> callback;
    private boolean anyAmount = false;
    private ArrayList<AbstractCard> list = null;

    public BanishAction(int amount, Consumer<ArrayList<AbstractCard>> callback) {
        this(amount);
        this.callback = callback;
    }

    public BanishAction(int amount) {
        this.amount = amount;
        duration = Settings.ACTION_DUR_FASTER;
    }

    public BanishAction(int amount, Consumer<ArrayList<AbstractCard>> callback, boolean anyAmount) {
        this(amount, callback);
        this.anyAmount = anyAmount;
    }

    public BanishAction(ArrayList<AbstractCard> list, Consumer<ArrayList<AbstractCard>> callback) {
        this.list = list;
        this.callback = callback;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FASTER && list == null) {
            if (AbstractDungeon.player.discardPile.isEmpty()) {
                isDone = true;
                return;
            }
            if (AbstractDungeon.player.discardPile.size() < amount) {
                AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.5f,
                        uiStrings.TEXT[0], true));
                isDone = true;
                return;
            }
            if (anyAmount) {
                AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.discardPile, amount, true, "");
                tickDuration();
                return;
            }
            AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.discardPile, amount, "", false);
            tickDuration();
            return;
        }
        if (list != null) {
            callback.accept(list);
            for (AbstractCard c : list) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(
                        c, AbstractDungeon.player.discardPile
                ));
            }
            isDone = true;
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            if (callback != null) {
                callback.accept(AbstractDungeon.gridSelectScreen.selectedCards);
            }
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(
                        c, AbstractDungeon.player.discardPile
                ));
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }
        isDone = true;
    }
}
