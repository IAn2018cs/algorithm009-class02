package week01;

import java.util.Deque;
import java.util.LinkedList;

public class DequeTest {

    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();

        // 双端队列 实现 队列 先进先出
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        System.out.println(deque);

        String peek = deque.peekFirst();
        System.out.println(peek);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.pollFirst());
        }

        System.out.println(deque);


        // 双端队列 实现 栈 后进先出
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        String peek2 = deque.peekFirst();
        System.out.println(peek2);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.pollFirst());
        }

        System.out.println(deque);

    }
}
