import java.lang.StringBuilder;
public class RodCut {
	public int[] prices;

	public RodCut() {
		//example from CLRS pg. 362
		this.prices = new int[]{1,5,8,9,10,17,17,20,24,30};
	}

	public int naiveRC(int rodLength) {
		if(rodLength == 0) {
			return 0;
		}

		int q = Integer.MIN_VALUE;
		for(int i=0; i<=rodLength-1; i++) {
			q = Math.max(q, prices[i] + naiveRC(rodLength-1-i));
		}

		return q;
	}

	//Top-Down with Memoization
	public int topDownDP(int rodLength) {
		int[] revMem = new int[rodLength];
		int[] seqTable = new int[rodLength];
		for(int i=0; i<revMem.length;i++) {
			revMem[i] = Integer.MIN_VALUE;
		}

		int optimal = auxTopDown(rodLength, revMem, seqTable);
 		printRodSequence(seqTable, rodLength);
		return optimal;
	}

	private int auxTopDown(int rodLength, int[] revMem, int[] seqTable) {

		if(rodLength == 0) {
			return 0;
		}
		if(revMem[rodLength-1] != Integer.MIN_VALUE) {
			return revMem[rodLength-1];
		}

		int q = Integer.MIN_VALUE;
		
		for(int i=1; i<=rodLength; i++) {
			int decomp = prices[i-1] + auxTopDown(rodLength-i, revMem, seqTable);
			q = Math.max(q, decomp);
			if(q == decomp) {
				seqTable[rodLength-1] = rodLength-i;
			}

		}

		revMem[rodLength-1] = q;

		return q;
	}

	//print optimal cutting sequence
	public void printRodSequence(int[] seq, int rodLength) {
		if(rodLength == 0) {
			//pass
		} else {
			printRodSequence(seq, rodLength - seq[rodLength-1]);
			System.out.println(seq[rodLength-1] + "->");
		}
	}

	//Bottom-Up, non-recursive
	public int bottomUpDP(int rodLength) {

		return 0;

	}
	public static void main(String[] args) {
		RodCut rc = new RodCut();
		long startTime = System.nanoTime();
		int optimalRev = rc.naiveRC(10);
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;		
		System.out.println("Naive method took:" + totalTime);
		System.out.println("optimal rev: " + optimalRev);
		
		startTime = System.nanoTime();
		optimalRev = rc.topDownDP(9);
		endTime = System.nanoTime();
		System.out.println("Top-Down DP Memoization took:" + (endTime-startTime));
		System.out.println("optimal rev: " + optimalRev);

	}



}
