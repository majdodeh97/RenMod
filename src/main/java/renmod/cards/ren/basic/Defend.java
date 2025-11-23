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

public class Defend extends BaseCarbonCard {

    public static final String ID = makeID("Defend");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.BASIC,
            CardTarget.SELF,
            1
    );
    private static final int RESTORE_PERCENT = 20;

    public Defend() {
        super(ID, info, false);

        setBlock(5, 3);
        this.setCustomVar(CustomNames.Effect1, RESTORE_PERCENT);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        CarbonManager.restoreCarbon(customVar(CustomNames.Effect1));
    }

    public AbstractCard makeCopy() {
        return new Defend();
    }

}