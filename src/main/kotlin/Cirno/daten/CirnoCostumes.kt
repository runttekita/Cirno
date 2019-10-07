package Cirno.daten

import Cirno.Cirno.Statics.cirnoCostumes
import Cirno.Cirno.Statics.textureLoader
import Cirno.characters.CirnoChar
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.helpers.input.InputHelper
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption
import reina.yui.Yui
import reina.yui.YuiClickableObject

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
            println(cirnoButton)
            Yui.autoPlaceHorizontallyWithVerticalOffset(cirnoButton!!.hb, this, 200f)
            image = getCurrentCostume()
            super.render(sb)
        }
    }

    public fun getCurrentCostume(): Texture {
        return textureLoader.getTexture("Cirno/images/char/defaultCharacter/cirno$currentCostume.png")
    }

    override fun onClick() {
    }

    override fun onUnhover() {
    }

    override fun update() {
        super.update()
        onClick()
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