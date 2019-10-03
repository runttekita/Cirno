package Piruru.powers;

import Piruru.abstracts.PiruruPower;
import Piruru.interfaces.OnRecover;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RecoverDamageP extends PiruruPower implements OnRecover {

    public RecoverDamageP(int amount) {
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onRecover(AbstractCard c) {
        AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
                new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE
        ));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
