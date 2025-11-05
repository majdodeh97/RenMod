package renmod.cards.ren.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public class CarbonReserve extends BaseCard {

    public static final String ID = makeID("CarbonReserve");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static int RESTORE_PERCENT_AMOUNT = 30;
    private static int RESTORE_PERCENT_AMOUNT_UPGRADE = 10;

    public CarbonReserve() {
        super(ID, info, false);

        setCustomVar(CustomNames.Effect1, RESTORE_PERCENT_AMOUNT, RESTORE_PERCENT_AMOUNT_UPGRADE);
        setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amountToIncrease = (int)(CarbonManager.getMaxCarbon() * (customVar(CustomNames.Effect1)/100.0));
        CarbonManager.addCurrentCarbon(amountToIncrease);
    }

    public AbstractCard makeCopy() {
        return new CarbonReserve();
    }

}