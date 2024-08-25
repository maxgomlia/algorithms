package org.max.monotonicstack;

import java.util.Stack;

public class DailyTemperatures {
    public static void main(String [] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.dailyTemperatures(new int[] {70, 73, 75, 71, 69, 72, 76, 73});

    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<int []> stack = new Stack<>();

        int [] res = new int [temperatures.length];
        int k = 0;


        //  [70, 73, 75, 71, 69, 72, 76, 73]
        for (int i=0; i < temperatures.length; i++) {
            if (stack.isEmpty() || temperatures[i] < stack.peek()[0]) {
                stack.push(new int [] {temperatures[i], i});
            } else {
                while (!stack.isEmpty() && temperatures[i] > stack.peek()[0]) {
                    res[k++] = i - stack.peek()[1];
                    stack.pop();
                }
                stack.push(new int [] {temperatures[i], i});
            }
        }

        return res;
    }
}
