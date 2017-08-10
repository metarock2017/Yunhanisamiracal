package SimpleFactory;

/**
 * Created by asus on 2017/8/2.
 */
public class LiMaChaoShou {
    DishFactory dishFactory;
    public LiMaChaoShou(DishFactory dishFactory){
        this.dishFactory = dishFactory;
    }
    public Dish orderFromResturant(String dishName){
        return dishFactory.orderDish(dishName);
    }
}
