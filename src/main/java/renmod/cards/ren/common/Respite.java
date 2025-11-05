package renmod.cards.ren.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public class Respite extends BaseCard {

    public static final String ID = makeID("Respite");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            1
    );
    private static final int COST_PERCENT = 20;

    public Respite() {
        super(ID, info, false);

        this.setCustomVar(CustomNames.Cost, COST_PERCENT);
        setMagic(10, -5);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = CarbonManager.getCurrentCarbonPercent(this.customVar(CustomNames.Cost));
        int cardDraw = calculateCardDraw(amount);

        CarbonManager.removeCurrentCarbon(amount);
        this.addToBot(new DrawCardAction(cardDraw));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + createExtraDescription(amount, cardDraw);
        this.initializeDescription();
    }

    public void applyPowers() {
        int amount = CarbonManager.getCurrentCarbonPercent(this.customVar(CustomNames.Cost));
        int cardDraw = calculateCardDraw(amount);
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + createExtraDescription(amount, cardDraw);
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

    private String createExtraDescription(int amount, int cardDraw){

        return cardStrings.EXTENDED_DESCRIPTION[0] + amount + cardStrings.EXTENDED_DESCRIPTION[1] + cardDraw + cardStrings.EXTENDED_DESCRIPTION[2];
    }
}