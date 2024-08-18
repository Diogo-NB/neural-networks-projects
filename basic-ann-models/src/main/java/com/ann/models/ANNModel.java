package com.ann.models;

// Interface que define os métodos básicos para os modelos
public interface ANNModel {

    void train(int[] input, int target);

    void train(int[][] input, int[] target);

    float test(int[] array);

    void reset();

}