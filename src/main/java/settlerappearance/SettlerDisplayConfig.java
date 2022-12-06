package settlerappearance;

import necesse.engine.GlobalData;
import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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
        for (LoadData l : save.getLoadData()) {
            map.put(Integer.parseInt(l.getName()), save.getInt(l.getName()));
        }
    }

    public static void set(Integer index, int slot, boolean b) {
        if (index == null || slot < 0 || slot > 5) {
            return;
        }
        Integer bitflags = getInstance().map.getOrDefault(index, 0);
        bitflags = b ? (bitflags | (1 << slot)) : (bitflags & ~(1 << slot));
        getInstance().map.put(index, bitflags);
        saveConfig();
    }

    public static boolean get(Integer index, int slot) {
        if (index == null || !getInstance().map.containsKey(index) || (slot < 0 || slot > 5)) {
            return false;
        }
        return (getInstance().map.get(index) & (1 << slot)) != 0;
    }

    public static void saveConfig() {
        SaveData appearance = new SaveData("APPEARANCE");
        Set<Integer> set = getInstance().map.keySet();
        for (Integer index : set) {
            appearance.addInt(String.valueOf(index), getInstance().map.get(index));
        }
        File file = new File(GlobalData.cfgPath() + "appearance.cfg");
        appearance.saveScript(file);
    }

    public static SettlerDisplayConfig getInstance() {
        return OBJ;
    }
}
