package renmod.cards.ren.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class Respite extends BaseCarbonCard {

    public static final String ID = makeID("Respite");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int COST_PERCENT = 40;

    public Respite() {
        super(ID, info, false);

        this.setCustomVar(CustomNames.Cost, COST_PERCENT);
        setMagic(5, -2);
        setCarbonCost(COST_PERCENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        int amount = this.consumeCarbonCost();
        int cardDraw = 1 + calculateCardDraw(amount);

        this.addToBot(new DrawCardAction(cardDraw));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + createExtraDescription(cardDraw);
        this.initializeDescription();
    }

    public void applyPowers() {
        int amount = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Cost));
        int cardDraw = 1 + calculateCardDraw(amount);
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + createExtraDescription(cardDraw);
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new Respite();
    }

    private int calculateCardDraw(int amount){
        return (int) Math.floor((amount * 1.0f) / this.magicNumber);
    }

    private String createExtraDescription(int cardDraw){

        return cardStrings.EXTENDED_DESCRIPTION[0] + cardDraw + cardStrings.EXTENDED_DESCRIPTION[2];
    }
}