package SimpleFactory;

/**
 * Created by asus on 2017/8/2.
 */
public class FactoryScenes {
    public static void main(String[] args) {
        DishFactory cqdishfactory = new CQDishFactory();
        LiMaChaoShou liMaChaoShou = new LiMaChaoShou(cqdishfactory);

        liMaChaoShou.orderFromResturant("chaoshou");
    }
}
