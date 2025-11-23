package renmod.cards.ren.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.powers.ren.SiphonPower;
import renmod.util.CardStats;

public class Siphon extends BaseCarbonCard {

    public static final String ID = makeID("Siphon");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );

    public Siphon() {
        super(ID, info, true);

        setMagic(5);
        setInnate(false,true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new SiphonPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Siphon();
    }
}