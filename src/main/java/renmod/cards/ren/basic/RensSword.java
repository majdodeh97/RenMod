package renmod.cards.ren.basic;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CarbonModifier;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class RensSword extends BaseCarbonCard {

    public static final String ID = makeID("RensSword");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.BASIC,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE_PERCENT = 100;
    private static final int COST_PERCENT = 100;
    private static final int COST_PERCENT_UPGRADE = -50;

    public RensSword() {
        super(ID, info, false);

        this.setCustomVar(CustomNames.Effect1, DAMAGE_PERCENT);
        this.baseDamage = 0;
        this.setCarbonCost(COST_PERCENT, COST_PERCENT_UPGRADE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));

        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        this.consumeCarbonCost();

        this.baseDamage = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        this.baseDamage = CarbonManager.getConsumeCarbonAmount(this.customVar(CustomNames.Effect1));
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
        return new RensSword();
    }

}