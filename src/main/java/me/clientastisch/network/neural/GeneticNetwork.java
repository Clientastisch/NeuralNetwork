package me.clientastisch.network.neural;

import me.clientastisch.network.neural.functions.FunctionType;
import me.clientastisch.network.neural.functions.Random;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("ALL")
public class GeneticNetwork extends NeuralNetwork {

    private AtomicReference<Double> score = new AtomicReference<>(new Double(0));

    public GeneticNetwork(FunctionType function, double score, int... neuronsInLayers) {
        super(function, neuronsInLayers);
        this.score.set(score);
    }

    public synchronized void mutate(double rate, double strength) {
        Random random = new Random();
        getLayers().stream().skip(1).forEach(layer -> {
            layer.getNeurons().forEach(neuron -> {
                /* MUTATE BIAS */
                if(Math.random() < rate)
                    neuron.setBias(neuron.getBias() + (random.nextGaussian() * strength));

                /* MUTATE WEIGHTS */
                Arrays.stream(neuron.getWeights()).forEach(weight -> {
                    if(Math.random() < rate)
                        weight += random.nextGaussian() * strength;
                });
            });
        });
    }

    public double getScore() {
        return score.get();
    }

    public void judge(double value) {
        score.set(score.get() + value);
    }
}