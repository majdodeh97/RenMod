package renmod.stances;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import renmod.Carbon.CarbonManager;
import renmod.stances.effects.CustomParticleEffect;

import static renmod.BasicMod.makeID;

public class Amorphic extends AbstractStance {
    public static final String STANCE_ID = makeID("AmorphicStance");

    private static final int HEAL_PERCENTAGE = 20; // Also change hardcoded value in Keywords.json

    private final AmorphicCarbonDecreasedListener listener;

    public Amorphic() {
        this.ID = STANCE_ID;
        this.name = "Amorphic";
        this.updateDescription();
        listener = new AmorphicCarbonDecreasedListener(HEAL_PERCENTAGE);
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new CustomParticleEffect(0,1,0));
            }
        }
    }

    public void updateDescription() {
        this.description = "In this #bStance, " + HEAL_PERCENTAGE + "% of Carbon used is gained as health.";
    }

    public void onEnterStance() {
        CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
        CarbonManager.addCarbonDecreasedListener(listener);
    }

    @Override
    public void onExitStance() {
        CarbonManager.removeCarbonDecreasedListener(listener);
    }

    private class AmorphicCarbonDecreasedListener implements CarbonManager.CarbonDecreasedListener {

        private final int healPercentage;

        public AmorphicCarbonDecreasedListener(int healPercentage){
            this.healPercentage = healPercentage;
        }

        @Override
        public void onCarbonDecreased(float amount) {
            float decimal = healPercentage / 100.0f;
            int healAmount = (int)Math.ceil(decimal * amount);
            AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, healAmount));
        }
    }
}