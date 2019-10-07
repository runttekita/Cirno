package cirno.daten

import basemod.BaseMod
import cirno.Cirno
import cirno.Cirno.Statics.cirnoCostumes
import cirno.Cirno.Statics.textureLoader
import cirno.characters.CirnoChar
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption
import reina.yui.Yui
import reina.yui.YuiClickableObject
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen
import basemod.ReflectionHacks
import java.util.ArrayList



class CirnoCostumes: YuiClickableObject(textureLoader.getTexture(""), -500f, -500f) {

    var currentCostume = 1
    private var cirnoButton: CharacterOption? = null

    override fun render(sb: SpriteBatch) {
        if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT && CardCrawlGame.chosenCharacter == CirnoChar.Enums.enums.Cirno) {
            if (cirnoButton == null) {
                for (o in CardCrawlGame.mainMenuScreen.charSelectScreen.options) {
                    if (o.c is CirnoChar) {
                        cirnoButton = o
                    }
                }
            }
            if (cirnoButton != null) {
                Yui.autoPlaceHorizontallyWithVerticalOffset(cirnoButton!!.hb, this, 200f)
                image = getCurrentCostume()
                super.render(sb)
            }
        }
    }

    public fun getCurrentCostume(): Texture {
        return textureLoader.getTexture("cirno/images/char/defaultCharacter/cirno$currentCostume.png")
    }

    override fun onClick() {
    }

    override fun onUnhover() {
    }

    @SpirePatch(
            clz = CharacterOption::class,
            method = "updateHitbox"
    )
    public class ChangeCostume {
        public companion object {
            @JvmStatic
            fun Postfix(__instance: CharacterOption) {
                if (__instance.c is CirnoChar) {
                    if (__instance.hb.clickStarted && __instance.selected) {
                        if (cirnoCostumes!!.currentCostume < 9) {
                            cirnoCostumes!!.currentCostume++
                        } else {
                            cirnoCostumes!!.currentCostume = 1
                        }
                    }
                }
            }
        }
    }
}