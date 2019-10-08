package cirno.events

import cirno.Cirno
import cirno.Cirno.Statics.makeID
import com.megacrit.cardcrawl.cards.AbstractCard
import com.megacrit.cardcrawl.cards.CardGroup
import com.megacrit.cardcrawl.cards.DamageInfo
import com.megacrit.cardcrawl.cards.blue.Chill
import com.megacrit.cardcrawl.cards.blue.ColdSnap
import com.megacrit.cardcrawl.cards.blue.Coolheaded
import com.megacrit.cardcrawl.cards.blue.Glacier
import com.megacrit.cardcrawl.core.AbstractCreature
import com.megacrit.cardcrawl.events.AbstractImageEvent
import com.megacrit.cardcrawl.core.CardCrawlGame
import com.megacrit.cardcrawl.dungeons.AbstractDungeon
import com.megacrit.cardcrawl.events.beyond.SensoryStone
import com.megacrit.cardcrawl.localization.EventStrings
import com.megacrit.cardcrawl.rewards.RewardItem
import com.megacrit.cardcrawl.rooms.AbstractRoom
import javazoom.jl.decoder.LayerIIIDecoder.io



class AMeetingWithAFriend : AbstractImageEvent(eventStrings.NAME, eventStrings.DESCRIPTIONS[0], "cirno/images/events/aFriend.png") {

    public companion object {
        val ID = makeID(AMeetingWithAFriend::class.java)
        private val eventStrings = CardCrawlGame.languagePack.getEventString(ID)
        private val NAME = eventStrings.NAME
        private val DESCRIPTIONS = eventStrings.DESCRIPTIONS
        private val OPTIONS = eventStrings.OPTIONS
    }

    init  {
        imageEventText.setDialogOption(OPTIONS[0])
        imageEventText.setDialogOption(OPTIONS[1])
    }

    override fun buttonEffect(i: Int) {
        when (screenNum) {
            0 -> {
                when (i) {
                    0 -> {
                        val frostCards = arrayListOf(ColdSnap(), Coolheaded(), Chill(), Glacier())
                        frostCards.removeAt(AbstractDungeon.cardRng.random(frostCards.size - 1))
                        val frostRewards = RewardItem()
                        frostRewards.cards = frostCards
                        AbstractDungeon.getCurrRoom().rewards.clear()
                        AbstractDungeon.getCurrRoom().addCardReward(frostRewards)
                        AbstractDungeon.player.damage(DamageInfo(null as AbstractCreature?, 10, DamageInfo.DamageType.HP_LOSS))
                        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.COMPLETE
                        AbstractDungeon.combatRewardScreen.open()
                        AbstractDungeon.combatRewardScreen.rewards.removeAt(1) //Gross hack
                        imageEventText.updateBodyText(DESCRIPTIONS[1]);
                        imageEventText.updateDialogOption(0, OPTIONS[2])
                        screenNum = 1
                        imageEventText.clearRemainingOptions()
                    }
                    1 -> {
                        imageEventText.updateBodyText(DESCRIPTIONS[2]);
                        imageEventText.updateDialogOption(0, OPTIONS[2])
                        screenNum = 1
                        imageEventText.clearRemainingOptions()
                    }
                }
            }
            1 -> openMap()
        }
    }
}