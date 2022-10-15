package org.example;

import org.example.service.AsyncService;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> sims = Arrays.asList("0512-11111", "0512-22222", "0512-33333", "0512-44444", "0512-55555", "0512-99999");
        AsyncService asynDemo = new AsyncService();
        asynDemo.startParallelProcessing(sims);

    }
}