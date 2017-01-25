public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length<1) {
            return 0;
        }
        
        Integer[] histogram = new Integer[matrix[0].length];
        Arrays.fill(histogram, 0);
        int maxArea = 0;
        
        for(int i=0;i<matrix.length;i++) {
            updateHistogram(histogram, matrix[i]);
            int maxRectangle = findHistogramMaxRectangle(histogram);
            maxArea = Math.max(maxArea, maxRectangle);
        }
        
        return maxArea;
    }
    
    private void updateHistogram(Integer[] histogram, char[] row) {
        for(int i=0;i<row.length;i++) {
            if(row[i] == '0') {
                histogram[i] = 0;
            } else {
                histogram[i] += 1;
            }
        }
    }
    
    private int findHistogramMaxRectangle(Integer[] histogram) {
        Deque<Integer> positions = new LinkedList<Integer>();
        Deque<Integer> heights = new LinkedList<Integer>();
        int maxHeight = 0;
        
        for(int i=0;i<histogram.length;i++) {
            if(heights.isEmpty() || histogram[i] > heights.peekFirst()) {
                heights.addFirst(histogram[i]);
                positions.addFirst(i);
            } else if(histogram[i] < heights.peekFirst()) {
                Integer pastHeight = null;
                Integer pastIndex = null;
                
                while(!heights.isEmpty() && histogram[i] < heights.peekFirst()) {
                    pastHeight = heights.removeFirst();
                    pastIndex = positions.removeFirst();
                    maxHeight = Math.max(maxHeight, pastHeight*(i-pastIndex));
                }
                
                positions.addFirst(pastIndex);
                heights.addFirst(histogram[i]);
            }
        }
        
        while(!heights.isEmpty()) {
            maxHeight = Math.max(maxHeight, heights.removeFirst()*(histogram.length-positions.removeFirst()));
        }
        return maxHeight;   
    }
    
}
