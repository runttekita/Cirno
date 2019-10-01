package Piruru.powers;

import Piruru.abstracts.PiruruPower;
import Piruru.intents.FrozenIntent;
import basemod.ReflectionHacks;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.lang.reflect.Field;

import static Piruru.Piruru.makeID;


/**
 * Basically stolen from
 * @see com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower
 */
public class Frozen extends PiruruPower implements
        CloneablePowerInterface,
        InvisiblePower {
    public static final String POWER_ID = makeID(Frozen.class);
    public float shaderTimer;
    private byte moveByte;
    private AbstractMonster.Intent moveIntent;
    private EnemyMoveInfo move;

    public Frozen(AbstractCreature owner) {
        this.owner = owner;
    }

    public Frozen(AbstractMonster owner) {
        super();
        this.owner = owner;
        type = PowerType.DEBUFF;
        shaderTimer = 0.0f;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public void onDeath() {
        ReflectionHacks.setPrivate(owner, AbstractCreature.class, "animationTimer", 0.0f);
    }

    @Override
    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public void update(int slot) {
        super.update(slot);

        if (shaderTimer < 1.0f) {
            shaderTimer += Gdx.graphics.getDeltaTime();
            if (shaderTimer > 1.0f) {
                shaderTimer = 1.0f;
            }
        }

        if (owner instanceof AbstractMonster && ((AbstractMonster) owner).state != null) {
            ((AbstractMonster) owner).state.setTimeScale(0);
        }
    }

    @Override
    public void onInitialApplication() {
        // Dumb action to delay grabbing monster's intent until after it's actually set
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                if (owner instanceof AbstractMonster) {
                    moveByte = ((AbstractMonster) owner).nextMove;
                    moveIntent = ((AbstractMonster) owner).intent;
                    try {
                        Field f = AbstractMonster.class.getDeclaredField("move");
                        f.setAccessible(true);
                        move = (EnemyMoveInfo) f.get(owner);
                        move.intent = FrozenIntent.FROZEN;
                        ((AbstractMonster) owner).createIntent();
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                isDone = true;
            }
        });
    }

    @Override
    public void onRemove() {
        if (owner instanceof AbstractMonster) {
            AbstractMonster m = (AbstractMonster) owner;
            if (move != null) {
                m.setMove(moveByte, moveIntent, move.baseDamage, move.multiplier, move.isMultiDamage);
            } else {
                m.setMove(moveByte, moveIntent);
            }
            m.createIntent();
            m.applyPowers();
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new Frozen(owner);
    }

    /**
     * Straight nabbed and improved from
     * @see com.evacipated.cardcrawl.mod.stslib.patches.StunMonsterPatch
     * Ty Papa Kio!
     */
    @SpirePatch(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class FrozenPowerPatch {
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(AbstractMonster.class.getName())
                            && m.getMethodName().equals("takeTurn")) {
                        m.replace("if (!m.hasPower(" + Frozen.class.getName() + ".POWER_ID)) {" +
                                "$_ = $proceed($$);" +
                                "}");
                    }
                }
            };
        }
    }
}
