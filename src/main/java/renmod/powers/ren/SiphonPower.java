package renmod.powers.ren;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import renmod.BasicMod;
import renmod.Carbon.CarbonManager;
import renmod.powers.BasePower;
import renmod.stances.effects.CustomStanceAuraEffect;

public class SiphonPower extends BasePower {
    public static final String POWER_ID = BasicMod.makeID("SiphonPower");

    private static final PowerType TYPE = PowerType.BUFF;

    public SiphonPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    public void atEndOfRound() {
        CarbonManager.restoreCarbon(this.amount, true);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
