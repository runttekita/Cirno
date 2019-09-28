package Piruru.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.DiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AllForSkills extends AbstractGameAction {
    private boolean upgraded;

    public AllForSkills(boolean upgraded) {
        this.upgraded = upgraded;
    }

    @Override
    public void update() {
        if (AbstractDungeon.player.discardPile.size() > 0) {
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.type == AbstractCard.CardType.SKILL) {
                    if (upgraded) {
                        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                            @Override
                            public void update() {
                                if (AbstractDungeon.player.discardPile.contains(c)) {
                                    AbstractDungeon.player.hand.addToHand(c);
                                    c.unhover();
                                    c.setAngle(0.0f);
                                    c.lighten(false);
                                    c.drawScale = 0.12F;
                                    c.targetDrawScale = 0.75F;
                                    c.applyPowers();
                                    AbstractDungeon.player.discardPile.removeCard(c);
                                }
                                AbstractDungeon.player.hand.refreshHandLayout();
                                AbstractDungeon.player.hand.glowCheck();
                                isDone = true;
                            }
                        });
                    } else {
                        AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(c));
                    }
                }
            }
        }
        isDone = true;
    }

}
