package Piruru.powers;

import Piruru.abstracts.PiruruPower;
import Piruru.interfaces.OnRefreshHand;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Chain extends PiruruPower implements OnRefreshHand {

    public Chain(AbstractCreature owner, int amount) {
        this.owner = owner;
        this.amount = amount;
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction a) {
        for (AbstractCard ca : AbstractDungeon.player.drawPile.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++;
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca);
                ca.cost++;
                ca.isCostModified = false;
                ChainedField.chained.set(ca, false);
            }
        }
        for (AbstractCard ca : AbstractDungeon.player.discardPile.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++;
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca);
                ca.cost++;
                ca.isCostModified = false;
                ChainedField.chained.set(ca, false);
            }
        }
        for (AbstractCard ca : AbstractDungeon.player.hand.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++;
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca);
                ca.cost++;
                ca.isCostModified = false;
                ChainedField.chained.set(ca, false);
            }
        }
        for (AbstractCard ca : AbstractDungeon.player.exhaustPile.group) {
            if (ChainedField.chained.get(ca)) {
                ca.costForTurn++;
                ca.isCostModifiedForTurn = IsCostModifiedForTurnField.costModifiedTurn.get(ca);
                ca.cost++;
                ChainedField.chained.set(ca, false);
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void onRefreshHand() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--;
                    c.isCostModifiedForTurn = true;
                    ChainedField.chained.set(c, true);
                }
                if (c.cost > 0) {
                    c.cost--;
                    c.isCostModified = true;
                    ChainedField.chained.set(c, true);
                }
            }
        }
    }

    @Override
    public void onInitialApplication() {
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--;
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn);
                    c.isCostModifiedForTurn = true;
                }
                if (c.cost > 0) {
                    c.cost--;
                    c.isCostModified = true;
                }
                ChainedField.chained.set(c, true);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--;
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn);
                    c.isCostModifiedForTurn = true;
                }
                if (c.cost > 0) {
                    c.cost--;
                    c.isCostModified = true;
                }
                ChainedField.chained.set(c, true);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--;
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn);
                    c.isCostModifiedForTurn = true;
                }
                if (c.cost > 0) {
                    c.cost--;
                    c.isCostModified = true;
                }
                ChainedField.chained.set(c, true);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!ChainedField.chained.get(c)) {
                if (c.costForTurn > 0) {
                    c.costForTurn--;
                    IsCostModifiedForTurnField.costModifiedTurn.set(c, c.isCostModifiedForTurn);
                    c.isCostModifiedForTurn = true;
                }
                if (c.cost > 0) {
                    c.cost--;
                    c.isCostModified = true;
                }
                ChainedField.chained.set(c, true);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class ChainedField {
        public static SpireField<Boolean> chained = new SpireField<>(() -> false);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class IsCostModifiedForTurnField {
        public static SpireField<Boolean> costModifiedTurn = new SpireField<>(() -> false);
    }
}
