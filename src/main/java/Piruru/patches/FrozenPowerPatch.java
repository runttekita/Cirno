package Piruru.patches;

import Piruru.powers.FrozenPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
// *************************
// Stolen from:
// https://github.com/kiooeht/StSLib/blob/master/src/main/java/com/evacipated/cardcrawl/mod/stslib/patches/StunMonsterPatch.java
// Thank you papa Kio!
// *************************
@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class FrozenPowerPatch {
    public static ExprEditor Instrument()
    {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException
            {
                if (m.getClassName().equals(AbstractMonster.class.getName())
                        && m.getMethodName().equals("takeTurn")) {
                    m.replace("if (!m.hasPower(" + FrozenPower.class.getName() +") {" +
                            "$_ = $proceed($$);" +
                            "}");
                }
            }
        };
    }
}
