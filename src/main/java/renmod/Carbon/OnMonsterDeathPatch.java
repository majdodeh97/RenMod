package renmod.Carbon;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class OnMonsterDeathPatch {
    @SpirePatch(clz = AbstractMonster.class, method = "die", paramtypes = {})
    public static class MonsterDeathPatch {
        @SpirePostfixPatch
        public static void postfix(AbstractMonster __instance) {
            CarbonManager.onMonsterDeath(__instance);
        }
    }
}
