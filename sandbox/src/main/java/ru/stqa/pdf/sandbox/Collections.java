package ru.stqa.pdf.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "PHP"};
        List<String> languages = Arrays.asList("Java", "C#", "PHP");

        for (String l : langs) {
            System.out.println(l);
        }

        for (String l : languages) {
            System.out.println(l);
        }
    }
}
