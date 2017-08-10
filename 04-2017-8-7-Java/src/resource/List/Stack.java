package resource.List;

import java.util.LinkedList;

/**
 * 栈：后进先出(LIFO)容器
 * Created by wang on 2017/8/6.
 */
public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<T>();

    public void push(T v) {
        storage.addFirst(v);
    }

    public T peek() {
        return storage.getFirst();
    }

    public T pop() {
        return storage.removeFirst();
    }

    public boolean empty() {
        return storage.isEmpty();
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    public static void main(String[] args) {
        String test = "+d+-+-+e+R---+c+o+r---+k-+,-+e+h+ --+--+l+l--+!+!+o---";
        //作业：使用栈处理以上字符串，如果是"+"号将字母压入栈，而"-"将栈顶元素弹出并打印
        Stack<String> stringStack = new Stack<>();

        char[] chars = test.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '+') {
                int aci = (int) chars[i + 1];
                if ((aci >= 65 && aci <= 90) || (aci >= 97 && aci <= 122)) {
                    stringStack.push(String.valueOf(chars[i + 1]));
                    System.out.println(stringStack.toString());
                }
            } else if (chars[i] == '-' && !stringStack.empty()) {
                System.out.println(stringStack.pop());
            }
        }

        System.out.println(stringStack.toString());
    }
}
