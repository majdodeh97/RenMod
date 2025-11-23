package renmod.cards.ren.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CustomNames;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.cards.ren.BaseCarbonCard;
import renmod.cards.ren.actions.HarvestAction;
import renmod.util.CardStats;

public class Harvest extends BaseCarbonCard {

    public static final String ID = makeID("Harvest");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            1
    );
    private static final int DAMAGE = 12;
    private static final int DAMAGE_UPGRADE = 4;

    public Harvest() {
        super(ID, info, false);

        setDamage(DAMAGE, DAMAGE_UPGRADE);
        setSelfRetain(false, true);
        setExhaust(true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HarvestAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public AbstractCard makeCopy() {
        return new Harvest();
    }
}