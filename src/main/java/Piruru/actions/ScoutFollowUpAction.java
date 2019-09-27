package Piruru.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Iterator;

//Scrape shameless c/p shut the fuck up
public class ScoutFollowUpAction extends AbstractGameAction {

    public ScoutFollowUpAction() {
        this.duration = Settings.ACTION_DUR_FASTER;// 12
    }// 13

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER) {// 17
            Iterator var1 = ScoutCardsAction.scrapedCards.iterator();// 18

            while (var1.hasNext()) {
                AbstractCard c = (AbstractCard) var1.next();
                if (c.type != AbstractCard.CardType.ATTACK) {// 19
                    AbstractDungeon.player.hand.moveToDiscardPile(c);// 20
                    c.triggerOnManualDiscard();// 21
                    GameActionManager.incrementDiscard(false);// 22
                }
            }

            ScoutCardsAction.scrapedCards.clear();// 25
        }

        this.tickDuration();// 28
    }// 29
}
