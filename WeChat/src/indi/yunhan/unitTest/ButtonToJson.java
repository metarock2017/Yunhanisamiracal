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

        Button button1 = new Button(false, "click", "demo1", "some_key");
        Button button2 = new Button(true, "click", "demo2", "some_key");
        Button button3 = new Button(false, "click", "demo3", "some_key");
        Button button4 = new Button(false, "click", "demo4", "some_key");

        button2.getSub_button().add(button3);
        button2.getSub_button().add(button4);

        buttonContainer.add(button1);
        buttonContainer.add(button2);

        String s = gson.toJson(buttonContainer);

        System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        new ButtonToJson().ButtonStringTest();
    }
}
