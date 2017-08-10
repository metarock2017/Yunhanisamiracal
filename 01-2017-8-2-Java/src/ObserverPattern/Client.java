package ObserverPattern;

/**
 * Created by asus on 2017/8/4.
 */
public class Client {
    public static void main(String[] args) {
        ConcreteSubject cs = new ConcreteSubject();

        ConcreteObserver observe1 = new ConcreteObserver();
        ConcreteObserver observe2 = new ConcreteObserver();
        ConcreteObserver observe3 = new ConcreteObserver();

        cs.registerObserve(observe1);
        cs.registerObserve(observe2);
        cs.registerObserve(observe3);

        cs.setState(2);

    }
}
