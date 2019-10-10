package cirno.interfaces

import com.evacipated.cardcrawl.modthespire.lib.SpireField
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch
import com.megacrit.cardcrawl.characters.AbstractPlayer

interface Shiver {

    @SpirePatch(
            clz = AbstractPlayer::class,
            method = SpirePatch.CLASS
    )
    public class ShiverActive {
        public companion object {
            @JvmField
            public var isShivering =  SpireField<Boolean> {false}
        }
    }

}

var AbstractPlayer.isShivering: Boolean
    get() = Shiver.ShiverActive.isShivering.get(this)
    set(value) = Shiver.ShiverActive.isShivering.set(this, value)