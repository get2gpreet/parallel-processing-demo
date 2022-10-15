package org.example.service;


import org.apache.commons.collections4.CollectionUtils;
import org.example.facade.DBUtil;
import org.example.model.Employee;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AsyncService {

    LocalDateTime now = LocalDateTime.now();

    public CompletableFuture<Object> createCompletableFuture(String sim) {
        CompletableFuture<Object> addAsync = CompletableFuture.supplyAsync(() -> {
            DBUtil dbUtil = new DBUtil();
            List<Employee> fromDB = null;
            try {
                fromDB = dbUtil.getAllEmployee(sim);
            } catch (Exception e) {
                System.out.println(ChronoUnit.SECONDS.between(now, LocalDateTime.now()));
                System.out.println("#####----Error occurred during sim:" + sim);
            }

            if (CollectionUtils.isNotEmpty(fromDB)) {
                System.out.println(ChronoUnit.SECONDS.between(now, LocalDateTime.now()));
                System.out.println("-----Success response from DB sim : " + sim);
            }

            if (CollectionUtils.isEmpty(fromDB)) {
                System.out.println(ChronoUnit.SECONDS.between(now, LocalDateTime.now()));
                System.out.println("****----Not Found in DB from DB sim : " + sim);
            }

            System.out.println("---------------------------------------------------");
            //fromDB.forEach(System.out::println);
            return fromDB;
        });
        return addAsync;
    }

    public void startParallelProcessing(List<String> sims) {
        List<CompletableFuture<Object>> futureList = new ArrayList<>();
        System.out.println("Parallel Processing Started " + new java.util.Date());
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        sims.forEach(sim -> futureList.add(createCompletableFuture(sim)));

        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        CompletableFuture<List<Object>> allCompletableFuture = allFutures.thenApply(future -> futureList.stream().map(completableFuture -> completableFuture.join()).collect(Collectors.toList()));
        CompletableFuture<List<Object>> completableFuture = allCompletableFuture.toCompletableFuture();
        try {
            Long count = completableFuture.get().stream().filter(futureResponse -> Objects.isNull(futureResponse)).count();
            if (count > 0) {
                System.out.println("error occurred");
                throw new Exception("An Error Occurred" + count);
            } else {
                System.out.println("No error");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
