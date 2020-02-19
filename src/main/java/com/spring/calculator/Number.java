package com.spring.calculator;

import javax.validation.constraints.Min;

public class Number {
    @Min(value=0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk1;
    @Min(value=0, message = "Validacijos klaida: skaičius negali būti neigiamas")
    private int sk2;
    private String zenklas;
    private int rezult;

    public Number() {
    }

    public Number(int sk1, int sk2, String zenklas, int rezult) {
        this.sk1 = sk1;
        this.sk2 = sk2;
        this.zenklas = zenklas;
        this.rezult = rezult;
    }

    public int getSk1() {
        return sk1;
    }

    public void setSk1(int sk1) {
        this.sk1 = sk1;
    }

    public int getSk2() {
        return sk2;
    }

    public void setSk2(int sk2) {
        this.sk2 = sk2;
    }

    public String getZenklas() {
        return zenklas;
    }

    public void setZenklas(String zenklas) {
        this.zenklas = zenklas;
    }

    public int getRezult() {
        return rezult;
    }

    public void setRezult(int rezult) {
        this.rezult = rezult;
    }
}
