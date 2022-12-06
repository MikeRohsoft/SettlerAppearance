package settlerappearance.patches;

import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.components.FormButton;
import necesse.gfx.forms.events.FormEventListener;
import necesse.gfx.forms.events.FormInputEvent;
import necesse.gfx.forms.presets.containerComponent.mob.EquipmentForm;
import necesse.gfx.forms.presets.containerComponent.mob.ShopEquipmentForm;
import necesse.inventory.container.Container;
import net.bytebuddy.asm.Advice;
import settlerappearance.FormPatcher;

@ModConstructorPatch(target = EquipmentForm.class, arguments = {Client.class, Container.class, String.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, FormEventListener.class}) // No arguments
public class EquipmentFormPatch {
    @Advice.OnMethodExit
    static void OnMethodExit(@Advice.This EquipmentForm form, @Advice.Argument(0) Client client, @Advice.Argument(1) Container container, @Advice.Argument(2) String header, @Advice.Argument(3) int cosmeticHeadSlotIndex, @Advice.Argument(4) int cosmeticChestSlotIndex, @Advice.Argument(5) int cosmeticFeetSlotIndex, @Advice.Argument(6) int armorHeadSlotIndex, @Advice.Argument(7) int armorChestSlotIndex, @Advice.Argument(8) int armorFeetSlotIndex, @Advice.Argument(9) int weaponSlotIndex, @Advice.Argument(10) FormEventListener<FormInputEvent<FormButton>> backButtonPressed) {
        if (!(form instanceof ShopEquipmentForm)) {
            FormPatcher.patchForm(form);
        }
    }
}

