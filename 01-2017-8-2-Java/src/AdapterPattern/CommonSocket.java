package AdapterPattern;

/**
 * Created by asus on 2017/8/3.
 */
public class CommonSocket implements CommonInterface {
    @Override
    public void InputPower() {
        System.out.println("Input Power With CommonInterface");
    }
}
