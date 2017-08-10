package ObserverPattern;

/**
 * Created by asus on 2017/8/4.
 */
public class ConcreteObserver implements Observer {
    private int myState;

    @Override
    public void update(Subject sub) {
        myState=((ConcreteSubject)sub).getState();
        System.out.println("主题状态："+myState);
    }
}
