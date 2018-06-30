package utility;

/**
 * This is the Encoder Class of the practice assignment. Here, I attempt to encode ASCII characters into DNA sequences of  A, T, G, and C.
 * I do so by converting an ASCII character into it's binary form, and then assigning pairs of Bits a respective DNA value.
 * For example, "a" is converted to binary as 01 10 00 01, which then is divided as 01-T, 10-G, 00-A, and 01-T, with an encoded result of TGAT.
 * Many of these functions are static, simply to save time on my part. There are certainly better ways of structuring a program such as this, but given 
 * the time constraint, I did what I could.
 */
public class Encoder {
	//These constants provide a reference for the arbitrary associations I have given each pair of Bits to their DNA counterpartss
	private static final int A = 00, T = 01, G = 10, C = 11; 
	
	/**
	 * Returns the encoded result of a given input string. For example, "cat" would be encoded as TGACTGATTCTA
	 *
	 * @param input
	 * @param RNAFlag
	 * @return The fully encoded result in DNA or RNA form
	 */
	public static String encode(String input, boolean RNAFlag) {
		//Divide the input into a character array for ease of manipulation.
		char[] inCharArray = input.toCharArray();
		StringBuilder encodingResult = new StringBuilder();
		
		//One character at a time, loop through the input string and convert each ASCII character into it's binary form.
		for(char c : inCharArray) {
		  String charAsBinaryStr = String.format("%8s", Integer.toBinaryString((int)c)).replace(' ', '0');
		  //Call the convertBinaryToDNA function to turn a single byte of data into a DNA string, and then add it to the encoded result string.
		  encodingResult.append(convertBinaryToDNA(charAsBinaryStr, RNAFlag));
		}
		return encodingResult.toString();
	}
	
	/**
	 * Accepts a string of binary characters, Assumes the string to be 8 characters long, and converts it to DNA
	 * using the constants listed above. 
	 * 
	 * @param binaryStr
	 * @param RNAFlag
	 * @return A StringBuilder which is the converted DNA sequence from the given input binary string.
	 */
	private static StringBuilder convertBinaryToDNA(String binaryStr, boolean RNAFlag) {
		StringBuilder conversionResult = new StringBuilder();
		
		//Loop through the input binary string 2 characters at a time. Convert each pair of bits to a corresponding DNA value.
		for(int i =0; i < binaryStr.length()-1; i += 2) {
			int pair = Integer.parseInt((binaryStr.charAt(i) +""+ binaryStr.charAt(i+1)));
			switch(pair) {
				case A:
					conversionResult.append("A");
					break;
				//In the case of encountering the bits 01, check if we are encoding as RNA. If so, use U instead of T.
				case T:
					if(RNAFlag)
					  conversionResult.append("U");
					else
					  conversionResult.append("T");
					break;
				case G:
					conversionResult.append("G");
					break;
				case C:
					conversionResult.append("C");
					break;
			}
		}
		return conversionResult;
	}
}

