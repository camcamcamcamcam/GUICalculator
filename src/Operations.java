public class Operations {

	/*
	 * Stuff to add: When a number is next to brackets
	 * Final thing: Re-add the try catch block
	 */

	static String isolate(String equation) {
		Calculator.isdeg = Calculator.degrad.isSelected();
		String compact = "";
		// Compacts trigonometric functions and logs into single digits
		for (int i = 0; i < equation.length() - 4; i++) {
			System.out.println(equation.substring(i, i + 5));
			switch (equation.substring(i, i + 5)) {
			case "sin-1":
				compact = "S";
				break;
			case "cos-1":
				compact = "C";
				break;
			case "tan-1":
				compact = "T";
			}
			System.out.println("Shortened character:" + compact);
			if (!compact.equals("")) {
				equation = equation.substring(0, i) + compact + equation.substring(i + 5, equation.length());
				System.out.println("Compacted: " + equation);
				// If function is compacted, string is appended.
				compact = "";
				// Compactor is replaced
			}
		}
		for (int i = 0; i < equation.length() - 2; i++) {
			System.out.println(equation.substring(i, i + 3));
			switch (equation.substring(i, i + 3)) {
			case "Ans":
				compact = "" + Calculator.answer;
				break;
			case "log":
				compact = "L";
				break;
			case "sin":
				compact = "s";
				break;
			case "cos":
				compact = "c";
				break;
			case "tan":
				compact = "t";
				break;
			}
			System.out.println("Shortened character:" + compact);
			if (!compact.equals("")) {
				equation = equation.substring(0, i) + compact + equation.substring(i + 3, equation.length());
				System.out.println("Compacted: " + equation);
				compact = "";
				// If function is compacted, string is appended.
			}
		}
		boolean foundBrackets = true;
		while (foundBrackets) {
			// While there are brackets to potentially be found.
			foundBrackets = false;
			// Set to false until brackets are found
			long differenceOfBrackets = 0;
			for (int i = 0; i < equation.length(); i++) {
				switch (equation.charAt(i)) {
				case '(':
					differenceOfBrackets++;
					foundBrackets = true;
					break;
				case ')':
					differenceOfBrackets--;
					foundBrackets = true;
				}
			}
			// If brackets are found, the while loop will execute again
			// until they have all been reduced to numbers.
			if (differenceOfBrackets < 0) {
				return "Too many closing brackets";
			}
			while (differenceOfBrackets > 0) {
				equation += ')';
				differenceOfBrackets--;
			}
			// Returns syntax error if too many closing brackets, adds
			// closing brackets on the end if there are too many opening
			// brackets.
			int openExpressionFound = 0;
			int closeExpressionFound = 0;
			int openBracketPosition = -1;
			int closeBracketPosition = -1;
			for (int i = 0; openExpressionFound != 2 && i < equation.length(); i++) {
				switch (equation.charAt(i)) {
				case '(':
					System.out.println("Found opening bracket at place " + i);
					if (openExpressionFound != 1) {
						openBracketPosition = i;
					}
					openExpressionFound++;
					break;
				case ')':
					if (openExpressionFound != 1) {
						System.out.println("Closing brackets found before opening brackets");
					} else {
						System.out.println("Found closing bracket at place " + i);
						closeExpressionFound = 1;
						closeBracketPosition = i;
					}
				}
			}
			// Finds the position of the first set of brackets, if any.
			if (closeExpressionFound < 1) {
				// i.e. if a second opening bracket is found before a closing
				// bracket
				for (int i = equation.length() - 1; i > 0; i--) {
					if (equation.charAt(i) == ')') {
						closeBracketPosition = i;
						System.out.println("Found closing2 bracket at place " + i);
						break;
					}
				}
			}
			if (openBracketPosition != -1 && closeBracketPosition != -1) {
				// If brackets have been found
				System.out.println("substring: " + equation.substring(openBracketPosition + 1, closeBracketPosition));
				equation = equation.substring(0, openBracketPosition)
						+ isolate(equation.substring(openBracketPosition + 1, closeBracketPosition))
						+ equation.substring(closeBracketPosition + 1, equation.length());
				System.out.println(equation);
				// Takes a substring of the string inside the brackets and
				// uses recursion to find more.
			}
		}
		System.out.println(bedmas(equation));
		if ((long) bedmas(equation) == bedmas(equation)) {
			return "" + (long) bedmas(equation);
		} else {
			return "" + bedmas(equation);
		}
		// Gets rid of the decimal point if it is unnecessary.
	}

	static double bedmas(String equation) {

		for (int i = equation.length() - 1; i >= 0; i--) {
			System.out.println(i);
			switch (equation.charAt(i)) {
			case '^':
				if (i < equation.length() - 1) {
					// Prevents it calling at string position equation.length()
					if (!(equation.charAt(i + 1) == '0' || equation.charAt(i + 1) == '1'
							|| equation.charAt(i + 1) == '2' || equation.charAt(i + 1) == '3'
							|| equation.charAt(i + 1) == '4' || equation.charAt(i + 1) == '5'
							|| equation.charAt(i + 1) == '6' || equation.charAt(i + 1) == '7'
							|| equation.charAt(i + 1) == '8' || equation.charAt(i + 1) == '9'
							|| equation.charAt(i + 1) == '.')) {
						// If character after power sign is not a number
						equation = equation.substring(0, i + 1) + "2" + equation.substring(i + 1, equation.length());
						System.out.println("Appended: " + equation);
					}
				} else {
					// If power is the last character
					equation = equation + 2;
					System.out.println("Appended " + equation);
				}
				equation = equation.substring(0, (int) calculate(equation, i)[2])
						+ Math.pow(calculate(equation, i)[0], calculate(equation, i)[1])
						+ equation.substring((int) calculate(equation, i)[3], equation.length());
				System.out.println("^:" + equation);
				i = equation.length();
				break;
			case '√':
				if (i > 0) {
					// Prevents it calling at string position -1
					if (!(equation.charAt(i - 1) == '0' || equation.charAt(i - 1) == '1'
							|| equation.charAt(i - 1) == '2' || equation.charAt(i - 1) == '3'
							|| equation.charAt(i - 1) == '4' || equation.charAt(i - 1) == '5'
							|| equation.charAt(i - 1) == '6' || equation.charAt(i - 1) == '7'
							|| equation.charAt(i - 1) == '8' || equation.charAt(i - 1) == '9'
							|| equation.charAt(i - 1) == '.')) {
						// If character before root sign is not a number
						equation = equation.substring(0, i) + "2" + equation.substring(i, equation.length());
						// Makes it square root by default (and shifts i up one
						// to align with root sign)
						System.out.println("Appended: " + equation);
						i++;
					}
				} else {
					// If root is the first character
					equation = 2 + equation;
					System.out.println("Appended " + equation);
					// Makes it square root by default (and shifts i up one to
					// align with root sign)
					i++;
				}
				equation = equation.substring(0, (int) calculate(equation, i)[2])
						+ Math.pow(calculate(equation, i)[1], (1 / calculate(equation, i)[0]))
						+ equation.substring((int) calculate(equation, i)[3], equation.length());
				System.out.println("√:" + equation);
				i = equation.length();
			}
		}
		// For loop that goes backwards and checks for exponentation
		// (exponents have right-associativity)
		for (int i = 0; i < equation.length(); i++) {
			// For loop for factorial and e
			switch (equation.charAt(i)) {
			case '!':
				equation = equation.substring(0, (int) calculate(equation, i)[2])
						+ factorial((int) calculate(equation, i)[0]) + equation.substring(i + 1, equation.length());
				System.out.println("!:" + equation);
				break;
			case 'e':
				System.out.println("Before: " + equation);
				equation = equation.substring(0, i) + Math.E + equation.substring(i + 1, equation.length());
				System.out.println("Appended: " + equation);
			}
		}
		for (int i = 0; i < equation.length(); i++) {
			double result = 0;// Stores the result of the function
			boolean funcFound = false;
			// substrings only if function is found
			switch (equation.charAt(i)) {
			case 'l':
				funcFound = true;
				equation = equation.substring(0, i + 1) + equation.substring(i + 2, equation.length());
				System.out.println("Appended: " + equation);
				// Changes ln to l.
				result = Math.log(calculate(equation, i)[1]);
				System.out.println("ln:" + result);
				break;
			case 's':
				funcFound = true;
				System.out.println(Calculator.isdeg);
				if (!Calculator.isdeg) {
					// Checks whether it is set to radians or degrees
					result = Math.sin(calculate(equation, i)[1]);
				} else {
					result = Math.sin(Math.toRadians(calculate(equation, i)[1]));
					System.out.println("radians: " + Math.toRadians(calculate(equation, i)[1]));
				}

				System.out.println("sin:" + result);
				break;
			case 'c':
				funcFound = true;
				if (!Calculator.isdeg) {
					// Checks whether it is set to radians or degrees
					result = Math.sin(calculate(equation, i)[1]);
				} else {
					result = Math.sin(Math.toRadians(calculate(equation, i)[1]));
				}
				System.out.println("cos:" + result);
				break;
			case 't':
				funcFound = true;
				if (!Calculator.isdeg) {
					// Checks whether it is set to radians or degrees
					result = Math.sin(calculate(equation, i)[1]);
				} else {
					result = Math.sin(Math.toRadians(calculate(equation, i)[1]));
				}
				System.out.println("tan:" + result);
				break;
			case 'L':
				funcFound = true;
				result = Math.log10(calculate(equation, i)[1]);
				System.out.println("log:" + result);
				break;
			case 'S':
				funcFound = true;
				result = Math.asin(calculate(equation, i)[1]);
				System.out.println("sin-1:" + result);
				if (Calculator.isdeg) {
					// Checks whether it is set to radians or degrees
					result = Math.toDegrees(result);
				}
				break;
			case 'C':
				funcFound = true;
				result = Math.acos(calculate(equation, i)[1]);
				System.out.println("cos-1:" + result);
				if (Calculator.isdeg) {
					// Checks whether it is set to radians or degrees
					result = Math.toDegrees(result);
				}
				break;
			case 'T':
				funcFound = true;
				result = Math.atan(calculate(equation, i)[1]);
				System.out.println("tan-1:" + result);
				if (Calculator.isdeg) {
					// Checks whether it is set to radians or degrees
					result = Math.toDegrees(result);
				}
			}
			if (funcFound) {
				equation = equation.substring(0, (int) calculate(equation, i)[2]) + result
						+ equation.substring((int) calculate(equation, i)[3], equation.length());
				System.out.println("Trig: " + equation);
			}
		}
		// For loop that checks for functions ln, shortened logs and
		// trigonometric functions
		for (int i = 0; i < equation.length(); i++) {
			switch (equation.charAt(i)) {
			case '*':
				equation = equation.substring(0, (int) calculate(equation, i)[2])
						+ (calculate(equation, i)[0] * calculate(equation, i)[1])
						+ equation.substring((int) calculate(equation, i)[3], equation.length());
				i = 0;
				System.out.println("*:" + equation);
				break;
			case '/':
				equation = equation.substring(0, (int) calculate(equation, i)[2])
						+ (calculate(equation, i)[0] / calculate(equation, i)[1])
						+ equation.substring((int) calculate(equation, i)[3], equation.length());
				i = 0;
				System.out.println("/:" + equation);
				break;
			case '%':
				equation = equation.substring(0, (int) calculate(equation, i)[2])
						+ (calculate(equation, i)[0] % calculate(equation, i)[1])
						+ equation.substring((int) calculate(equation, i)[3], equation.length());
				i = 0;
				System.out.println("%:" + equation);

				// Third stage of bedmas. Evaluates multiplication, division and
				// modulus from left to right. Resets the code to start from
				// zero whenever the string answer is appended.
			}
		}
		for (int i = 0; i < equation.length(); i++) {
			if (equation.charAt(i) == '+') {
				System.out.println("ooriginal index" + i);
				equation = equation.substring(0, (int) calculate(equation, i)[2])
						+ (calculate(equation, i)[0] + calculate(equation, i)[1])
						+ equation.substring((int) calculate(equation, i)[3], equation.length());
				i = 0;
				System.out.println("+:" + equation);
			} else if (equation.charAt(i) == '-') {
				if (i == 0) {
					// If the minus is at the start, see it as a negative sign.
					i++;
				} else {
					equation = equation.substring(0, (int) calculate(equation, i)[2])
							+ (calculate(equation, i)[0] - calculate(equation, i)[1])
							+ equation.substring((int) calculate(equation, i)[3], equation.length());
					i = 0;
					System.out.println("-:" + equation);
				}
			}
			// Final stage of bedmas. Evaluates addition and subtraction from
			// left to right. Resets the code to start from zero whenever the
			// string answer is appended.
		}
		return Double.parseDouble(equation);
		// Returns answer as a parsed double.
	}

	static double[] calculate(String equation, int indexOfOperator) {
		boolean isNumber[] = new boolean[equation.length()];
		for (int i = 0; i < equation.length(); i++) {
		  boolean error = false;
		  try {
		    Integer.parseInt("" + equation.charAt(i));
		  }
		  catch (Exception e) {
		    error = true;
		  }
		  if (!error || equation.charAt(i) == '.') {
		    isNumber[i] = true;
		  }
		  if (i > 0) {
		    isNumber[i] = isNumber[i] || (equation.charAt(i) == '-' && !isNumber[i - 1]);
		  } else {
		    isNumber[i] = isNumber[i] || equation.charAt(i) == '-';
		  }
		}
		// Converts the string array into a series of ones and zeros, showing
		// which characters are numbers or not. (- count as characters when at
		// the start of the string or after an operator.)
		int startIndex1 = 0;
		int endIndex1 = indexOfOperator - 1;
		int startIndex2 = indexOfOperator + 1;
		int endIndex2 = 0;
		// Variables that store where numbers to the left and right of the
		// operator begin and end.
		System.out.println("Original index: " + indexOfOperator);
		indexOfOperator = endIndex1;
		if (!(equation.charAt(endIndex1 + 1) == 'L' || equation.charAt(endIndex1 + 1) == 'S'
				|| equation.charAt(endIndex1 + 1) == 'C' || equation.charAt(endIndex1 + 1) == 'T'
				|| equation.charAt(endIndex1 + 1) == 'l' || equation.charAt(endIndex1 + 1) == 's'
				|| equation.charAt(endIndex1 + 1) == 'c' || equation.charAt(endIndex1 + 1) == 't')) {
			while (isNumber[indexOfOperator]) {
				startIndex1 = indexOfOperator;
				System.out.println("Start index of first number: " + startIndex1);
				indexOfOperator--;
				if (indexOfOperator < 0) {
					break;
				}
			}
		}
		indexOfOperator = startIndex2;
		System.out.println("Index: " + indexOfOperator);
		if (equation.charAt(endIndex1 + 1) != '!') {
			while (isNumber[indexOfOperator]) {
				endIndex2 = indexOfOperator;
				System.out.println("End index of second number: " + endIndex2);
				indexOfOperator++;
				if (indexOfOperator > equation.length() - 1) {
					break;
				}
			}
		}

		// Evaluates unknown indexes using the boolean array created above.
		double stuffToReturn[] = new double[4];
		if (equation.charAt(endIndex1 + 1) == '!') {
			System.out.println(Double.parseDouble(equation.substring(startIndex1, endIndex1 + 1)));
			System.out.println(startIndex2 + ", " + (endIndex2 + 1));
			System.out.println(startIndex1 + ", " + (endIndex1 + 1));
			stuffToReturn[0] = Double.parseDouble(equation.substring(startIndex1, endIndex1 + 1));
			stuffToReturn[2] = startIndex1;
			stuffToReturn[3] = endIndex2 + 1;
		} else if (equation.charAt(endIndex1 + 1) == 'L' || equation.charAt(endIndex1 + 1) == 'S'
				|| equation.charAt(endIndex1 + 1) == 'C' || equation.charAt(endIndex1 + 1) == 'T'
				|| equation.charAt(endIndex1 + 1) == 'l' || equation.charAt(endIndex1 + 1) == 's'
				|| equation.charAt(endIndex1 + 1) == 'c' || equation.charAt(endIndex1 + 1) == 't') {
			System.out.println(startIndex2 + ", " + (endIndex2 + 1));
			System.out.println(Double.parseDouble(equation.substring(startIndex2, endIndex2 + 1)));
			System.out.println(startIndex1 + ", " + (endIndex1 + 1));
			stuffToReturn[1] = Double.parseDouble(equation.substring(startIndex2, endIndex2 + 1));
			stuffToReturn[2] = startIndex1;
			stuffToReturn[3] = endIndex2 + 1;
		} else {
			System.out.println(Double.parseDouble(equation.substring(startIndex1, endIndex1 + 1)));
			System.out.println(startIndex2 + ", " + (endIndex2 + 1));
			System.out.println(Double.parseDouble(equation.substring(startIndex2, endIndex2 + 1)));
			System.out.println(startIndex1 + ", " + (endIndex1 + 1));
			stuffToReturn[0] = Double.parseDouble(equation.substring(startIndex1, endIndex1 + 1));
			stuffToReturn[1] = Double.parseDouble(equation.substring(startIndex2, endIndex2 + 1));
			stuffToReturn[2] = startIndex1;
			stuffToReturn[3] = endIndex2 + 1;
		}

		// Parses both numbers into a double array. The third and fourth numbers
		// show which part of the equation was solved.
		return stuffToReturn;
	}

	static double factorial(long num) {
		long factorialised = 1;
		while (num > 1) {
			factorialised *= num;
			num--;
		}
		return factorialised;
	}
}