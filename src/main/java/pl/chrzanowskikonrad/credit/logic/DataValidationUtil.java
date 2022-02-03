package pl.chrzanowskikonrad.credit.logic;

import java.math.BigDecimal;

public class DataValidationUtil {

    public static void validateCreditNameField(String creditName) {
        if (creditName != null) {
            if (creditName.length() > 50) {
                throw new IllegalArgumentException(prepareMessageForTooLongCreditNameField());
            }
            if (creditName.isEmpty()) {
                throw new IllegalArgumentException(prepareMessageForEmpty());
            }
        } else {
            throw new IllegalArgumentException(prepareMessageForEmpty());
        }
    }


    public static void validateCreditValue(BigDecimal value) {
        if (value != null) {
            if (!isValuePositive(value)) {
                throw new IllegalArgumentException(prepareMessageForNegativeValue());
            }
        } else {
            throw new IllegalArgumentException(prepareMessageForEmpty());
        }
    }

    private static String prepareMessageForTooLongCreditNameField() {
        return "Nazwa kredytu nie może być dłuższa niż 255 znaków.";
    }

    private static String prepareMessageForNegativeValue() {
        return "Pole  'Wartość kredytu'  nie może mieć wartości ujemnej.";
    }

    private static String prepareMessageForEmpty() {
        return "Pole nie może być puste.";
    }

    private static boolean isValuePositive(BigDecimal value) {
        boolean isValuePositive = true;
        if (value != null) {
            String toCheck = value.toString();
            if (toCheck.startsWith("-")) {
                isValuePositive = false;
            }
        }
        return isValuePositive;
    }

}


