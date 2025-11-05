package renmod.Carbon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import renmod.CustomCharacter.RenCharacter;

import static renmod.BasicMod.characterPath;
import static renmod.BasicMod.makeID;

public class CarbonRender {
    public static Hitbox tipHitbox = new Hitbox(0.0F, 0.0F, 80.0F * Settings.scale, 80.0F * Settings.scale);// 35
    private static Texture texture = ImageMaster.loadImage(characterPath("carbonorb/img.png"));
    private static boolean cachedContagion = false;
    public static final String ID = makeID(CarbonRender.class.getSimpleName());


    @SpirePatch(clz = EnergyPanel.class, method = "render")
    public static class EnergyPanel_Render {
        @SpirePrefixPatch
        public static void prefix(EnergyPanel __instance, SpriteBatch sb) {

            tipHitbox.update();
            if (tipHitbox.hovered && !AbstractDungeon.isScreenUp) {
                AbstractDungeon.overlayMenu.hoveredTip = true;
            }

            if(cachedContagion != CarbonManager.getContagion()){
                cachedContagion = CarbonManager.getContagion();
                texture = cachedContagion ?
                        ImageMaster.loadImage(characterPath("carbonorb/contagion.png")) :
                        ImageMaster.loadImage(characterPath("carbonorb/img.png"));
            }

            CarbonRender.Render(sb);
        }
    }

    public static void Render(SpriteBatch spriteBatch) {
        float x = 290.0F * Settings.xScale; //280.0F * Settings.xScale;
        float y = 255.0F * Settings.yScale; //245.0F * Settings.yScale;

        if(!(AbstractDungeon.player instanceof RenCharacter))
            return;

        int maxAmount = CarbonManager.getMaxCarbon();
        int amount =  CarbonManager.getCurrentCarbon();

        tipHitbox.move(x, y);
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && (amount > 0 || maxAmount > 0)) {
            spriteBatch.setColor(1, 1, 1, 1);
            spriteBatch.draw(texture, x - texture.getWidth() * Settings.scale / 2.0f, y - texture.getHeight() * Settings.scale / 2.0f, texture.getWidth() * Settings.scale, texture.getHeight() * Settings.scale);
            String display = String.valueOf(amount);
            if (maxAmount > 0)
                display += "/" + maxAmount;


            float tempScale = FontHelper.energyNumFontBlue.getScaleX();
            try {
                FontHelper.energyNumFontBlue.getData().setScale(tempScale * .75f);
                FontHelper.renderFontCentered(spriteBatch, FontHelper.energyNumFontBlue, display, x, y);
            } finally {
                FontHelper.energyNumFontBlue.getData().setScale(tempScale);
            }

            String tip = cachedContagion ?
                    "You have Contagion. NL NL Carbon Capacity is increased by " + CarbonManager.CONTAGION_PERCENT_INCREASE + "%. NL NL Start each combat with max Carbon. NL NL Carbon regeneration is disabled." :
                    "Some cards use Carbon. NL NL Gain Carbon Capacity by killing enemies: NL Basic: " + CarbonManager.MONSTER_INCREASE + " Carbon Capacity. NL Elite: " + CarbonManager.ELITE_INCREASE + " Carbon Capacity. NL Boss: " + CarbonManager.BOSS_INCREASE + " Carbon Capacity. NL NL Restore " + CarbonManager.RESTORE_PERCENT + "% of your Carbon Capacity every turn.";

            if (tipHitbox.hovered)
                TipHelper.renderGenericTip(60.0F * Settings.scale, 450.0F * Settings.scale, "Carbon", tip);
        }
    }
}