package indi.yunhan.unitTest;

import indi.yunhan.model.menu.Button;
import indi.yunhan.model.menu.ButtonContainer;
import com.google.gson.Gson;

/**
 * Created by asus on 2017/8/6.
 */
public class ButtonToJson {

    public String ButtonStringTest (){
        Gson gson = new Gson();

        ButtonContainer buttonContainer = new ButtonContainer();

        Button undercover = new Button(true,"view","谁是卧底","non");
        Button create = new Button(false,"view","创建房间","url","http://ghan.s1.natapp.link/undercover/gameview/create.html");
        Button join = new Button(false,"view","加入游戏","url","http://ghan.s1.natapp.link/undercover/gameview/join.html");
        Button me = new Button(false,"view","小军涵的主页","url","http://www.yunhan.me/");

        undercover.getSub_button().add(create);
        undercover.getSub_button().add(join);

        buttonContainer.add(undercover);
        buttonContainer.add(me);


        String s = gson.toJson(buttonContainer);

        System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        new ButtonToJson().ButtonStringTest();
    }
}
