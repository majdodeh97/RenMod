package renmod.cards.ren.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public class GatheringStorm extends BaseCard {

    public static final String ID = makeID("GatheringStorm");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            2
    );
    private static final int BASE_PERCENT = 30;
    private static final int PERCENT_INCREASE_PER_RETAIN = 10;
    private static final int PERCENT_INCREASE_PER_RETAIN_UPGRADE = 10;

    public GatheringStorm() {
        super(ID, info, false);

        this.setCustomVar(CustomNames.Effect1, BASE_PERCENT);
        this.setCustomVar(CustomNames.Effect2, PERCENT_INCREASE_PER_RETAIN, PERCENT_INCREASE_PER_RETAIN_UPGRADE);
        this.setSelfRetain(true);
    }

    public void onRetained() {
        setCustomVar(CustomNames.Effect1, customVar(CustomNames.Effect1) + customVar(CustomNames.Effect2));
        this.baseDamage = CarbonManager.getConsumeCarbonAmount(customVar(CustomNames.Effect1));
        this.upgradedDamage = true;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription = this.rawDescription + cardStrings.EXTENDED_DESCRIPTION[0];
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = CarbonManager.getConsumeCarbonAmount(customVar(CustomNames.Effect1));
        this.calculateCardDamage(m);
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.baseDamage = CarbonManager.getConsumeCarbonAmount(customVar(CustomNames.Effect1));
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void applyPowers() {
        this.baseDamage = CarbonManager.getConsumeCarbonAmount(customVar(CustomNames.Effect1));
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
        return new GatheringStorm();
    }

}