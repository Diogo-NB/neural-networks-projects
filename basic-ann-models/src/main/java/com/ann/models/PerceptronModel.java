package com.ann.models;

import com.ann.util.RandomUtil;

public class PerceptronModel implements ANNModel {

    // Pesos
    private float[] weigths;
    // Bias
    private float bias;
    // Taxa de aprendizado
    private float learningRate = 0.1f;

    public PerceptronModel(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        weigths = new float[size];
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = RandomUtil.randomFloat(-0.5f, +0.5f);
        }

        bias = RandomUtil.randomFloat(-0.5f, +0.5f);
    }

    public PerceptronModel(int size, float learningRate) {
        this(size);
        this.learningRate = learningRate;
    }

    @Override
    public String toString() {
        return "Modelo Perceptron";
    }

    public float[] getWeigths() {
        return weigths.clone();
    }

    public float getBias() {
        return bias;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public void train(int[] input, int target) {
        train(new int[][] { input }, new int[] { target });
    }

    public void train(int[][] input, int[] target) {
        if (input.length != target.length || input[0].length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        boolean trained = false;
        int count = 0;

        while (!trained) {
            trained = true; // Assume que está treinado

            for (int i = 0; i < input.length; i++) {
                float yLiq = test(input[i]);
                int y = yLiq >= 0.0f ? 1 : -1;

                if (y != target[i]) {
                    // Target não foi atingido
                    trained = false;
                    count++;

                    // Ajuste dos pesos
                    for (int j = 0; j < input[i].length; j++) {
                        weigths[j] += learningRate * input[i][j] * target[i];
                    }

                    bias += learningRate * target[i];
                }
            }
        }

        System.out.println("Iterações: " + count);
    }

    public float test(int[] array) {
        float result = 0.0f;

        for (int j = 0; j < weigths.length; j++) {
            result += array[j] * weigths[j];
        }
        result += bias;
        return result;
    }

    public void reset() {
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = RandomUtil.randomFloat(-0.5f, +0.5f);
        }
        bias = RandomUtil.randomFloat(-0.5f, +0.5f);
    }

}
