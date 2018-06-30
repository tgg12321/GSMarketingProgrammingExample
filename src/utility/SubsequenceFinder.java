package utility;

import java.util.Arrays;

/**
 * This Class attempts to find the Longest Common Subsequence in a given character sequence. It is intended to be used with
 * the DNA sequences associated with the rest of the project, but should work with any two arbitrary strings.
 * @author Trent
 *
 */
public class SubsequenceFinder {
	
	/**
	 * This is the bottom-up recursive function that finds the LCS
	 * @param strand1
	 * @param strand2
	 * @return A string that is the Longest common subsequence
	 */
    public static String getLCS(String strand1, String strand2)
    {
    	StringBuilder LCSResult = new StringBuilder();
    	int length1 = strand1.length();
    	int length2 = strand2.length();
        int[][] LCSArray = new int[length1+1][length2+1];
        
        //Here I build the 2D array that will determine my LCS
        for (int i=0; i<=length1; i++){
            for (int j=0; j<=length2; j++) {
                if (i == 0 || j == 0)
                    LCSArray[i][j] = 0;
                else if (strand1.charAt(i-1) == strand2.charAt(j-1))
                    LCSArray[i][j] = LCSArray[i-1][j-1] + 1;
                else
                    LCSArray[i][j] = Math.max(LCSArray[i-1][j], LCSArray[i][j-1]);
            }
        }

        int index = LCSArray[length1][length2];
        int temp = index;
  

        char[] runningLCS = new char[index+1];
        
        //Here I start from the bottom-right of the array, and fill the array with
        //characters one by one
        int i = length1, j = length2;
        while (i > 0 && j > 0){
        	//If the characters in strand1 and strand 2 are the same, then they are part of the LCS
            if (strand1.charAt(i-1) == strand2.charAt(j-1)){
            	runningLCS[index-1] = strand1.charAt(i-1); 
                i--; 
                j--; 
                index--;     
            }
            //If they arent the same, find the larger of the two and go in that direction.
            else if (LCSArray[i-1][j] > LCSArray[i][j-1])
                i--;
            else
                j--;
        }
        
        for(int k=0;k<=temp;k++)
            LCSResult.append(runningLCS[k]);
        return LCSResult.toString();

    }
 
}
