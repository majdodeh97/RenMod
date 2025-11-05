package renmod.cards.ren.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class FutureSight extends BaseCarbonCard {

    public static final String ID = makeID("FutureSight");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0
    );
    private static int COST_AMOUNT = 5;

    public FutureSight() {
        super(ID, info, false);

        setMagic(1, 1);
        setCustomVar(CustomNames.Cost, COST_AMOUNT);
        this.setCarbonCost(COST_AMOUNT);
        this.setIsCarbonCostFlat(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int scryAmount = CarbonManager.getConsumeCarbonAmount(customVar(CustomNames.Cost), true);

        this.consumeCarbonCost();

        if(scryAmount != 0){
            this.addToBot(new ScryAction(scryAmount));
        }

        this.addToBot(new DrawCardAction(this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new FutureSight();
    }

}