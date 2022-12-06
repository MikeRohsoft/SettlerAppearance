package settlerappearance;

import necesse.gfx.forms.Form;
import necesse.gfx.forms.components.FormCheckBox;
import necesse.gfx.forms.components.FormComponent;
import necesse.gfx.forms.events.FormInputEvent;
import necesse.gfx.forms.presets.containerComponent.mob.EquipmentForm;

import java.util.Objects;

public class FormPatcher {
    public static Integer shopContainerMobUniqueID;
    public static void displayHead(FormInputEvent<FormCheckBox> e) {
        SettlerDisplayConfig.set(shopContainerMobUniqueID, 0, !e.from.checked);
    }

    public static void displayTorso(FormInputEvent<FormCheckBox> e) {
        SettlerDisplayConfig.set(shopContainerMobUniqueID, 1, !e.from.checked);
    }

    public static void displayFeet(FormInputEvent<FormCheckBox> e) {
        SettlerDisplayConfig.set(shopContainerMobUniqueID, 2, !e.from.checked);
    }

    public static void displayCosmeticHead(FormInputEvent<FormCheckBox> e) {
        SettlerDisplayConfig.set(shopContainerMobUniqueID, 3, !e.from.checked);
    }

    public static void displayCosmeticTorso(FormInputEvent<FormCheckBox> e) {
        SettlerDisplayConfig.set(shopContainerMobUniqueID, 4, !e.from.checked);
    }

    public static void displayCosmeticFeet(FormInputEvent<FormCheckBox> e) {
        SettlerDisplayConfig.set(shopContainerMobUniqueID, 5, !e.from.checked);
    }
    public static void patchForm(EquipmentForm form) {
        for (FormComponent c : form.getComponentList()) {
            if ((!(c instanceof Form)) || !Objects.equals(((Form) c).name, "settlerequipment")) {
                continue;
            }
            shopContainerMobUniqueID = form.getMob().getUniqueID();
            int x = ((Form)c).getWidth() / 2 + 37;
            ((Form)c).addComponent(new FormCheckBox("", x, 33)).onClicked(FormPatcher::displayHead).checked = !SettlerDisplayConfig.get(shopContainerMobUniqueID, 0);
            ((Form)c).addComponent(new FormCheckBox("", x, 73)).onClicked(FormPatcher::displayTorso).checked = !SettlerDisplayConfig.get(shopContainerMobUniqueID, 1);
            ((Form)c).addComponent(new FormCheckBox("", x, 113)).onClicked(FormPatcher::displayFeet).checked = !SettlerDisplayConfig.get(shopContainerMobUniqueID, 2);
            x = ((Form)c).getWidth() / 2 - 53;
            ((Form)c).addComponent(new FormCheckBox("", x, 33)).onClicked(FormPatcher::displayCosmeticHead).checked = !SettlerDisplayConfig.get(shopContainerMobUniqueID, 3);
            ((Form)c).addComponent(new FormCheckBox("", x, 73)).onClicked(FormPatcher::displayCosmeticTorso).checked = !SettlerDisplayConfig.get(shopContainerMobUniqueID, 4);
            ((Form)c).addComponent(new FormCheckBox("", x, 113)).onClicked(FormPatcher::displayCosmeticFeet).checked = !SettlerDisplayConfig.get(shopContainerMobUniqueID, 5);
            break;
        }
    }
}
