package renmod.Carbon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.Level;
import renmod.BasicMod;
import renmod.CustomCharacter.RenCharacter;
import renmod.cards.BaseCard;

import java.util.ArrayList;
import java.util.List;

public class CarbonManager {

    public static final int BASE_CARBON = 10;
    public static final int RESTORE_PERCENT = 20; // Also change hardcoded value in Keywords.json
    public static final int CONTAGION_PERCENT_INCREASE = 50; // Also change hardcoded value in Keywords.json

    public static final int ROOM_INCREASE = 1; // Also change hardcoded text in Keywords.json and CarbonRender.java
    public static final int MONSTER_INCREASE = 1; // Also change hardcoded value in Keywords.json
    public static final int ELITE_INCREASE = 2; // Also change hardcoded value in Keywords.json
    public static final int BOSS_INCREASE = 5; // Also change hardcoded value in Keywords.json

    private static final List<CarbonDecreasedListener> carbonDecreasedListeners = new ArrayList<>();

    // Punlic

    public static void triggerCarbonRestore(){
        int amountToIncrease = (int)(getMaxCarbon() * (RESTORE_PERCENT/100.0));

        if(amountToIncrease != 0){
            addCurrentCarbon(amountToIncrease);
        }
    }

    // Max Carbon

    public static void addMaxCarbon(int value){
        if(isRenCharacter()) {
            int intialValue = getMaxCarbon(true);

            setMaxCarbon(intialValue + value);
        }
    }

    public static void setMaxCarbon(int value){
        if(isRenCharacter()){
            MaxCarbon.maxCarbon.set(AbstractDungeon.player, value);
        }
    }

    public static int getMaxCarbon(boolean original){
        if(isRenCharacter()){
            if(original)
                return MaxCarbon.maxCarbon.get(AbstractDungeon.player);

            if(getContagion()){
                return (int)Math.floor((1 + CONTAGION_PERCENT_INCREASE / 100.0) * MaxCarbon.maxCarbon.get(AbstractDungeon.player));
            } else {
                return MaxCarbon.maxCarbon.get(AbstractDungeon.player);
            }
        } else {
            return BASE_CARBON;
        }
    }

    public static int getMaxCarbon(){
        return getMaxCarbon(false);
    }

    // Current Carbon

    public static void addCurrentCarbon(int value, boolean force){
        if(value < 0) {
            BasicMod.logger.log(Level.ERROR, "Adding a negative value for Carbon.");
            return;
        }

        if(isRenCharacter()) {
            int intialValue = getCurrentCarbon();

            setCurrentCarbon(intialValue + value, force);
        }
    }

    public static void addCurrentCarbon(int value){
        addCurrentCarbon(value, false);
    }

    public static void removeCurrentCarbon(int value){
        if(value < 0) {
            BasicMod.logger.log(Level.ERROR, "Removing a negative value for Carbon.");
            return;
        }

        if(isRenCharacter()) {
            int intialValue = getCurrentCarbon();

            setCurrentCarbon(intialValue - value, true);
        }
    }

    public static void setCurrentCarbon(int value, boolean force){
        if(value < 0) {
            BasicMod.logger.log(Level.ERROR, "Setting a negative value for Carbon.");
            return;
        }

        if(isRenCharacter()){
            if(force || !getContagion()){
                int oldValue = getCurrentCarbon();
                int newValue = Math.min(Math.max(0, value), getMaxCarbon());

                CurrentCarbon.currentCarbon.set(AbstractDungeon.player, newValue);

                if(newValue < oldValue){
                    notifyCarbonDecreased(oldValue - newValue);
                }
            }
        }
    }

    public static void setCurrentCarbon(int value){
        setCurrentCarbon(value, false);
    }

    public static int getCurrentCarbon(){
        if(isRenCharacter()){
            return CurrentCarbon.currentCarbon.get(AbstractDungeon.player);
        } else {
            return BASE_CARBON;
        }
    }

    public static int getCurrentCarbonPercent(int percentage){
        float decimal = percentage/100.0f;

        if(isRenCharacter()){
            return (int)(Math.floor(getCurrentCarbon() * decimal));
        } else {
            return (int)(Math.floor(BASE_CARBON * decimal));
        }
    }

    // Contagion

    public static void setContagion(boolean value){
        if(isRenCharacter()){
            Contagion.contagion.set(AbstractDungeon.player, value);

            if(value){
                setCurrentCarbon(getMaxCarbon(), true); // Give player max carbon on the turn you activate contagion
            }
        }
    }

    public static boolean getContagion(){
        if(isRenCharacter()){
            return Contagion.contagion.get(AbstractDungeon.player);
        } else {
            return false;
        }
    }

    // Patches

    public static void onTurnStart(){
        if(isRenCharacter()){
            if(getContagion()){
                setCurrentCarbon(getMaxCarbon(), true);
            } else {
                triggerCarbonRestore();
            }
        }
    }

    public static void onMonsterDeath(AbstractMonster m){
        if(isRenCharacter()){
            if (!m.hasPower("Minion")) {
                int increaseAmount = 0;
                switch (m.type) {
                    case NORMAL:
                        increaseAmount = MONSTER_INCREASE;
                        break;
                    case ELITE:
                        increaseAmount = ELITE_INCREASE;
                        break;
                    case BOSS:
                        increaseAmount = BOSS_INCREASE;
                        break;
                }

                addMaxCarbon(increaseAmount);
            }
        }
    }

    public static void onEnterRoom(){
        if(isRenCharacter()){
            //addMaxCarbon(ROOM_INCREASE);
            setContagion(false);
            setCurrentCarbon(0, true);
            carbonDecreasedListeners.clear();
        }
    }

    // Private

    private static boolean isRenCharacter(){
        return AbstractDungeon.player instanceof RenCharacter;
    }

    // Listeners

    public static void addCarbonDecreasedListener(CarbonDecreasedListener listener) {
        carbonDecreasedListeners.add(listener);
    }

    public static void removeCarbonDecreasedListener(CarbonDecreasedListener listener) {
        carbonDecreasedListeners.remove(listener);
    }

    private static void notifyCarbonDecreased(float amount) {
        for (CarbonDecreasedListener listener : carbonDecreasedListeners) {
            listener.onCarbonDecreased(amount);
        }

        for (AbstractCard c : AbstractDungeon.player.discardPile.group){
            if(c instanceof BaseCard){
                ((BaseCard)c).onCarbonConsumed(amount);
            }
        }
    }

    public interface CarbonDecreasedListener {
        void onCarbonDecreased(float amount);
    }
}
