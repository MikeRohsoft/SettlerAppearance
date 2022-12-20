package settlerappearance;

import necesse.gfx.forms.Form;
import necesse.gfx.forms.components.FormCheckBox;
import necesse.gfx.forms.components.FormComponent;
import necesse.gfx.forms.events.FormInputEvent;
import necesse.gfx.forms.presets.containerComponent.mob.EquipmentForm;

public class FormPatcher {
    public static int shopContainerMobUniqueID;
    public static void setAppearance(FormInputEvent<FormCheckBox> e) {
        SettlerDisplayConfig.setItemSlotDisplayState(shopContainerMobUniqueID, ((AppearanceCheckbox)e.from).slot, !e.from.checked ? 1 : 0);
    }

    public static void patchForm(EquipmentForm form) {
        for (FormComponent component : form.getComponentList()) {
            if (!(component instanceof Form)) {
                 continue;
            }
            Form currentForm = (Form)component;
            if (!"settlerequipment".equals(currentForm.name)) {
                continue;
            }
            shopContainerMobUniqueID = form.getMob().getUniqueID();
            int halfWidth = currentForm.getWidth() / 2;
            int x1 = halfWidth + 37;
            int x2 = halfWidth - 53;
            int[][] matrix = new int[][] {
                { x1, x1, x1, x2, x2, x2 },
                { 33, 73, 113, 33, 73, 113 }
            };
            AppearanceCheckbox box;
            for (int slot = 0; slot < 6; slot++) {
                box = currentForm.addComponent(new AppearanceCheckbox(matrix[0][slot], matrix[1][slot]));
                box.onClicked(FormPatcher::setAppearance);
                box.checked = SettlerDisplayConfig.getItemSlotDisplayState(shopContainerMobUniqueID, slot);
                box.slot = slot;
            }
            break;
        }
    }
}
