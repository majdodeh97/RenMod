package renmod.powers.ren;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import renmod.BasicMod;
import renmod.Carbon.CarbonManager;
import renmod.powers.BasePower;

public class DomainExpansionPower extends BasePower {
    public static final String POWER_ID = BasicMod.makeID("DomainExpansionPower");

    private static final PowerType TYPE = PowerType.BUFF;

    public DomainExpansionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, true, owner, amount);
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            int damageAmount = CarbonManager.getCurrentCarbonPercent(this.amount);
            this.addToBot(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(damageAmount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
