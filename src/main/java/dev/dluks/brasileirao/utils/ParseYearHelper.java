package dev.dluks.brasileirao.utils;

import dev.dluks.brasileirao.exceptions.InvalidYearException;

import java.util.Optional;

public class ParseYearHelper {

    private ParseYearHelper() {
    }

    public static Optional<Integer> parse(String year) {
        if (year == null || year.isBlank()) {
            return Optional.empty();
        }
        try {
            return Optional.of(Integer.parseInt(year));
        } catch (NumberFormatException e) {
            throw new InvalidYearException("O ano deve ser um número inteiro entre 2003 e 2023.");
        }
    }

}
