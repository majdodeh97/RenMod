package renmod.cards.ren.common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public class Reload extends BaseCard {

    public static final String ID = makeID("Reload");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );

    public Reload() {
        super(ID, info, false);

        setMagic(5, 2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CarbonManager.restoreCarbon(this.magicNumber, true);
    }

    public AbstractCard makeCopy() {
        return new Reload();
    }

}