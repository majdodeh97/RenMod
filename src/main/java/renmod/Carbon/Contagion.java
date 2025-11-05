package renmod.Carbon;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz= AbstractPlayer.class,
        method= SpirePatch.CLASS
)

public class Contagion
{
    public static SpireField<Boolean> contagion = new SpireField<Boolean>(() -> false);
}