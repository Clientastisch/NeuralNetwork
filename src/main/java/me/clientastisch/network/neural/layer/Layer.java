package me.clientastisch.network.neural.layer;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Layer implements Serializable, Cloneable {

    @Getter private final CopyOnWriteArrayList<Neuron> neurons;

    public Layer(Neuron... neuron) {
        this.neurons = new CopyOnWriteArrayList<>();
        neurons.addAll(Arrays.asList(neuron));

        for (int index = 0; index < this.neurons.size(); index++)
            this.neurons.get(index).setIndex(index);
    }
}
