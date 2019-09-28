package Piruru.abstracts;

import Piruru.Piruru;
import Piruru.characters.PiruruChar;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Piruru.Piruru.makeID;

public abstract class PiruruCard extends CustomCard {
    private int upgradeDamage;
    private int upgradeBlock;
    private int upgradeMagic;
    private int upgradeCost;

    public PiruruCard(CardStrings strings, int cost, CardType type, CardRarity rarity, CardTarget target, int upgradeDamage,
                      int upgradeBlock, int upgradeMagic, int upgradeCost) {
        super(null, strings.NAME, (String) null, cost, strings.DESCRIPTION, type, PiruruChar.Enums.PIRURU_ICE, rarity, target);
        cardID = makeID(this.getClass().getSimpleName());
        this.upgradeDamage = upgradeDamage;
        this.upgradeBlock = upgradeBlock;
        this.upgradeMagic = upgradeMagic;
        this.upgradeCost = upgradeCost;
    }

    private static String getImg(String id) {
        String imgName = id.substring((id.indexOf(":") + 1)).trim();
        // return Piruru.makeCardPath(imgName);
        return null;
    }

    public String makeName() {
        return Piruru.makeID(this.getClass().getSimpleName());
    }

    public void act(AbstractGameAction a) {
        AbstractDungeon.actionManager.addToBottom(a);
    }

    private DamageInfo dmgInfo() {
        return new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.NORMAL);
    }

    public void damage(AbstractCreature m) {
        act(new DamageAction(m, dmgInfo()));
    }

    public void block() {
        act(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public ApplyPowerAction power(AbstractCreature target, AbstractCreature source, AbstractPower p, int amount) {
        return new ApplyPowerAction(target, source, p, amount);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage();
            upgradeBlock();
            upgradeMagic();
            upgradeCost();
        }
    }

    private void upgradeDamage() {
        if (upgradeDamage != 0) {
            baseDamage += upgradeDamage;
            upgradedDamage = true;
        }
    }

    private void upgradeBlock() {
        if (upgradeBlock != 0) {
            baseBlock += upgradeBlock;
            upgradedBlock = true;
        }
    }

    private void upgradeMagic() {
        if (upgradeMagic != 0) {
            baseMagicNumber += upgradeMagic;
            upgradedMagicNumber = true;
        }
    }

    private void upgradeCost() {
        if (upgradeCost != cost) {
            int diff = costForTurn - cost;
            cost = upgradeCost;
            if (costForTurn > 0) {
                costForTurn = cost + diff;
            }

            if (costForTurn < 0) {
                costForTurn = 0;
            }
            upgradedCost = true;
        }
    }

}
