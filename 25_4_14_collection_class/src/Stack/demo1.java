package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class demo1 {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<Integer>();
        st.push(10);
        System.out.println(st.size());


        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);    // 入栈
        stack.pop();      // 出栈
        stack.peek();     // 查看栈顶
        stack.isEmpty();  // 是否为空
    }
}
