package renmod.powers.ren;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import renmod.BasicMod;
import renmod.Carbon.CarbonManager;
import renmod.powers.BasePower;
import renmod.stances.effects.CustomStanceAuraEffect;

public class ContagionPower extends BasePower {
    public static final String POWER_ID = BasicMod.makeID("ContagionPower");

    private static final PowerType TYPE = PowerType.BUFF;

    private float particleTimer;

    public ContagionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, true, owner, amount);
        particleTimer = 0;
    }

    public void atEndOfRound() {
        if(this.amount == 1){
            this.flash();
            this.addToBot(new VFXAction(new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
            this.addToBot(new LoseHPAction(this.owner, this.owner, 99999));
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            CarbonManager.setContagion(false);
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    @Override
    public void updateParticles() {
        super.updateParticles();

        this.particleTimer -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer < 0.0F) {
            this.particleTimer = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(new CustomStanceAuraEffect(0.5f,0,1));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + CarbonManager.CONTAGION_PERCENT_INCREASE + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
