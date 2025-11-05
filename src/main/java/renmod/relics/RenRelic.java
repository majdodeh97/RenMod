package renmod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import renmod.BasicMod;
import renmod.Carbon.CarbonManager;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;

public class RenRelic extends BaseRelic implements ClickableRelic {
    private static final String NAME = "RenRelic"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = BasicMod.makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private boolean usedThisCombat = false;

    public RenRelic() {
        super(ID, NAME, RenCharacter.Meta.CARD_COLOR, RARITY, SOUND);
    }

    @Override
    public void onEnterRoom(AbstractRoom room) {
        this.usedThisCombat = false;
    }

    @Override
    public void onRightClick() {
        if(usedThisCombat)
            return;

        this.flash();
        CarbonManager.restoreCarbon(100);
        this.usedThisCombat = true;

        for (AbstractCard c : AbstractDungeon.player.hand.group){
            if(c instanceof BaseCard){
                ((BaseCard)c).applyPowers();
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}