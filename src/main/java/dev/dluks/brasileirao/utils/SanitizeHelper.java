package dev.dluks.brasileirao.utils;

public class SanitizeHelper {

    private SanitizeHelper() {
    }

    private static final String[] SPECIAL_CHARS = {"\"", "\u00A0"};

    public static String sanitize(String value) {
        for (String specialChar : SPECIAL_CHARS) {
            value = value.replace(specialChar, "");
        }
        return value;
    }

    public static String[] sanitize(String[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = sanitize(values[i]);
        }
        return values;
    }

}
