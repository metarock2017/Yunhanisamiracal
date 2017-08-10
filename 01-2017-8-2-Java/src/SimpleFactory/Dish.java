package SimpleFactory;

/**
 * Created by asus on 2017/8/2.
 */
public abstract class Dish implements Food{
    public Dish() {
        System.out.println("New Dish!");
    }

    public void eaten(){
        System.out.println("Being eaten!");
    }
    public void sold(){
        System.out.println("Being sold!");
    }

    public abstract void cook();

    public abstract void bale();
}
