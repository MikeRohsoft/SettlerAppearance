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
            case 0: case 1: case 2: break;
            default: return;
        }
        inventoryItem =
            (!thisHumanMob.equipmentInventory.isSlotClear(slot + 3) && SettlerDisplayConfig.getItemSlotDisplayState(thisHumanMob.getUniqueID(), slot + 3)) ?
                thisHumanMob.equipmentInventory.getItem(slot + 3) :
            (!thisHumanMob.equipmentInventory.isSlotClear(slot) && SettlerDisplayConfig.getItemSlotDisplayState(thisHumanMob.getUniqueID(), slot)) ?
                thisHumanMob.equipmentInventory.getItem(slot) :
            (defaultItemStringID != null) ?
                new InventoryItem(defaultItemStringID) :
                null;
    }
}
