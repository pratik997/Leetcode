class Solution {
    public int[][] imageSmoother(int[][] img) {
        int row = img.length, col = img[0].length;
        if(row==1 && col==1) return img; // [1]X[1] matrix
        int res[][] = new int[row][col];
        //[1]X[col] matrix
        if(row==1) {
            for(int i=0;i<col;i++) {
                int left = (i!=0)?img[0][i-1]:0; 
                int right = (i!=col-1)?img[0][i+1]:0;
                if(i==0 || i==col-1) {
                    res[0][i] = (img[0][i]+left+right)/2;
                }
                else {
                    res[0][i] = (img[0][i]+left+right)/3;
                }
            }
            return res;
        }
        //[row]X[1] matrix
        if(col==1) {
            for(int i=0;i<row;i++) {
                int top = (i!=0)?img[i-1][0]:0; 
                int bottom = (i!=row-1)?img[i+1][0]:0;
                if(i==0 || i==row-1) {
                    res[i][0] = (img[i][0]+top+bottom)/2;
                }
                else {
                    res[i][0] = (img[i][0]+top+bottom)/3;
                }
            }
            return res;
        }
        //Creating sum matrix
        for(int i=1;i<row;i++) {
            img[i][0] += img[i-1][0];
        }
        for(int i=1;i<col;i++) {
            img[0][i] += img[0][i-1];
        }
        for(int i=1;i<row;i++) {
            for(int j=1;j<col;j++) {
                img[i][j] = img[i][j]+img[i-1][j]+img[i][j-1]-img[i-1][j-1];
            }
        }
        //For center elements when exists
        if(row>2 && col>2) {
            for(int i=1;i<row-1;i++) {
                for(int j=1;j<col-1;j++) {
                    int total = img[i+1][j+1];
                    int lsub = (j-2>=0?img[i+1][j-2]:0);
                    int usub = (i-2>=0?img[i-2][j+1]:0);
                    int add = (i-2>=0 && j-2>=0?img[i-2][j-2]:0);
                    res[i][j] = (total-lsub-usub+add)/9;
                }
            }
        }
        //For border elements
        if(row>1 && col>1) {
            //For left column
            for(int i=0;i<row;i++) {
                int total = 0, count = 0;
                if(i+1<row) {
                    total = img[i+1][1];
                    count = i==0?4:6;
                }
                else {
                    total = img[i][1];
                    count = 4;
                }
                int sub = (i-2>=0)?img[i-2][1]:0;
                res[i][0] = (total-sub)/count;
            }
            //For top row(1st element is considered in previous iteration)
            for(int i=1;i<col;i++) {
                int total = 0, count = 0;
                if(i+1<col) {
                    total = img[1][i+1];
                    count = 6;
                }
                else {
                    total = img[1][i];
                    count = 4;
                }
                int sub = (i-2>=0)?img[1][i-2]:0;
                res[0][i] = (total-sub)/count;
            }
            //For right column(Same reason as above for skipping first element)
            for(int i=1;i<row;i++) {
                int total = 0, count = 0, lsub = 0, usub = 0, add = 0;
                if(i+1<row) {
                    total = img[i+1][col-1];
                    count = (i==0)?4:6;
                    lsub = (col-3)>=0?img[i+1][col-3]:0;
                }
                else {
                    total = img[i][col-1];
                    count = 4;
                    lsub = (col-3)>=0?img[i][col-3]:0;
                }
                usub = (i-2>=0)?img[i-2][col-1]:0;
                add = (i-2>=0 && col-3>=0)?img[i-2][col-3]:0;
                res[i][col-1] = (total-lsub-usub+add)/count;
            }
            //For bottom row(2 corner elements are already taken care)
            for(int i=1;i<col-1;i++) {
                int total = 0, count = 0, lsub = 0, usub = 0, add = 0;
                total = img[row-1][i+1]; //250
                count = 6;
                lsub = (i-2>=0)?img[row-1][i-2]:0; //55
                usub = (row-3>=0 && i+1<col)?img[row-3][i+1]:0; //
                add = (row-3>=0 && i-2>=0)?img[row-3][i-2]:0;
                res[row-1][i] = (total-usub-lsub+add)/count;
            }
        }
        return res;
    }
}