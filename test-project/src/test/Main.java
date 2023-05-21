package test;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        Queue<Character> queue = new PriorityQueue<Character>();
        queue.add('G');
        queue.add('A');
        queue.add('C');
        queue.add('E');
        queue.add('F');
        System.out.println(queue);
        while (!queue.isEmpty())
        {
            System.out.println(queue.remove());
            System.out.println(queue);
        }

    }
}
