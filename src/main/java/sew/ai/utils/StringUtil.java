package sew.ai.utils;

import org.apache.logging.log4j.message.ParameterizedMessage;
import org.openqa.selenium.InvalidArgumentException;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {
    private static final String SURROUNDING_QUOTES = "^[\"'].*[\"']$";

    /**
     * Returns a formatted String by replacing each instance of {} placeholders  with the given arguments.
     *
     * @param messagePattern the message pattern containing placeholders.
     * @param arguments      the arguments to be used to replace placeholders.
     * @return String the formatted message
     */
    public static String format(final String messagePattern, Object... arguments) {
        return ParameterizedMessage.format(messagePattern, arguments);
    }

    /**
     * Returns the string passed without surrounding single (') or double (") quotes
     * if they exist. Otherwise it returns the string unchanged. Quotes inside the string
     * will not be removed. Likewise, if there are not matching quotes on both ends, a leading or
     * trailing quote will be left unmodified.
     *
     * @param text String the text to check for surrounding quotes
     * @return String the text without surrounding quotes (if they exist)
     */
    public static String stripSurroundingQuotes(String text) {
        if (text == null)
            return null;
        Pattern pattern = Pattern.compile(SURROUNDING_QUOTES);
        if (pattern.matcher(text).find())
            return text.substring(1, text.length() - 1);
        return text;
    }

    /**
     * Constructs the ordinal string of the given int. For example "1st", "2nd", "111th"
     *
     * @param i int the integer to create an ordinal string for
     * @return String the ordinal string
     */
    public static String ordinal(int i) {
        if (i < 0)
            throw new InvalidArgumentException("Integer must be non-negative to produce ordinal.");
        String[] suffixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];
        }
    }

    public static String getSubString(String fullString, int beginIndex, int endIndex) {
        String subString = null;
        if (fullString.length() >= 20) {
            subString = fullString.substring(beginIndex, endIndex);
        }
        return subString;
    }

    public static String getValueBeforeDecimal(String value) {
        String[] split = value.split("\\.");
        return split[0];
    }

    /**
     * This method is used to get the last four digits of Credit and Bank
     * accounts
     *
     * @param inputString
     * @return
     */
    public static String getLastFourChars(String inputString) {
        String lastFourDigits = "";
        if (inputString.length() > 4) {
            lastFourDigits = inputString.substring(inputString.length() - 4);
        }
        else {
            lastFourDigits = inputString;
        }
        System.out.println(lastFourDigits);
        return lastFourDigits;
    }

    public static void compareIntValues(int expected, int actual) {
        Assert.assertEquals(expected, actual);
    }

    public static String splitString(String splitValue, String replaceValue, String delimiter) {
        splitValue = splitValue.replace(replaceValue, "").trim();
        String arraySplit[] = splitValue.split(delimiter);
        return arraySplit[1];
    }

    public static int getStringLengthAfterDecimal(String splitValue, String replaceValue) {
        int iDecimal = 0;
        if (splitValue.contains(".")) {
            splitValue = splitValue.replace(replaceValue, "").trim();
            String aSplit[] = splitValue.split("\\.");
            iDecimal = aSplit[1].length();
        }
        else {
            iDecimal = 0;
        }
        return iDecimal;
    }

    public static void verifySplitStringLengthAfterDecimal(String splitValue, String replaceValue,
                                                           String delimiter) {
        splitValue = splitValue.replace(replaceValue, "").trim();
        String arraySplit[] = splitValue.split(delimiter);
        Assert.assertEquals(2, arraySplit[1].length());
    }

    public static String addCommaToArrayList(List<String> arrayList) {
        String returnedString;
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            if (sb.length() > 0)
                sb.append(",");
            sb.append(iterator.next());
        }
        returnedString = sb.toString();
        return returnedString;
    }

    public static String addCommaToArray(String[] array) {
        String returnedString;
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<array.length; i++) {
            if (sb.length() > 0 && i<array.length)
                sb.append(",");
        }
        returnedString = sb.toString();
        return returnedString;
    }

    public static String removeCharsFromBalance(String sRemainingBalance) {
        if (sRemainingBalance.contains("$")) {
            sRemainingBalance = sRemainingBalance.replace("$", "");
            if ((sRemainingBalance.contains("CR")) && (sRemainingBalance.contains(","))) {
                sRemainingBalance = sRemainingBalance.replace("CR", "")
                        .replace(",", "").trim();
            }
            else if ((!sRemainingBalance.contains("CR")) && (sRemainingBalance.contains(","))) {
                sRemainingBalance = sRemainingBalance.replace(",", "").trim();
            }
            else if ((!sRemainingBalance.contains("CR")) && (!sRemainingBalance.contains(","))) {
                sRemainingBalance = sRemainingBalance.replace(",", "").trim();
            }
            else if ((sRemainingBalance.contains("CR")) && (!sRemainingBalance.contains(","))) {
                sRemainingBalance = sRemainingBalance.replace("CR", "").trim();
            }
        }
        return sRemainingBalance;
    }

    /**
     * @param sNumber
     * @return US format phone numbers
     */
    public static String formatMobileNumberUsFormat(String sNumber) {
        String number = null;
        number = sNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        return number;
    }

    public static String getTemporaryPasswordFromEmail(String emailContent) {
        String strippedText = emailContent.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*", " ");
        String tempPassword = strippedText.split("Password: ")[1].split(" ")[0];
        return tempPassword;
    }

    public static boolean isAlpha(String input) {
        boolean isAlpha = false;
        input = input.replaceAll(" ", "");
        isAlpha = input.matches("[a-zA-Z]+");
        return isAlpha;
    }

    public static boolean isNumeric(String input) {
        boolean isNumeric = false;
        isNumeric = input.matches("[0-9]+");
        return isNumeric;
    }

    public static String getUtilityBillFormat(String apibillContent, String currency) {
        if (apibillContent.contains("CR")) {
            apibillContent = "-" + apibillContent.replace("CR", "")
                    .replace(currency, "").replace("A", "").trim();
        }
        else {
            apibillContent = apibillContent.replace(currency, "")
                    .replace("A", "").trim();
        }
        return apibillContent;
    }
}
