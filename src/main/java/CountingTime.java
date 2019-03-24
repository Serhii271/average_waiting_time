import factory.InputLineFactory;
import lines.InputLine;
import lines.InputLineC;
import lines.InputLineD;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CountingTime {
    private static List<InputLine> cLineList = new ArrayList<>();
    private static List<InputLine> dLineList = new ArrayList<>();
    private static final AtomicInteger count = new AtomicInteger();

    public static List<String> counting(String filePath) throws IOException {
        createLineList(filePath);
        List<String> result = new ArrayList<>(dLineList.size());
        dLineList.stream()
                .map(s -> (InputLineD) s)
                .sorted(Comparator.comparingInt(InputLine::getNumberLineInFile))
                .forEach(s -> result.add(timeWaitingForInputLineD(s)));

        return result;
    }

    private static void createLineList(String filePath) throws IOException {
        Files.lines(Paths.get(filePath))
                .skip(1)
                .map(s -> count.incrementAndGet() + " " + s)
                .map(InputLineFactory::createInputLine)
                .forEach(s ->
                {
                    if (s.getTypeLine() == 'C') {
                        cLineList.add(s);
                    } else {
                        dLineList.add(s);
                    }
                });
    }


    private static boolean matchTwoLines(InputLineD inputLineD, InputLineC inputLineC) {
        if (inputLineD.getNumberLineInFile() < inputLineC.getNumberLineInFile()) {
            return false;
        }
        if (inputLineD.getResponseType() != inputLineC.getResponseType()) {
            return false;
        }
        if (
                !inputLineD.getServiceId().equals("*")
                        && !inputLineD.getServiceId().startsWith(inputLineC.getServiceId())
                        && !inputLineC.getServiceId().startsWith(inputLineD.getServiceId())) {
            return false;
        }

        if (
                !inputLineD.getQuestionTypeId().equals("*")
                        && !inputLineD.getQuestionTypeId().startsWith(inputLineC.getQuestionTypeId())
                        && !inputLineC.getQuestionTypeId().startsWith(inputLineD.getQuestionTypeId())) {
            return false;
        }

        if (
                inputLineD.getDateFrom().compareTo(inputLineC.getDateApplication()) > 0
                        || inputLineD.getDateTo().compareTo(inputLineC.getDateApplication()) < 0) {
            return false;
        }
        return true;
    }

    private static String timeWaitingForInputLineD(InputLineD inputLineD) {
        OptionalDouble average =
                cLineList.stream()
                        .map(s -> (InputLineC) s)
                        .filter(s -> matchTwoLines(inputLineD, s))
                        .mapToInt(s -> s.getWaitingTime())
                        .average();
        int result = (int) Math.round(average.orElse(-1));
        if (result == -1) {
            return "-";
        } else {
            return Integer.toString(result);
        }
    }


}
