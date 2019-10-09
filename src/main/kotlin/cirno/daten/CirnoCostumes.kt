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
import basemod.abstracts.CustomSavable
import cirno.Cirno.Statics.makeID
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.helpers.FontHelper
import java.util.ArrayList



class CirnoCostumes: YuiClickableObject(textureLoader.getTexture(""), Settings.WIDTH / 2f - 300f * Settings.scale, Settings.HEIGHT / 2f) {

    var currentCostume = 1
    private var cirnoButton: CharacterOption? = null

    init {
    }

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
                image = getCurrentCostume()
                super.render(sb)
                FontHelper.renderFontCentered(sb, FontHelper.energyNumFontBlue, CardCrawlGame.languagePack.getCharacterString(makeID(CirnoChar::class.java)).TEXT[3], x + image.width * Settings.scale, y)
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