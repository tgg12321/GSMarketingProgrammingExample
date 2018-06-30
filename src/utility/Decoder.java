package utility;

/**
 * The Decoder class is intended to accept strings of DNA information, and decode them into ASCII strings.
 * I do so by splitting the DNA sequence into groups of 4, converting the 4 DNA Characters into pairs of bits, and then
 * using the resulting 8-bit string to convert into an ASCII letter. Essentially, the reverse of the Encoding process in the Encoder Class file.
 * 
 * @author Trent
 *
 */
public class Decoder {

	/**
	 * This is the main Decode function. It accepts an input string of DNA, and a substring position to begin the decoding process.
	 * It then converts the DNA into ASCII characters, successfully decoding the information.
	 * @param input
	 * @param substringStart
	 * @return The decoded ASCII string from the encoded DNA sequence.
	 */
	public static String decode(String input, int substringStart) {
		//Start the decoding process at the given substring index.
		String encodedString = input.substring(substringStart, input.length());
		
		//Split the given DNA sequence into groups of 4 characters
		String[] encodedDNA = encodedString.split("(?<=\\G....)");
		
		StringBuilder decodedResult = new StringBuilder();
		
		//For each group of 4 character DNA blocks, call the convertDNAtoASCII function to convert those 4 characters into a single ASCII character
		for(String str : encodedDNA) {
			decodedResult.append(convertDNAtoASCII(str));
		}
		
		return decodedResult.toString();
	}
	
	/**
	 * This function attempts to convert a 4 character DNA sequence into a single ASCII character by 
	 * breaking each character down into it's binary form, and determining it's proper ASCII value
	 * @param DNAStr
	 * @return A single ASCII character, yielded by converting the input DNA sequence
	 */
	private static char convertDNAtoASCII(String DNAStr) {
		//Ensure that the provided DNA sequence is exactly 4 characters long
		if(DNAStr.length() != 4) {
			return 0;
		}
		StringBuilder conversionResult = new StringBuilder();
		
		//One character at a time, convert each DNA char into it's respective arbitrary binary form established in the encoding process
		for(int i =0; i < DNAStr.length(); i++) {
			char character = DNAStr.charAt(i);
			switch(character) {
				default:
					return 0;
				case 'A':
					conversionResult.append("00");
					break;
				case 'T':
					conversionResult.append("01");
					break;
				case 'G':
					conversionResult.append("10");
					break;
				case 'C':
					conversionResult.append("11");
					break;
			}
		}
		//Parse the binary into an integer, and the return the ASCII value of that integer.
		int ASCIIresult =  Integer.parseInt(conversionResult.toString() , 2);
		return (char)ASCIIresult;
	}
	
	/**
	 * This function will accept a strand of DNA, and return that DNA's complementary strand. This is the primary
	 * logic for Objective 4.
	 * @param primaryStrand
	 * @return the Complementary strand of the input strand
	 */
	public static String getComplementaryStrand(String primaryStrand) {
		char[] primaryStrandArray = primaryStrand.toCharArray();
		StringBuilder complementaryStrand = new StringBuilder();
		
		//One character at a time, convert each DNA char into it's complement.
		for(char c : primaryStrandArray) {
			if(c == 'A')
				complementaryStrand.append("T");
			if(c == 'T')
				complementaryStrand.append("A");
			if(c == 'G')
				complementaryStrand.append("C");
			if(c == 'C')
				complementaryStrand.append("G");
		}

		return complementaryStrand.toString();
	}
	/**
	 * This function attempts to find an encoded message within a given DNA sequence. It assumes that the input sequence's length is divisible by 4, and 
	 * also assumes that only the characters 0-128 were desired in the encoded message. Although it is trivial to update this to the first 255 characters if this was an incorrect assumption.
	 * It returns the index of the beginning of the encoded message's substring.
	 * @param encodedString
	 * @return The index of the beginning of the encoded message 
	 */
	public static int findEncodedMessage(String encodedString) {
		
		//Starting at the beginning, parse the entire DNA sequence. If no valid ASCII message is found, skip the first 4 character's and parse the message again. 
		//Continue to do so until a valid ASCII message is found, and then return it's index within the string.
		for(int i =0; i < encodedString.length(); i+=4) { 
			String possibleMessage = decode(encodedString.substring(i, encodedString.length()),0);
			boolean validASCIIMessage = true;
			for(char c : possibleMessage.toCharArray()) {
				if(c <= 0 || c > 128) { // A valid ASCII message is determined as any string that does not contain a char outside the first 128 ASCII characters
					validASCIIMessage = false;
				}
			}
			if(validASCIIMessage == true) 
				return i;
		}
		//return -1 if no encoded message can be found
		return -1;
	}
}
