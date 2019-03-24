package factory;

import exceptions.IncorrectLineException;
import lines.InputLine;
import lines.InputLineC;
import lines.InputLineD;

public class InputLineFactory {
    public static InputLine createInputLine(String inputString) {
        if (isInvalidString(inputString)) {
            throw new IncorrectLineException(inputString);
        }
        if (inputString.substring(inputString.indexOf(' ') + 1, inputString.indexOf(' ') + 2).equals("C")) {
            return InputLineC.parseOf(inputString);
        } else {
            return InputLineD.parseOf(inputString);
        }
    }

    private static boolean isInvalidString(String inputString) {
        String[] partsOfInputString = inputString.split("\\s");
        if (!(partsOfInputString[1].equals("C") ||
                partsOfInputString[1].equals("D"))
        ) {
            return true;
        }

        if (!(partsOfInputString[4].equals("P") ||
                partsOfInputString[4].equals("N"))
        ) {
            return true;
        }

        if (partsOfInputString[1].equals("C") && Integer.parseInt(partsOfInputString[6]) < 0) {
            return true;
        }

        if (partsOfInputString[1].equals("C")
                && (partsOfInputString[5].length() <8 || partsOfInputString[5].length()>10) ) {
            return true;
        }

        if (partsOfInputString[1].equals("D")
                && (partsOfInputString[5].length() <8 || partsOfInputString[5].length()>21) ) {
            return true;
        }
        return false;
    }
}
