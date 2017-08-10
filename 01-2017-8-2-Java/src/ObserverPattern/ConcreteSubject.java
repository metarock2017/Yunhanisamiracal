package ObserverPattern;

/**
 * Created by asus on 2017/8/4.
 */
public class ConcreteSubject extends Subject {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        this.notifyAllObserve();
    }
}
