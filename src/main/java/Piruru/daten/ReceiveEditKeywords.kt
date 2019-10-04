package Piruru.daten

import Piruru.Piruluk
import basemod.BaseMod
import com.badlogic.gdx.Gdx
import com.evacipated.cardcrawl.mod.stslib.Keyword
import com.google.gson.Gson
import java.nio.charset.StandardCharsets

class ReceiveEditKeywords {

    init {
        val gson = Gson()
        val json = Gdx.files.internal("Piruru/localization/eng/prodStrings/keywords.json").readString(StandardCharsets.UTF_8.toString())
        val keywords = gson.fromJson(json, Array<Keyword>::class.java)

        for (keyword in keywords) {
            BaseMod.addKeyword(Piruluk.getModID()?.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION)
        }
    }

}