package renmod.cards.ren.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import renmod.Carbon.CarbonManager;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class Diamond extends BaseCarbonCard {

    public static final String ID = makeID("Diamond");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public Diamond() {
        super(ID, info, false);
        setCostUpgrade(0);
        setInnate(true);
        setEthereal(true);
        setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new renmod.stances.Diamond()));
    }

    public AbstractCard makeCopy() {
        return new Diamond();
    }
}