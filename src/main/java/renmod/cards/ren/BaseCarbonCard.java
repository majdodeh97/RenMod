package renmod.cards.ren;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.badlogic.gdx.graphics.Color;
import renmod.Carbon.CarbonManager;
import renmod.Carbon.CarbonModifier;
import renmod.cards.BaseCard;
import renmod.util.CardStats;

public abstract class BaseCarbonCard extends BaseCard {

    public int carbonCost = 0;
    public int carbonCostUpgrade = 0;
    public boolean isCarbonCostFlat = false;

    public BaseCarbonCard(String ID, CardStats info, boolean upgradesDescription){
        super(ID, info, upgradesDescription);

        CardModifierManager.addModifier((AbstractCard)this, new CarbonModifier());
    }

    public void setCarbonCost(int carbonCost, int carbonCostUpgrade){
        this.carbonCost = carbonCost;
        this.carbonCostUpgrade = carbonCostUpgrade;
    }

    public void setCarbonCost(int carbonCost){
        setCarbonCost(carbonCost, 0);
    }

    public void setIsCarbonCostFlat(boolean isCarbonCostFlat){
        this.isCarbonCostFlat = isCarbonCostFlat;
    }

    public int consumeCarbonCost(){
        return CarbonManager.consumeCarbon(this.carbonCost, this.isCarbonCostFlat);
    }

    public String getCostText(){
        return this.upgraded ?
                (this.carbonCost + this.carbonCostUpgrade) + (this.isCarbonCostFlat ? "" : "%") :
                this.carbonCost + (this.isCarbonCostFlat ? "" : "%");
    }

    public Color getTextColor(){
        return this.upgraded && this.carbonCostUpgrade != 0 ?
                new Color(0,1,0,1) :
                new Color(1,1,1,1);
    }
}
