package com.casasmap.p1;

import java.util.Random;
public class CristalBall {
	// member variables
	public String string = "";

	 public String[] answers = {
	  
	  "Totally", "No doubt about it", "For sure!", "Of course not!", "No",
	  "Never ever", "Maybe", "Who knows!", "Better try again"
	  
	 };
	

	// methods

	public String getAnAnswer() {
		// choosing a random number out of three
		Random random = new Random();
		int randomGenerated = random.nextInt(answers.length);
		string = answers[randomGenerated];
		return string;
	}


}
