package lines;

import java.util.Objects;

public class InputLine {

    private int numberLineInFile;
    private char typeLine;
    private String serviceId;
    private String questionTypeId;
    private char responseType;

    protected InputLine(int numberLineInFile, char typeLine, String serviceId, String questionTypeId, char responseType) {
        this.numberLineInFile = numberLineInFile;
        this.typeLine = typeLine;
        this.serviceId = serviceId;
        this.questionTypeId = questionTypeId;
        this.responseType = responseType;
    }

    public int getNumberLineInFile() {
        return numberLineInFile;
    }

    public char getTypeLine() {
        return typeLine;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getQuestionTypeId() {
        return questionTypeId;
    }

    public char getResponseType() {
        return responseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputLine inputLine = (InputLine) o;
        return numberLineInFile == inputLine.numberLineInFile &&
                typeLine == inputLine.typeLine &&
                responseType == inputLine.responseType &&
                serviceId.equals(inputLine.serviceId) &&
                questionTypeId.equals(inputLine.questionTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberLineInFile, typeLine, serviceId, questionTypeId, responseType);
    }

    @Override
    public String toString() {
        return "InputLine{" +
                "numberLineInFile=" + numberLineInFile +
                ", typeLine=" + typeLine +
                ", serviceId='" + serviceId + '\'' +
                ", questionTypeId='" + questionTypeId + '\'' +
                ", responseType=" + responseType +
                '}';
    }
}
