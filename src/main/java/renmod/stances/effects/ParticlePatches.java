package renmod.stances.effects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import renmod.powers.ren.ContagionPower;
import spireTogether.SpireTogetherMod;
import spireTogether.monsters.CharacterEntity;
import spireTogether.patches.StanceSwitchRenderPatches;
import spireTogether.util.Reflection;
import spireTogether.util.SpireVariables;

public class ParticlePatches {
    @SpirePatch(
            clz = CustomStanceAuraEffect.class,
            method = "<ctor>",
            paramtypez = { float.class, float.class, float.class, float.class }
    )
    public static class CustomStanceAuraEffectPatcher {
        public static void Postfix(CustomStanceAuraEffect __instance) {

            if (Loader.isModLoaded("spireTogether") && StanceSwitchRenderPatches.renderOn != null) {
                Reflection.setFieldValue("x", __instance, StanceSwitchRenderPatches.renderOn.hb.cX + MathUtils.random(-StanceSwitchRenderPatches.renderOn.hb.width / 16.0F, StanceSwitchRenderPatches.renderOn.hb.width / 16.0F) - (float)((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedWidth / 2.0F);
                Reflection.setFieldValue("y", __instance, StanceSwitchRenderPatches.renderOn.hb.cY + MathUtils.random(-StanceSwitchRenderPatches.renderOn.hb.height / 16.0F, StanceSwitchRenderPatches.renderOn.hb.height / 12.0F) - (float)((TextureAtlas.AtlasRegion) Reflection.getFieldValue("img", __instance)).packedHeight / 2.0F);
            }
        }
    }

    @SpirePatch(
            clz = CustomParticleEffect.class,
            method = "<ctor>",
            paramtypez = { float.class, float.class, float.class, float.class }
    )
    public static class CustomParticleEffectPatcher {
        public static void Postfix(CustomParticleEffect __instance) {
            if (Loader.isModLoaded("spireTogether") && StanceSwitchRenderPatches.renderOn != null) {
                Reflection.setFieldValue("x", __instance, Float.valueOf(StanceSwitchRenderPatches.renderOn.hb.cX + MathUtils.random(100.0F, 160.0F) * SpireVariables.scale.x - 32.0F));
                Reflection.setFieldValue("y", __instance, Float.valueOf(StanceSwitchRenderPatches.renderOn.hb.cY + MathUtils.random(-50.0F, 220.0F) * SpireVariables.scale.y - 32.0F));
            }
        }
    }

    @SpirePatch(
            cls = "spireTogether.monsters.CharacterEntity",
            method = "update",
            optional = true
    )
    public static class CharacterEntityUpdatePatcher {
        public static void Postfix(CharacterEntity __instance) {
            if(Loader.isModLoaded("spireTogether")){
                StanceSwitchRenderPatches.renderOn = __instance;
                for(AbstractPower p : __instance.powers){
                    if(p.ID == ContagionPower.POWER_ID){
                        p.updateParticles();
                    }
                }

                StanceSwitchRenderPatches.renderOn = null;
            }
        }
    }
}
