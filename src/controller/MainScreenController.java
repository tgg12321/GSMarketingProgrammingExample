package controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import utility.Decoder;
import utility.Encoder;
import utility.SubsequenceFinder;

/**
 * This is the controller for the main screen of the project. 
 * It is on this screen that each objective of the project is fulfilled. 
 * The user provides input through each TextField, and upon pressing the corresponding button,
 * non-editable output TextFields will be updated with the related information.
 * 
 * For example, the first text field asks for an ASCII string to be encoded. By inputting the word "cat" into the field
 * and clicking the "Encode" button, the field to the right labeled Encoded Sequence will update with the DNA sequence that corresponds to cat. 
 * In this case - TGACTGATTCTA.
 * 
 *
 */
public class MainScreenController implements Initializable {
	//***********************************************
	// Objective 1 and 2 Related FXML Objects
	//***********************************************
	@FXML private TextField encodeTextField;
	@FXML private Button encodeButton;
	@FXML private CheckBox RNAcheckBox;
	@FXML private TextField encodedSequenceField;
	
	//***********************************************
	// Objective 3 Related FXML Objects
	//***********************************************
	@FXML private TextField decodeTextField;
	@FXML private Button decodeButton;
	@FXML private TextField decodedSequenceField;
	
	@FXML private TextField substringEncodedField;
	@FXML private Button findEncodedMessageButton;
	@FXML private TextField substringStartField;
	@FXML private TextField foundEncodedMessageField;
	
	//***********************************************
	// Objective 4 Related FXML Objects
	//***********************************************
	@FXML private TextField compStrandDNAField;
	@FXML private TextField primaryStrandField;
	@FXML private Button findComplementaryButton;
	@FXML private TextField compStrandASCIIField;
	
	//***********************************************
	// Objective 5 Related FXML Objects
	//***********************************************
	
	@FXML private TextField firstLCSField;
	@FXML private TextField secondLCSField;
	@FXML private TextField LCSResultField;
	@FXML private Button findLCSButton;
	
	public MainScreenController(){}
	@Override
	public void initialize(URL location, ResourceBundle resources) {}
	
	//Upon pressing the Encode button, the program takes the given input, checks to encode as RNA or not, and fills the encodedSequenceField to the right. 
	@FXML private void onEncodeButtonClicked(ActionEvent event) {
		encodedSequenceField.setText(Encoder.encode(encodeTextField.getText(),RNAcheckBox.isSelected()));
	}
	
	//Upon pressing the Decode button, the program takes the given input, decodes the information and fills the decodedSequenceField to the right. 
	@FXML private void onDecodeButtonClicked(ActionEvent event) {
		decodedSequenceField.setText(Decoder.decode(decodeTextField.getText(),0));
	}
	
	//Upon pressing the Find Complementary Button, the program finds the complement of the provided input and both fills out the related field, as well as decodes the complement.
	//For example, Given a Complementary strand of 	ACTGACTAAGAT, the program finds the complement as TGACTGATTCTA, and decodes it as the word "cat" 
	@FXML private void onFindComplementaryButtonClicked(ActionEvent event) {
		primaryStrandField.setText(Decoder.getComplementaryStrand(compStrandDNAField.getText()));
		compStrandASCIIField.setText(Decoder.decode(primaryStrandField.getText(),0));
	}
	
	//Upon pressing the Find Encoded Message button, the program reads in from the input field, and attempts to parse an encoded message within the given string of DNA.
	//Assumes that the input's length will be divisible by 4, and will also only find encoded messages that use the ASCII characters from 0-128. This was an assumption of 
	//mine in an attempt to improve the function's ability to parse the substring.
	@FXML private void onFindMessageClicked(ActionEvent event) {
		substringStartField.setText(""+Decoder.findEncodedMessage(substringEncodedField.getText()));
		if(!substringStartField.getText().equals("-1"))
			foundEncodedMessageField.setText(Decoder.decode(substringEncodedField.getText(), Integer.parseInt(substringStartField.getText())));
	}
	
	//Upon pressing the Find Least Common Subsequence Button, the program will read from the first and second LCS fields, and fill the LCSResult field with the LCS.
	@FXML private void onFindLCSClicked(ActionEvent event) {
		LCSResultField.setText(SubsequenceFinder.getLCS(firstLCSField.getText(), secondLCSField.getText()));
	}

}
