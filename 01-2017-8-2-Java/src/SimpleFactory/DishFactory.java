package SimpleFactory;

/**
 * Created by asus on 2017/8/2.
 */
public abstract class DishFactory {
    public final Dish orderDish(String dishName){
        Dish dish;
        dish = createDish(dishName);

        dish.cook();
        dish.bale();

        return dish;
    }

    public abstract Dish createDish(String dishName);
}
