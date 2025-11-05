//package renmod.Carbon;
//
//import basemod.BaseMod;
//import basemod.interfaces.PostInitializeSubscriber;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.TipHelper;
//import com.megacrit.cardcrawl.helpers.input.InputHelper;
//import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
//import java.util.Properties;
//
//public class CarbonTutorialMod {
//
//    // Persistent config
//    private static SpireConfig config;
//
//    private static boolean hasSeenTutorial() {
//        try {
//            if (config == null) {
//                Properties defaults = new Properties();
//                defaults.setProperty("hasSeenTutorial", "false");
//                config = new SpireConfig("CarbonTutorialMod", "Config", defaults);
//            }
//            return config.getBool("hasSeenTutorial");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    private static void setSeenTutorial(boolean seen) {
//        try {
//            config.setBool("hasSeenTutorial", seen);
//            config.save();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Tutorial effect
//    public static class CarbonTutorialEffect extends AbstractGameEffect {
//        private static final float TARGET_X = Settings.WIDTH / 2f - 200f * Settings.scale;
//        private static final float TARGET_Y = Settings.HEIGHT / 2f;
//        private static final float FADE_SPEED = 2f;
//        private static final float SLIDE_SPEED = 600f;
//        private static final float PAUSE_TIME = 1.5f; // seconds
//
//        private static final float ActionDur = 0.1f; // seconds
//
//        private boolean clicked = false;
//        private boolean paused = false;
//        private float pauseTimer = 0f;
//
//        private float alpha = 0f;
//        private float currentY;
//
//        public CarbonTutorialEffect() {
//            duration = Float.MAX_VALUE;
//            currentY = TARGET_Y + 200f * Settings.scale; // start above
//        }
//
//        @Override
//        public void update() {
//            // Handle click
//            if (!clicked && InputHelper.justClickedLeft) clicked = true;
//
//            // Fade + slide logic
//            if (!clicked) {
//                if (!paused) {
//                    // Fade in
//                    alpha += FADE_SPEED * ActionDur;
//                    if (alpha > 1f) alpha = 1f;
//
//                    // Slide down
//                    currentY -= SLIDE_SPEED * ActionDur;
//                    if (currentY < TARGET_Y) currentY = TARGET_Y;
//
//                    // Start pause when fully visible
//                    if (alpha >= 1f && currentY <= TARGET_Y) {
//                        paused = true;
//                        pauseTimer = 0f;
//                    }
//                } else {
//                    // Pause at full opacity
//                    pauseTimer += ActionDur;
//                    if (pauseTimer >= PAUSE_TIME) {
//                        paused = false; // wait for click
//                    }
//                }
//            } else {
//                // Fade out
//                alpha -= FADE_SPEED * ActionDur;
//                currentY += SLIDE_SPEED * ActionDur;
//                if (alpha <= 0f) isDone = true;
//            }
//        }
//
//        @Override
//        public void render(SpriteBatch sb) {
//            Color original = sb.getColor().cpy();
//            sb.setColor(1f, 1f, 1f, alpha);
//            TipHelper.renderGenericTip(
//                    TARGET_X,
//                    currentY,
//                    "Carbon",
//                    "Some of Ren's cards consume Carbon to unleash their full power.\n" +
//                            "The Carbon symbol at the top right of a card shows how much Carbon it consumes."
//            );
//            sb.setColor(original);
//        }
//
//        @Override
//        public void dispose() { }
//    }
//
//    public static void receivePostInitialize() {
//        // Only show the tutorial once
//        if (!hasSeenTutorial()) {
//            AbstractDungeon.effectsQueue.add(new CarbonTutorialEffect());
//            setSeenTutorial(true);
//        }
//    }
//}
