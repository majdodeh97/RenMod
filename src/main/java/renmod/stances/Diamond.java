package renmod.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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

public class Diamond extends AbstractStance {
    public static final String STANCE_ID = makeID("DiamondStance");

    private static final int DAMAGE_PER_TURN = 5; // Also change hardcoded value in Keywords.json

    public Diamond() {
        this.ID = STANCE_ID;
        this.name = "Diamond";
        this.updateDescription();
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(new CustomParticleEffect(0,0,1));
            }
        }
    }

    @Override
    public void onEndOfTurn() {
        super.onEndOfTurn();
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player, DAMAGE_PER_TURN));
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        float newDamage = type == DamageInfo.DamageType.NORMAL ? damage * 2.0F : damage;
        return super.atDamageGive(newDamage, type);
    }

    public void updateDescription() {
        this.description = "In this #bStance, deal double damage. NL NL Lose " + DAMAGE_PER_TURN + " HP at the end of your turn.";
    }

    public void onEnterStance() {
        CardCrawlGame.sound.play("STANCE_ENTER_DIVINITY");
    }
}