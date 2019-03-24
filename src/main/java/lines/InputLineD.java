package lines;

import exceptions.IncorrectLineException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class InputLineD extends InputLine {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    private InputLineD(int numberLineInFile, char typeLine, String serviceId, String questionTypeId, char responseType,
                       LocalDate dateFrom, LocalDate dateTo) {
        super(numberLineInFile, typeLine, serviceId, questionTypeId, responseType);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public static InputLineD parseOf(String inputString) {
        String[] partsOfInputString = inputString.split("\\s");
        if (partsOfInputString.length != 6) {
            throw new IncorrectLineException(inputString);
        }
        return new InputLineD(
                Integer.parseInt(partsOfInputString[0]),
                partsOfInputString[1].charAt(0),
                partsOfInputString[2],
                partsOfInputString[3],
                partsOfInputString[4].charAt(0),
                pareDateFrom(partsOfInputString[5]),
                parseDateTo(partsOfInputString[5]));
    }


    private static LocalDate pareDateFrom(String inputRangeString) {
        int i;
        if ((i = inputRangeString.indexOf('-')) == -1) {
            return LocalDate.parse(inputRangeString, DateTimeFormatter.ofPattern("d.M.yyyy"));
        } else {
            String date = inputRangeString.substring(0,i);
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.yyyy"));
        }
    }

    private static LocalDate parseDateTo(String inputRangeString) {
        int i;
        if ((i = inputRangeString.indexOf('-')) == -1) {
            return LocalDate.now();
        } else {
            String date = inputRangeString.substring(i + 1);
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("d.M.yyyy"));
        }
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InputLineD that = (InputLineD) o;
        return dateFrom.equals(that.dateFrom) &&
                dateTo.equals(that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateFrom, dateTo);
    }
}
