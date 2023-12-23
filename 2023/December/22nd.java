class Solution {
    public int maxScore(String s) {
        int left_0 = (s.charAt(0)=='0')?1:0, right_1 = 0;
        for(int i=1;i<s.length();i++) {
            right_1 += (s.charAt(i)=='0')?0:1;
        }
        int max = left_0+right_1;
        for(int i=1;i<s.length()-1;i++) {
            if(s.charAt(i)=='0') {
                left_0++;
            }
            else {
                right_1--;
            }
            max = Math.max(max, left_0+right_1);
        }
        return max;
    }
}