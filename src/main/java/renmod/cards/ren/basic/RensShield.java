package renmod.cards.ren.basic;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class RensShield extends BaseCarbonCard {

    public static final String ID = makeID("RensShield");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );
    private static final int BLOCK_PERCENT = 100;
    private static final int COST_PERCENT = 100;

    public RensShield() {
        super(ID, info, false);

        this.setCustomVar(CustomNames.Effect1, BLOCK_PERCENT);
        this.setCostUpgrade(0);
        this.baseBlock = 0;
        this.setCarbonCost(COST_PERCENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseBlock = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));

        this.calculateCardDamage(m);
        this.addToBot(new GainBlockAction(p, p, this.block));

        this.consumeCarbonCost();

        this.baseBlock = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();

    }

    public void applyPowers() {
        this.baseBlock = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new RensShield();
    }

}