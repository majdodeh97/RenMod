package renmod.Carbon;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz= AbstractPlayer.class,
        method= SpirePatch.CLASS
)

public class CurrentCarbon
{
    public static SpireField<Integer> currentCarbon = new SpireField<Integer>(() -> 0);
}