//https://leetcode.com/problems/rotate-image/
public class RotateImageInPlace {
    public int[][] rotate(int[][] matrix) {
        
        Bounds bounds = this.new Bounds(matrix[0].length, matrix);
        
        //incrementing past the point of completion will trigger this condition
        while(bounds.left < bounds.right && bounds.up < bounds.down) {
            //we will use first row as temp storage so that we can rotate in place
            rightSwap(bounds);
            downSwap(bounds);
            leftSwap(bounds);
            increment(bounds);
        }
        return bounds.matrix;
    }
    
//    1 2 3       3 6 1
//    4 5 6       4 5 2
//    7 8 9       7 8 9 
    
    public void rightSwap(Bounds bounds) {
        
        for(int i = 0; i < bounds.right-bounds.left; i++) {
            //first two coords are from temp storage array and right parameters are for targeted column
            //always moving from left to right in temp storage
            swap(bounds.up, bounds.left+i, bounds.up+i, bounds.right, bounds);
        }
    }
    
//    3 6 1      9 8 1
//    4 5 2      4 5 2
//    7 8 9      7 6 3 
    
    public void downSwap(Bounds bounds) {
        for(int i = 0; i < bounds.right-bounds.left; i++) {
            swap(bounds.up, bounds.left+i, bounds.down, bounds.right-i, bounds);
        }
    }
    
    
//    9 8 1   7 4 1
//    4 5 2   8 5 2
//    7 6 3   9 6 3
    
    public void leftSwap(Bounds bounds) {
        for(int i = 0; i < bounds.right-bounds.left; i++) {
            swap(bounds.up, bounds.left+i, bounds.down-i, bounds.left, bounds);
        }
    }
    
    
    
    public void increment(Bounds bound) {
        bound.left++;
        bound.up++;
        bound.right--;
        bound.down--;
    }
    
    
    //generic swapping method
    public void swap(int r1, int c1, int r2, int c2, Bounds bound) {
        int temp = bound.matrix[r1][c1];
        bound.matrix[r1][c1] = bound.matrix[r2][c2];
        bound.matrix[r2][c2] = temp;
    }
    
    
    //state keeping for the current circumference bounds 
    public class Bounds {
        
        public int left;
        public int right;
        public int up;
        public int down;
        public int[][] matrix;
        
        public Bounds(int n, int[][] matrix){
            left = 0;
            right = n-1;
            up = 0;
            down = n-1;
            this.matrix = matrix;
        }
        
    }
}

