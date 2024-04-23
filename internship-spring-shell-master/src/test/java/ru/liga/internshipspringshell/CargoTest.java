package ru.liga.internshipspringshell;

import org.junit.jupiter.api.Test;

class CargoTest {

    @Test
    void cargoCarTest() {
        CargoCar a = new CargoCar(1);
        System.out.println(a.getNumberName());
        a.setCargoAtPos(0, 0, 5);
        System.out.println(a.getCargoAtPos(0, 0));
        for (int i = 0; i < 6; i++) {
            String str = "";
            for (int j = 0; j < 6; j++) {
                int pos = a.getCargoAtPos(i,j);
                char[] pos1 = Character.toChars(pos);
                str = str + pos;
            }
            System.out.println(str);
        }
    }
}
