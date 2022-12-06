package settlerappearance.patches;

import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.mobs.friendly.human.HumanMob;
import necesse.inventory.InventoryItem;
import net.bytebuddy.asm.Advice;
import settlerappearance.SettlerDisplayConfig;

@ModMethodPatch(target = HumanMob.class, name = "getDisplayArmor", arguments = {int.class, String.class})
public class HumanMobGetDisplayArmorPatch {
    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    static boolean onEnter() {
        return false;
    }

    @Advice.OnMethodExit
    static void onExit(@Advice.This HumanMob thisHumanMob, @Advice.Argument(0) int slot, @Advice.Argument(1) String defaultItemStringID, @Advice.Return(readOnly = false)InventoryItem inventoryItem) {
        switch (slot) {
            case 0:
            case 1:
            case 2:
                break;
            default: return;
        }

        boolean hideArmor = SettlerDisplayConfig.get(thisHumanMob.getUniqueID(), slot);
        boolean hideCosmetic = SettlerDisplayConfig.get(thisHumanMob.getUniqueID(), slot + 3);
        boolean hasCosmetic = !thisHumanMob.equipmentInventory.isSlotClear(slot + 3);
        boolean hasArmor = !thisHumanMob.equipmentInventory.isSlotClear(slot);
        if (hasCosmetic && !hideCosmetic) {
            inventoryItem = thisHumanMob.equipmentInventory.getItem(slot + 3);
        } else if (hasArmor && !hideArmor){
            inventoryItem = thisHumanMob.equipmentInventory.getItem(slot);
        } else if (defaultItemStringID != null) {
            inventoryItem = new InventoryItem(defaultItemStringID);
        } else {
            inventoryItem = null;
        }
    }
}
