package Cirno.daten

import Cirno.Cirno.Statics.textureLoader
import Cirno.characters.CirnoChar
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.core.Settings
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen

class CirnoCostumes {
    private var currentCostume = 0

    public fun render(sb: SpriteBatch) {
        if (CardCrawlGame.mode == CardCrawlGame.GameMode.CHAR_SELECT && CardCrawlGame.chosenCharacter == CirnoChar.Enums.enums.Cirno) {
            sb.draw(getCurrentCostume(), 5 * Settings.scale, 400 * Settings.scale)
        }
    }

    private fun getCurrentCostume(): Texture {
        return textureLoader.getTexture("Cirno/images/char/defaultCharacter/cirno$currentCostume.png")
    }

}