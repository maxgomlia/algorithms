package org.max.binarysearch;

public class FindFirstAndLastIndex {
    public static void main(String [] args) {
        int [] nums = new int [] {1, 3, 7, 7, 7, 10, 12};
        int target = 7;

        int first = findIndex(nums, target);
        int last = findIndex(nums, target + 1) - 1;

        System.out.println("First: " + first + "  " + "Last: " + last);
    }

    private static int findIndex(int [] nums, int target) {
        int start = 0;
        int end = nums.length;

        while (start < end) {
            int mid = start + (end - start)/2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}
