package settlerappearance;

import necesse.engine.GlobalData;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class SettlerDisplayConfig {
    private static final SettlerDisplayConfig OBJ = new SettlerDisplayConfig();

    Map<Integer, Integer> map;
    private SettlerDisplayConfig() {
        this.map = new HashMap<>();
        File file = new File(GlobalData.cfgPath() + "appearance.cfg");
        if (!file.exists()) {
            return;
        }

        LoadData save = new LoadData(file);
        for (LoadData data : save.getLoadData()) {
            this.map.put(Integer.parseInt(data.getName()), save.getInt(data.getName()));
        }
    }

    public static void setItemSlotDisplayState(int index, int slot, int b) {
        getInstance().map.put(index, (getInstance().map.getOrDefault(index, 0) & ~(1 << slot)) | ((1 << slot) & -b));
        saveConfig();
    }

    public static boolean getItemSlotDisplayState(int index, int slot) {
        return (getInstance().map.getOrDefault(index, 0) & (1 << slot)) == 0;
    }

    public static void saveConfig() {
        SaveData appearance = new SaveData("APPEARANCE");
        for (int index : getInstance().map.keySet()) {
            appearance.addInt(String.valueOf(index), getInstance().map.get(index));
        }
        File file = new File(GlobalData.cfgPath() + "appearance.cfg");
        appearance.saveScript(file);
    }

    public static SettlerDisplayConfig getInstance() {
        return OBJ;
    }
}
