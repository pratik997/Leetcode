class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        int num_of_points = points.length;
        Arrays.sort(points, new SortByYAxis());
        int maxArea = points[1][0]-points[0][0];
        for(int i=1;i<num_of_points-1;i++) {
            maxArea = Math.max(maxArea, points[i+1][0]-points[i][0]);
        }
        return maxArea;
    }
}
class SortByYAxis implements Comparator<int[]> {
    public int compare(int[] a, int[] b)
    {
        return a[0] - b[0];
    }
}