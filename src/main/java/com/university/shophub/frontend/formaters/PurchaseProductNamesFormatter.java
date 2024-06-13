package com.university.shophub.frontend.formaters;

import org.jetbrains.annotations.NotNull;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Component
public class PurchaseProductNamesFormatter implements Formatter<List<String>> {
    @Override
    public @NotNull List<String> parse(String text, @NotNull Locale locale) throws ParseException {
        return List.of(text.split(","));
    }

    @Override
    public @NotNull String print(@NotNull List<String> object, @NotNull Locale locale) {
        return String.join(",", object);
    }
}
