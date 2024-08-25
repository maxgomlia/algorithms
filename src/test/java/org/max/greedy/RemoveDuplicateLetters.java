package org.max.greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;


//Given a string s, remove all duplicate letters from the input string while maintaining the original order of the letters.
//
//        Additionally, the returned string should be the smallest in lexicographical order among all possible results.
//
//        Examples:
//
//        Input: "bbaac"
//        Expected Output: "bac"
//        Justification: Removing the extra 'b' and one 'a' from the original string gives 'bac', which is the smallest lexicographical string without duplicate letters.
//        Input: "zabccdef"
//        Expected Output: "zabcdef"
//        Justification: Removing one of the 'c's forms 'zabcdef', the smallest string in lexicographical order without duplicates.
//        Input: "mnopmn"
//        Expected Output: "mnop"
//        Justification: Removing the second 'm' and 'n' gives 'mnop', which is the smallest possible string without duplicate characters.
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> freq = new HashMap<>();

        for (int i=0; i < s.length(); i++) {
            freq.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }

        HashSet<Character> present = new HashSet<>();

        Stack<Character> stack = new Stack<>();

        for (int i=0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (!present.contains(current)) {
                while (!stack.isEmpty() && current < stack.peek() && freq.get(stack.peek()) > 0) {
                    present.remove(stack.pop());
                }

                present.add(current);
                stack.push(current);
            }
            freq.compute(s.charAt(i), (k, v) -> v - 1);
        }

        StringBuilder res = new StringBuilder();
        for (Character ch : stack) {
            res.append(ch);
        }

        return res.toString();
    }
}
