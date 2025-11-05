package renmod.cards.ren.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public class TempoStrike extends BaseCard {

    public static final String ID = makeID("TempoStrike");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int COST_PERCENT = 20;
    private static final int DAMAGE_PERCENT = 20;

    public TempoStrike() {
        super(ID, info, true);

        this.setCustomVar(CustomNames.Cost, COST_PERCENT);
        this.setCustomVar(CustomNames.Effect1, DAMAGE_PERCENT);
        this.setMagic(1,1);
        this.baseDamage = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = CarbonManager.getCurrentCarbonPercent(this.customVar(CustomNames.Effect1));
        int carbonCost = CarbonManager.getCurrentCarbonPercent(this.customVar(CustomNames.Cost));

        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new DrawCardAction(p, this.magicNumber));
        CarbonManager.removeCurrentCarbon(carbonCost);
        this.baseDamage = CarbonManager.getCurrentCarbonPercent(this.customVar(CustomNames.Cost));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        this.baseDamage = CarbonManager.getCurrentCarbonPercent(this.customVar(CustomNames.Cost));
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
        return new TempoStrike();
    }

}