package settlerappearance.patches;

import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.engine.network.client.Client;
import necesse.gfx.forms.components.FormButton;
import necesse.gfx.forms.events.FormEventListener;
import necesse.gfx.forms.events.FormInputEvent;
import necesse.gfx.forms.presets.containerComponent.mob.ShopEquipmentForm;
import necesse.inventory.container.mob.ShopContainer;
import net.bytebuddy.asm.Advice;
import settlerappearance.FormPatcher;

@ModConstructorPatch(target = ShopEquipmentForm.class, arguments = {Client.class, ShopContainer.class, FormEventListener.class})
public class ShopEquipmentFormPatch {
    @Advice.OnMethodExit
    static void OnMethodExit(@Advice.This ShopEquipmentForm form, @Advice.Argument(0) Client client, @Advice.Argument(1) ShopContainer container, @Advice.Argument(2) FormEventListener<FormInputEvent<FormButton>> backButtonPressed) {
        FormPatcher.patchForm(form);
    }
}