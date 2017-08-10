package AdapterPattern;

/**
 * Created by asus on 2017/8/3.
 */
public class AdapterWithLighting implements CommonInterface {
    private LightingInterface lightingInterface;

    public AdapterWithLighting(LightingInterface LightingSocket){
        this.lightingInterface = LightingSocket;
    }

    @Override
    public void InputPower() {
        lightingInterface.InputPowerWithLighting();
    }
}
