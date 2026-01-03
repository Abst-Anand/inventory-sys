package com.inventory.Util;

public class CurrencyFormatter {
    public static String format(String amount) {
        if (amount == null || amount.isEmpty()) {
            return amount;
        }

        // Remove existing commas
        amount = amount.replace(",", "");

        String[] parts = amount.split("\\.");
        String integerPart = parts[0];
        String decimalPart = parts.length > 1 ? "." + parts[1] : "";

        int len = integerPart.length();
        if (len <= 3) {
            return integerPart + decimalPart;
        }

        String lastThree = integerPart.substring(len - 3);
        String remaining = integerPart.substring(0, len - 3);

        StringBuilder formatted = new StringBuilder();

        // Insert commas after every 2 digits in remaining part
        int remLen = remaining.length();
        int firstGroup = remLen % 2;

        if (firstGroup > 0) {
            formatted.append(remaining.substring(0, firstGroup)).append(",");
        }

        for (int i = firstGroup; i < remLen; i += 2) {
            formatted.append(remaining.substring(i, i + 2)).append(",");
        }

        formatted.append(lastThree);

        return "₹ "+formatted.toString() + decimalPart;
    }
}
