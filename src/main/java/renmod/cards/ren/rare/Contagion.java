package renmod.cards.ren.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.powers.ren.ContagionPower;
import renmod.powers.ren.SiphonPower;
import renmod.util.CardStats;

public class Contagion extends BaseCard {

    public static final String ID = makeID("Contagion");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.RARE,
            CardTarget.SELF,
            1
    );
    private static int MAX_HP_LOSS = 5;

    public Contagion() {
        super(ID, info, false);

        this.setCustomVar(CustomNames.Magic1, MAX_HP_LOSS);
        this.setMagic(2, 1);
        this.setSelfRetain(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        p.decreaseMaxHealth(this.customVar(CustomNames.Magic1));
        this.addToBot(new ApplyPowerAction(p, p, new ContagionPower(p, this.magicNumber), this.magicNumber));
        CarbonManager.setContagion(true);
    }

    public AbstractCard makeCopy() {
        return new Contagion();
    }
}