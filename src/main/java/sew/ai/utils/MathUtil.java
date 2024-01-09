package sew.ai.utils;

import org.testng.Assert;
import sew.ai.config.CSPConfiguration;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class MathUtil {
    private static DecimalFormat tillThreeDecimal = new DecimalFormat(".###");

    private static DecimalFormat tillTwoDecimal = new DecimalFormat(".##");

    public static void assertDifference(Double actValue, Double expValue) {
        double difference = 0;
        if (actValue > expValue) {
            difference = (actValue - expValue);
        }
        else if (actValue < expValue) {
            difference = (expValue - actValue);
        }
        else if (actValue == expValue) {
            difference = (expValue - actValue);
        }
        DecimalFormat df = new DecimalFormat("###.#######");
        Double diff = Double.valueOf(df.format(difference));
        Assert.assertTrue((diff == 0) || (diff < 0.1), "Act value : " + actValue + " Exp value : "
                + expValue);
    }

    public static void assertDifference(String actValue, String expValue) {
        double difference = 0;
        double actual;
        double expected;
        if (actValue.contains(":")) {
            String actSplit[] = actValue.split(":");
            actual = Double.parseDouble(actSplit[1].trim()
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        else {
            actual = Double.parseDouble(actValue
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (expValue.contains(":")) {
            String expSplit[] = actValue.split(":");
            expected = Double.parseDouble(expSplit[1].trim()
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        else {
            expected = Double.parseDouble(expValue
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (actual > expected) {
            difference = (actual - expected);
        }
        else if (actual < expected) {
            difference = (expected - actual);
        }
        else if (actual == expected) {
            difference = (expected - actual);
        }
        DecimalFormat df = new DecimalFormat("###.#######");
        Double diff = Double.valueOf(df.format(difference));
        Assert.assertTrue((diff == 0) || (diff < 1), "Act value : " + actValue +
                " and Exp Value : " + expValue + " Having difference of : " + difference);
    }

    public static void assertDifferenceAverage(String actValue, String expValue) {
        double difference = 0;
        double actual;
        double expected;
        if (actValue.contains(":")) {
            String actArr[] = actValue.split(":");
            actual = Double.parseDouble(actArr[1].trim()
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replace(",", ""));
        }
        else {
            actual = Double.parseDouble(actValue
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replace(",", ""));
        }
        if (expValue.contains(":")) {
            String expArr[] = actValue.split(":");
            expected = Double.parseDouble(expArr[1].trim()
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replace(",", ""));
        }
        else {
            expected = Double.parseDouble(expValue
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replace(",", ""));
        }
        if (actual > expected) {
            difference = (actual - expected);
        }
        else if (actual < expected) {
            difference = (expected - actual);
        }
        else if (actual == expected) {
            difference = (expected - actual);
        }
        DecimalFormat df = new DecimalFormat("###.#######");
        Double diff = Double.valueOf(df.format(difference));
        Assert.assertTrue((diff == 0) || (diff < 4), "Act value : " + actValue
                + " Exp value : " + expValue + " Difference of : " + diff);
    }

    public static void assertDifferenceGallon(String actValue, String expValue) {
        double difference = 0;
        double actual;
        double expected;
        if (actValue.contains(":")) {
            String actValArr[] = actValue.split(":");
            actual = Double.parseDouble(actValArr[1].trim()
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        else {
            actual = Double.parseDouble(actValue
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (expValue.contains(":")) {
            String expValArr[] = actValue.split(":");
            expected = Double.parseDouble(expValArr[1].trim()
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        else {
            expected = Double.parseDouble(expValue
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (actual > expected) {
            difference = (actual - expected);
        }
        else if (actual < expected) {
            difference = (expected - actual);
        }
        else if (actual == expected) {
            difference = (expected - actual);
        }
        DecimalFormat df = new DecimalFormat("###.#######");
        Double diff = Double.valueOf(df.format(difference));
        // System.out.println(diff);
        Assert.assertTrue((diff == 0) || (diff < 10));
    }

    public static void assertDiffGallon(String actValue, String expValue) {
        double difference = 0;
        double act;
        double exp;
        if (actValue.contains(":")) {
            String actArr[] = actValue.split(":");
            act = Double.parseDouble(actArr[1].trim()
                    .replace("Gal", "")
                    .replaceAll(",", "")
            );
        }
        else {
            act = Double.parseDouble(actValue
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (expValue.contains(":")) {
            String expArr[] = actValue.split(":");
            exp = Double.parseDouble(expArr[1].trim()
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        else {
            exp = Double.parseDouble(expValue
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (act > exp) {
            difference = (act - exp);
        }
        else if (act < exp) {
            difference = (exp - act);
        }
        else if (act == exp) {
            difference = (exp - act);
        }
        DecimalFormat df = new DecimalFormat("###.#######");
        Double diff = Double.valueOf(df.format(difference));
        Assert.assertTrue((diff == 0) || (diff < 4),
                "Act Consumption in Gallon : " + actValue + " Exp Consumption in Gallon: " + expValue);
    }

    public static void assertDifferenceHourly(String actValue, String expValue) {
        double difference = 0;
        double actual = 0;
        double expected = 0;
        if (actValue.contains(":")) {
            String actValArr[] = actValue.split(":");
            actual = Double.parseDouble(actValArr[1].trim()
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        else {
            actual = Double.parseDouble(actValue
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (expValue.contains(":")) {
            String expValArr[] = actValue.split(":");
            expected = Double.parseDouble(expValArr[1].trim()
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        else {
            expected = Double.parseDouble(expValue
                    .replace("kWh", "")
                    .replace(CSPConfiguration.dateMetricsConfig.get("Currency"), "")
                    .replace("HCF", "")
                    .replace("CCF", "")
                    .replace("Gal", "")
                    .replaceAll(",", ""));
        }
        if (actual > expected) {
            difference = (actual - expected);
        }
        else if (actual < expected) {
            difference = (expected - actual);
        }
        else if (actual == expected) {
            difference = (expected - actual);
        }
        DecimalFormat format = new DecimalFormat("###.#######");
        Double diff = Double.valueOf(format.format(difference));
        Assert.assertTrue((diff == 0) || (diff < 0.02));
    }

    public static String getValuesBeforeDecimal(Double value) {
        String strValue = value.toString();
        String sValueBeforeDecimal = strValue.substring(0, strValue.indexOf('.'));
        return sValueBeforeDecimal;
    }

    public static String getValuesAfterDecimal(String value) {
        String strValue = String.valueOf(value);
        String sValueAfterDecimal = strValue.substring(strValue.indexOf('.'));
        return sValueAfterDecimal;
    }

    public static String localeNumberFormatting(String number, int decimalPlaces) {
        String formattedNumber = "";
        try {
            NumberFormat numberFormat = NumberFormat.getInstance(new Locale("en", "US"));
            numberFormat.setMinimumFractionDigits(decimalPlaces);
            numberFormat.setMaximumFractionDigits(decimalPlaces);
            formattedNumber = numberFormat.format(Double.parseDouble(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedNumber;
    }

    // This method calculates the average value in an array list
    public static String getAverageValueInArrayList(List<Double> unitConsumed) {
        double sum = 0;
        for (int count = 0; count < unitConsumed.size(); count++) {
            sum = sum + unitConsumed.get(count);
        }
        double dAverage = sum / unitConsumed.size();
        return toRoundoff(dAverage);
    }

    public static String getAverageOfCollection(Map<String, Double> unitConsumption) {
        double sum = 0;
        for (Map.Entry<String, Double> entry : unitConsumption.entrySet()) {
            sum = sum + Double.parseDouble(toRoundoff(entry.getValue()));
        }
        double average = sum / 12;
        return toRoundoff(average);
    }

    // This method calculates the average value in an array list
    public static String getAverageGallonValue(List<Double> unitConsumption) {
        double sum = 0;
        for (int count = 0; count < unitConsumption.size(); count++) {
            sum = sum + unitConsumption.get(count);
        }
        double average = sum / unitConsumption.size();
        return toRoundoff(average * 748);
    }

    // This method calculates the Highest value in an array list
    public static String getHighestInCollection(List<Double> unitConsumptions) {
        double highestVal = Collections.max(unitConsumptions);
        return toRoundoff(highestVal);
    }

    public static String getHighestValueInCollection(Map<String, Double> unitsConsumed) {
        String highestKey = Collections.max(unitsConsumed.entrySet(), Map.Entry.comparingByValue()).getKey();
        return toRoundoff(unitsConsumed.get(highestKey));
    }

    // This method calculates the Highest value in an array list
    public static String getHighestUsageConsumption(List<Double> unitConsumedMonthly) {
        double dHighestVal = Collections.max(unitConsumedMonthly);
        return toRoundoff(dHighestVal * 748);
    }

    // This method gets the maximum demand for the usage
    public static List<String> getMaxDemand(List<Double> usageAll) {
        List<String> maxDemand = new ArrayList<>();
        for (int count = 0; count < usageAll.size(); count++) {
            maxDemand.add(tillThreeDecimal.format(usageAll.get(count) * 1.2));
        }
        return maxDemand;
    }

    public static int getCountAfterDecimalPoint(String number) {
        int stringLength = 0;
        String[] result = number.split("\\.");
        stringLength = result[1].length();
        return stringLength;
    }

    /**
     * This Method is used to get the int count of a number
     *
     * @param n
     * @return
     * @author Souradeep.Ghosh
     */
    public static int getDigitCount(long n) {
        int count = 0;
        while (n != 0) {
            n = n / 10;
            ++count;
        }
        return count;
    }

    public static String toRoundoff(String value) {
        double dValue = Double.parseDouble(value);
        double roundOff = Math.round(dValue * 100.0) / 100.0;
        String sRoundOff = String.format("%.2f", roundOff);
        return sRoundOff;
    }

    public static String toRoundoff(Double value) {
        double roundOff = Math.round(value * 100.0) / 100.0;
        String sRoundOff = String.format("%.2f", roundOff);
        return sRoundOff;
    }

    // This method calculates the round off value in an array list
    public static void main(String[] arg) {
        System.out.println(toRoundoff("515.2"));
    }
}