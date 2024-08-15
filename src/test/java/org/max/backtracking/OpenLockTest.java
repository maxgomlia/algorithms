package org.max.backtracking;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.



Example 1:

Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".

Example 2:

Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009".

Example 3:

Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.
 */
public class OpenLockTest {

    @Test
    void openLock() {
        int res = open(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");
        assertEquals(6, res);

        res = open(new String[]{"8888"}, "0009");
        assertEquals(1, res);

        res = open(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888");
        assertEquals(-1, res);
    }

    private int open(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        LinkedList<int[]> queue = new LinkedList<>(); // keep the lock and steps count as last element [0, 0, 0, 0, 0]
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));

        queue.offer(getLock("0000"));
        visited.add("0000");

        while (!queue.isEmpty()) {
            int[] lock = queue.poll();
            if (target.equals(toString(lock))) {
                return lock[4];
            }

            List<int[]> children = getChildren(lock);
            for (int[] child : children) {
                String strChild = toString(child);
                if (!visited.contains(strChild)) {
                    visited.add(strChild);
                    queue.offer(child);
                }
            }
        }

        return -1;
    }

    private List<int[]> getChildren(int[] lock) {
        List<int[]> children = new ArrayList<>();

        for (int i = 0; i < lock.length - 1; i++) {
            int[] newLock1 = new int[]{lock[0], lock[1], lock[2], lock[3], lock[4] + 1};
            newLock1[i] = (newLock1[i] + 1) % 10;
            children.add(newLock1);

            int[] newLock2 = new int[]{lock[0], lock[1], lock[2], lock[3], lock[4] + 1};
            newLock2[i] = (newLock2[i] - 1 + 10) % 10;
            children.add(newLock2);
        }

        return children;
    }

    private int[] getLock(String lock) {
        return new int[]{Character.getNumericValue(lock.charAt(0)), Character.getNumericValue(lock.charAt(1)),
                Character.getNumericValue(lock.charAt(2)), Character.getNumericValue(lock.charAt(3)), 0};
    }

    private String toString(int[] lock) {
        return String.valueOf(lock[0]) +
                lock[1] +
                lock[2] +
                lock[3];
    }

}
