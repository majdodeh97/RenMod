package renmod.Carbon;

import basemod.abstracts.AbstractCardModifier;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import renmod.cards.ren.BaseCarbonCard;

import static renmod.BasicMod.characterPath;

public class CarbonModifier extends AbstractCardModifier {

    private static final Texture tex = ImageMaster.loadImage(characterPath("carbonorb/cardCost.png"));

    public boolean isInherent(AbstractCard card) {
        return true;
    }

    public void onRender(AbstractCard card, SpriteBatch sb) {
        String text = ((BaseCarbonCard)card).getCostText();
        Color color = ((BaseCarbonCard)card).getTextColor();

        ExtraIcons
                .icon(tex)
                .text(text)
                .offsetX(260)
                .offsetY(135)
                .textColor(color)
                .render(card);
    }

    public void onSingleCardViewRender(AbstractCard card, SpriteBatch sb) {
        String text = ((BaseCarbonCard)card).getCostText();
        Color color = ((BaseCarbonCard)card).getTextColor();

        ExtraIcons
                .icon(tex)
                .text(text)
                .offsetX(260)
                .offsetY(135)
                .textColor(color)
                .render(card);
    }

//    // OPTION B
//    public void onRender(AbstractCard card, SpriteBatch sb) {
//        ExtraIcons
//                .icon(tex)
//                .text("100%")
//                .offsetY(60)
//                .render(card);
//    }

    public boolean shouldApply(AbstractCard card) {
        return true;
    }

    public AbstractCardModifier makeCopy() {
        return new CarbonModifier();
    }
}
