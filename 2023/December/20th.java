//O(nlogn) approach by sorting
class Solution {
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        int num = prices.length, left = 0, right = 0, totalCost = 201;
        while(left<num-1) {
            right = left+1;
            while(right<num) {
                if(prices[left]+prices[right]>money) break;
                totalCost = Math.min(totalCost, prices[left]+prices[right++]);
            }
            left++;
        } 
        return (money - totalCost)<0?money:(money-totalCost);
    }
}


//O(n) approach just find first minimum and second minimum
class Solution {
    public int buyChoco(int[] prices, int money) {
        int fMin = 200, sMin = 200;
        for(int i=0;i<prices.length;i++) {
            if(fMin>prices[i]) {
                sMin = fMin;
                fMin = prices[i];
                continue;
            }
            if(sMin>prices[i]) {
                sMin = prices[i];
            }
        }
        return (money-fMin-sMin)<0?money:money-fMin-sMin;
    }
}