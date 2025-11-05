package renmod.cards.ren.uncommon;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class Regenerate extends BaseCarbonCard {

    public static final String ID = makeID("Regenerate");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int COST_PERCENT = 100;
    private static final int HealPercentAmount = 20;
    private static final int HealPercentAmountUpgrade = 10;

    public Regenerate() {
        super(ID, info, false);

        setCustomVar(CustomNames.Cost, COST_PERCENT);
        setCustomVar(CustomNames.Effect1, HealPercentAmount, HealPercentAmountUpgrade);
        setExhaust(true);
        setCarbonCost(COST_PERCENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int healAmount = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));

        this.consumeCarbonCost();

        this.addToBot(new HealAction(p, p, healAmount));

        healAmount = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));

        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + createExtraDescription(healAmount);
        this.initializeDescription();
    }

    public void applyPowers() {
        int healAmount = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));

        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + createExtraDescription(healAmount);
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new Regenerate();
    }

    private String createExtraDescription(int amount){

        return cardStrings.EXTENDED_DESCRIPTION[0] + amount + cardStrings.EXTENDED_DESCRIPTION[1];
    }
}