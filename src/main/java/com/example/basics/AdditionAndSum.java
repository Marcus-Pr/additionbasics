package com.example.basics;

public class AdditionAndSum {
    private int addable1;
    private int addable2;
    private int sum;

    AdditionAndSum () {}

    AdditionAndSum(int addable1, int addable2, int sum) {
        this.addable1 = addable1;
        this.addable2 = addable2;
        this.sum = sum;
    }

    public int getAddable1() {
        return addable1;
    }

    public int getAddable2() {
        return addable2;
    }

    public int getSum() {
        return sum;
    }

    public void setAddable1(int addable1) {
        this.addable1 = addable1;
    }

    public void setAddable2(int addable2) {
        this.addable2 = addable2;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
