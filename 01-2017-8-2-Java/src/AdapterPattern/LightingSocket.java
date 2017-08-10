package AdapterPattern;

/**
 * Created by asus on 2017/8/3.
 */
public class LightingSocket implements LightingInterface{
    @Override
    public void InputPowerWithLighting() {
        System.out.println("Input Power With LightingInterface");
    }
}
