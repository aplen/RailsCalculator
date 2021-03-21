package swingVersion;

import java.io.IOException;

class Main {

    public static void main(String[] arguments) throws IOException {
        new SwingWindow();

        //jpackage --input target/ --name Kalkulator_szyn_Solarspot --main-jar KalkulatorSolarspot-0.9-SNAPSHOT.jar --win-shortcut --win-menu --icon src\main\resources\solar.ico
    }
}
