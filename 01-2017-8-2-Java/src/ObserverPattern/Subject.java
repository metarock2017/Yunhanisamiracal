package ObserverPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2017/8/4.
 */
public abstract class Subject {
    // 订阅者列表
    protected List<Observer> list=new ArrayList<Observer>();

    // 订阅注册和取消
    public void registerObserve(Observer obs){
        list.add(obs);
    }
    public void cancelObserve(Observer obs){
        list.remove(obs);
    }
    public void notifyAllObserve() {
        for (Observer obs : list) {
            obs.update(this);
        }
    }

}
