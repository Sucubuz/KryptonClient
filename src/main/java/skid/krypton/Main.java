package skid.krypton;

import net.fabricmc.api.ModInitializer;

public final class Main implements ModInitializer {
    public static Krypton krypton;
    public void onInitialize() {
        krypton = new Krypton();
    }

    public static Krypton getKrypton() {
        return krypton;
    }
}