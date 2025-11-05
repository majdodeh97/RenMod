package renmod.cards.ren.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import renmod.Carbon.CarbonManager;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public class Leftovers extends BaseCard {

    public static final String ID = makeID("Leftovers");
    private static final CardStats info = new CardStats(
            RenCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            0
    );

    public Leftovers() {
        super(ID, info, false);

        setDamage(5, 2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void onCarbonConsumed(float amount) {
        this.addToBot(new DiscardToHandAction(this));
    }

    public AbstractCard makeCopy() {
        return new Leftovers();
    }

}