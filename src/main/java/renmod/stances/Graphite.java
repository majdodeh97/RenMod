package renmod.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import renmod.Carbon.CarbonManager;
import renmod.stances.effects.CustomParticleEffect;
import renmod.stances.effects.CustomStanceAuraEffect;

import static renmod.BasicMod.makeID;

public class Graphite extends BaseStance {
    public static final String STANCE_ID = makeID("GraphiteStance");

    private static final int BLOCK_PERCENTAGE = 20; // Also change hardcoded value in Keywords.json

    public Graphite() {
        this.ID = STANCE_ID;
        this.name = "Graphite";
        this.updateDescription();
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new CustomParticleEffect(0.5f,0.5f,0.5f));
            }
        }
    }

    public void updateDescription() {
        this.description = "In this #bStance, " + BLOCK_PERCENTAGE + "% of Carbon used is gained as block. NL NL Block is not removed at the start of your turn.";
    }

    public void onEnterStance() {
        CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
    }

    @Override
    public void onCarbonConsumed(float amount) {
        float decimal = BLOCK_PERCENTAGE / 100.0f;
        int blockAmount = (int)Math.ceil(decimal * amount);
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, blockAmount));
    }
}