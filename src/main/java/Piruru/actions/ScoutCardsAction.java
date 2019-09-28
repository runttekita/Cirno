package Piruru.actions;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * @see com.megacrit.cardcrawl.cards.blue.Scrape
 * Disgustingly copied from scrape and idgaf
 */
public class ScoutCardsAction extends AbstractGameAction {
    private boolean shuffleCheck;
    private static final Logger logger = LogManager.getLogger(DrawCardAction.class.getName());
    public static ArrayList<AbstractCard> scrapedCards = new ArrayList();

    public ScoutCardsAction(AbstractCreature source, int amount, boolean endTurnDraw) {
        this.shuffleCheck = false;// 21
        if (endTurnDraw) {// 26
            AbstractDungeon.topLevelEffects.add(new PlayerTurnEffect());// 27
        } else if (AbstractDungeon.player.hasPower("No Draw")) {// 28
            AbstractDungeon.player.getPower("No Draw").flash();// 29
            this.setValues(AbstractDungeon.player, source, amount);// 30
            this.isDone = true;// 31
            this.duration = 0.0F;// 32
            this.actionType = ActionType.WAIT;// 33
            return;// 34
        }

        this.setValues(AbstractDungeon.player, source, amount);// 37
        this.actionType = ActionType.DRAW;// 38
        if (Settings.FAST_MODE) {// 39
            this.duration = Settings.ACTION_DUR_XFAST;// 40
        } else {
            this.duration = Settings.ACTION_DUR_FASTER;// 42
        }

    }// 44

    public ScoutCardsAction(AbstractCreature source, int amount) {
        this(source, amount, false);// 47
    }// 48

    public void update() {
        if (this.amount <= 0) {// 52
            this.isDone = true;// 53
        } else {
            int deckSize = AbstractDungeon.player.drawPile.size();// 57
            int discardSize = AbstractDungeon.player.discardPile.size();// 58
            if (!SoulGroup.isActive()) {// 61
                if (deckSize + discardSize == 0) {// 66
                    this.isDone = true;// 67
                } else if (AbstractDungeon.player.hand.size() == 10) {// 71
                    AbstractDungeon.player.createHandIsFullDialog();// 72
                    this.isDone = true;// 73
                } else {
                    if (!this.shuffleCheck) {// 80
                        if (this.amount > deckSize) {// 81
                            int tmp = this.amount - deckSize;// 82
                            AbstractDungeon.actionManager.addToTop(new ScoutCardsAction(AbstractDungeon.player, tmp));// 83
                            AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());// 84
                            if (deckSize != 0) {// 85
                                AbstractDungeon.actionManager.addToTop(new ScoutCardsAction(AbstractDungeon.player, deckSize));// 86
                            }

                            this.amount = 0;// 88
                            this.isDone = true;// 89
                        }

                        this.shuffleCheck = true;// 91
                    }

                    this.duration -= Gdx.graphics.getDeltaTime();// 94
                    if (this.amount != 0 && this.duration < 0.0F) {// 97
                        if (Settings.FAST_MODE) {// 98
                            this.duration = Settings.ACTION_DUR_XFAST;// 99
                        } else {
                            this.duration = Settings.ACTION_DUR_FASTER;// 101
                        }

                        --this.amount;// 103
                        if (!AbstractDungeon.player.drawPile.isEmpty()) {// 105
                            scrapedCards.add(AbstractDungeon.player.drawPile.getTopCard());// 106
                            AbstractDungeon.player.draw();// 107
                            AbstractDungeon.player.hand.refreshHandLayout();// 108
                        } else {
                            logger.warn("Player attempted to draw from an empty drawpile mid-DrawAction?MASTER DECK: " + AbstractDungeon.player.masterDeck.getCardNames());// 110 112
                            this.isDone = true;// 113
                        }

                        if (this.amount == 0) {// 116
                            this.isDone = true;// 117
                        }
                    }

                }
            }
        }
    }// 54 62 68 74 120
}
