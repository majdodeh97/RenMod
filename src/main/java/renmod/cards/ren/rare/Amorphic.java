package renmod.cards.ren.rare;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public class Amorphic extends BaseCard {

    public static final String ID = makeID("Amorphic");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );

    public Amorphic() {
        super(ID, info, false);
        setCostUpgrade(0);
        setInnate(true);
        setEthereal(true);
        setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChangeStanceAction(new renmod.stances.Amorphic()));
    }

    public AbstractCard makeCopy() {
        return new Amorphic();
    }
}