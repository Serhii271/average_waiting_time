package lines;

import exceptions.IncorrectLineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InputLineC extends InputLine {
    private LocalDate dateApplication;
    private int waitingTime;

    private InputLineC(int numberLineInFile, char typeLine, String serviceId, String questionTypeId, char responseType,
                       String dateApplication, int waitingTime) {
        super(numberLineInFile, typeLine, serviceId, questionTypeId, responseType);
        this.dateApplication = LocalDate.parse(dateApplication, DateTimeFormatter.ofPattern("d.M.yyyy"));
        this.waitingTime = waitingTime;
    }


    public static InputLineC parseOf(String inputString) {
        String[] partsOfInputString = inputString.split("\\s");
        if (partsOfInputString.length != 7) {
            throw new IncorrectLineException(inputString);
        }
        return new InputLineC(
                Integer.parseInt(partsOfInputString[0]),
                partsOfInputString[1].charAt(0),
                partsOfInputString[2],
                partsOfInputString[3],
                partsOfInputString[4].charAt(0),
                partsOfInputString[5],
                Integer.parseInt(partsOfInputString[6]));
    }

    public LocalDate getDateApplication() {
        return dateApplication;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InputLineC that = (InputLineC) o;
        return waitingTime == that.waitingTime &&
                dateApplication.equals(that.dateApplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateApplication, waitingTime);
    }
}
