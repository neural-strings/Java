package ru.liga.internshipspringshell;

/**
 * CargoCar - грузоперевозочная машина с 6x6 мест для груза.
 */
public class CargoCar {
    private int numberName;
    private int[][] cargo = new int[6][6];

    CargoCar(int numberName) {
        this.numberName = numberName;
    }

    /**
     * getNumberName() - выводит номер машины.
     */
    public int getNumberName() {
        return numberName;
    }

    /**
     * getCargo() - выводит груз, как int[][]
     */
    public int[][] getCargo() {
        return cargo;
    }

    /**
     * getCargoAtPos(i,j) - выводит int значение груза в определённой точке.
     */
    public int getCargoAtPos(int i, int j) {
        return cargo[i][j];
    }

    /**
     setCargoAtPos(i, j, новое значение) - вставка нового int.
     */
    public void setCargoAtPos(int i, int j, int newValue) {
        this.cargo[i][j] = newValue;
    }
}
