package renmod.cards.ren.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.powers.ren.DomainExpansionPower;
import renmod.util.CardStats;

public class DomainExpansion extends BaseCard {

    public static final String ID = makeID("DomainExpansion");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );
    private static int POWER_AMOUNT = 10;
    private static int POWER_AMOUNT_UPGRADE = 10;

    public DomainExpansion() {
        super(ID, info, true);

        setMagic(POWER_AMOUNT, POWER_AMOUNT_UPGRADE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new DomainExpansionPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DomainExpansion();
    }
}