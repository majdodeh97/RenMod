package renmod.Carbon;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz= AbstractPlayer.class,
        method= SpirePatch.CLASS
)

public class MaxCarbon
{
    public static SpireField<Integer> maxCarbon = new SpireField<Integer>(() -> CarbonManager.BASE_CARBON);
}