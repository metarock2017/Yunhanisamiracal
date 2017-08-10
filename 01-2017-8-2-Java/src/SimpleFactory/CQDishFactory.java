package SimpleFactory;

/**
 * Created by asus on 2017/8/2.
 */
public class CQDishFactory extends DishFactory {
    @Override
    public Dish createDish(String dishName) {
        System.out.println("CQDishFactory is Cooking " + dishName);

        Dish dish = new CQDish(dishName);
        return dish;
    }
}
