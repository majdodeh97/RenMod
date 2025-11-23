package renmod.cards.ren.common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.util.CardStats;

public class BloodCarbon extends BaseCarbonCard {

    public static final String ID = makeID("BloodCarbon");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0
    );
    private static final int RESTORE_PERCENT = 20;
    private static final int RESTORE_PERCENT_UPGRADE = 20;

    public BloodCarbon() {
        super(ID, info, false);

        this.setCustomVar(CustomNames.Effect1, RESTORE_PERCENT, RESTORE_PERCENT_UPGRADE);
        setMagic(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new VFXAction(new OfferingEffect(), 0.2F));
        this.addToBot(new LoseHPAction(p, p, this.magicNumber));

        CarbonManager.restoreCarbon(customVar(CustomNames.Effect1));
    }

    public AbstractCard makeCopy() {
        return new BloodCarbon();
    }

}