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

        Button button1 = new Button(true,"click","游戏","non");
        Button button2 = new Button(false,"view","谁是卧底","url","http://ghan.s1.natapp.link/undercover/index.html");
        Button button3 = new Button(false,"view","狼人杀","url","http://ghan.s1.natapp.link/undercover/index.html");

        Button button4 = new Button(false,"view","恽涵","url","http://www.yunhan.me/");

        button1.getSub_button().add(button2);
        button1.getSub_button().add(button3);
        buttonContainer.add(button1);
        buttonContainer.add(button4);


        String s = gson.toJson(buttonContainer);

        System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        new ButtonToJson().ButtonStringTest();
    }
}
