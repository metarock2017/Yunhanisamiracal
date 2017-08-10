package AdapterPattern;

/**
 * Created by asus on 2017/8/3.
 */
public class House {

    private CommonInterface commonInterface;

    public House() {
    }

    public House(CommonInterface commonInterface) {
        this.commonInterface = commonInterface;
    }

    public void setSocket(CommonInterface commonInterface) {
        this.commonInterface = commonInterface;
    }

    public void charge() {

        commonInterface.InputPower();
    }
}