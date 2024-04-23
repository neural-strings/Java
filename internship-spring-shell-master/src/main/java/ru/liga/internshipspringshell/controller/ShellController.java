package ru.liga.internshipspringshell.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@ShellComponent //аннотация необходима чтобы Spring увидел класс как класс-обработчик консоли
public class ShellController {

    @ShellMethod("Method returns greetings")
    //@ShellMethod - обязательная аннотация, без нее команда не будет доступна для вызова (spring не будет ее видеть), описание внутри тоже обязательно
    public String hello(@ShellOption String who) {
        //@ShellOption необязательная аннотация, но бывает полезна, если нужно изменить
        // обязательность или название аргумента
        return "Hello " + who;
    }

    @ShellMethod("Method return money info")
    public StringBuffer codes() throws Exception {
        StringBuffer res = new StringBuffer();

        Path codesPath = Paths.get("src/main/resources/tsv/currencies.tsv");

        Stream<String> stream = Files.lines(codesPath, StandardCharsets.UTF_8);
        {
            stream.forEach(str -> res
                    .append(str.replaceAll("[0-9]", "")
                            .replace(",", "") + "\n"));
        }
        return res;
    }

    @ShellMethod("Method converts the transferred amount from one currency to another")
    public StringBuffer convertor(int val, String from, String to) throws Exception {

        StringBuffer res = new StringBuffer();

        String rub = "RUB";
        String lineFrom = "";
        String lineTo = "";

        Path codesPath = Paths.get("src/main/resources/tsv/currencies.tsv");

        Stream<String> stream1;
        Stream<String> stream2;
        stream1 = Files.lines(codesPath, StandardCharsets.UTF_8);
        stream2 = Files.lines(codesPath, StandardCharsets.UTF_8);

        lineFrom = (stream1.filter(x -> x.contains(from))
                .findFirst()
                .orElse(null));

        lineTo = (stream2.filter(x -> x.contains(to))
                .findFirst()
                .orElse(null));

        StringBuilder nominalCountFrom = new StringBuilder("");
        StringBuilder nominalCountTo = new StringBuilder("");
        StringBuilder numbersCont = new StringBuilder("");
        StringBuilder numbersContFrom = new StringBuilder("");
        String numbSpecSim = "0123456789,";
        int nominalTo = 1;
        int nominalFrom = 1;
        double coefficient = 1;
        double coefficientFrom = 1;

        for (int i = 4; i < lineTo.length(); i++) {

            String j = String.valueOf(lineTo.charAt(i));

            if (numbSpecSim.contains(j) && j != " ") {
                nominalCountTo.append(lineTo.charAt(i));
            } else {
                nominalTo = Integer.valueOf(nominalCountTo.toString());
                break;
            }
        }

        if (lineFrom != null) {
            for (int i = 4; i < lineFrom.length(); i++) {

                String j = String.valueOf(lineFrom.charAt(i));

                if (numbSpecSim.contains(j) && j != " ") {
                    nominalCountFrom.append(lineFrom.charAt(i));
                } else {
                    nominalFrom = Integer.valueOf(nominalCountFrom.toString());
                    break;
                }
            }
        }

        System.out.println("nominal: " + nominalFrom);

        for (int i = lineTo.length() - 1; i > 0; i--) {

            String j = String.valueOf(lineTo.charAt(i));

            if (numbSpecSim.contains(j) && j != " ") {
                numbersCont.append(lineTo.charAt(i));
            } else {
                numbersCont.reverse();
                coefficient = Double.valueOf(numbersCont.toString()
                        .replace(',', '.'));
                break;
            }
        }

        if (lineFrom != null) {
            for (int i = lineFrom.length() - 1; i > 0; i--) {

                String j = String.valueOf(lineFrom.charAt(i));

                if (numbSpecSim.contains(j) && j != " ") {
                    numbersContFrom.append(lineFrom.charAt(i));
                } else {
                    numbersContFrom.reverse();
                    coefficientFrom = Double.valueOf(numbersContFrom.toString()
                            .replace(',', '.'));
                    break;
                }
            }
        }

        if (lineFrom == null) {
            String res2 = String.valueOf(val / (coefficient / nominalTo));
            res = res.append(res2);
        } else {
            String res2 = String.valueOf(((val * coefficientFrom) / nominalFrom) / ((coefficient) / nominalTo));
            res = res.append(res2);
        }


        return res.append(" " + String.valueOf(to));

        //Запрос ввида: convertor 100 --from RUB --to AUD
        //Запрос ввида: convertor 100 --from USD --to AUD
        //Запрос ввида: convertor 100 --from USD --to INR
    }

//    @ShellMethod("Method ")
//    public

}

