package renmod.cards.ren.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class VulnerableSpot extends BaseCarbonCard {

    public static final String ID = makeID("VulnerableSpot");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );
    private static final int WEAK_AMOUNT = 2;

    public VulnerableSpot() {
        super(ID, info, false);

        setMagic(WEAK_AMOUNT);
        setDamage(8, 3);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if(m.hasPower(VulnerablePower.POWER_ID)){
            this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        }
    }

    public AbstractCard makeCopy() {
        return new VulnerableSpot();
    }

}