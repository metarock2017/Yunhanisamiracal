package AdapterPattern;

/**
 * Created by asus on 2017/8/3.
 */
public class AdapterScenes {
    public static void main(String[] args) {
        LightingSocket lightingSocket = new LightingSocket();

        House hotel = new House();

        AdapterWithLighting socketAdapter = new AdapterWithLighting(lightingSocket);

        hotel.setSocket(socketAdapter);

        hotel.charge();
    }
}
