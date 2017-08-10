package indi.yunhan.model.menu;

import java.util.ArrayList;

/**
 * Created by asus on 2017/8/6.
 */
public class ButtonContainer {
    private ArrayList<Button> button = new ArrayList<>();

    public final void add(Button button) {
        this.button.add(button);
    }

    public final void remove(int index) {
        this.button.remove(index);
    }

    public final int size() {
        return button.size();
    }
}
