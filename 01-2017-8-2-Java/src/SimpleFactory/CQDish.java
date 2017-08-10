package SimpleFactory;

/**
 * Created by asus on 2017/8/2.
 */
public class CQDish extends Dish{
    public CQDish(String name){
        System.out.println(name);
        System.out.println("New CQDish!");
    }
    @Override
    public void cook() {
        System.out.println("CQ's Dish is Cooking!");
    }

    @Override
    public void bale() {
        System.out.println("Bale Dish By CQ's Way!");
    }
}
