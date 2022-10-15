package org.example.facade;

import org.example.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DBUtil {

    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getAllEmployee(String mobile) throws Exception {
        delay(5);

        List<Employee> employees = getEmployees();

        employees.stream()
                .filter(employee -> employee.getPhone().equals(mobile))
                .collect(Collectors.toList());

        if (mobile.contains("111")) {
            delay(5);
        }
        if (mobile.contains("222")) {
            delay(5);
        }
        if (mobile.contains("333")) {
            delay(10);
            int i = 3333 / 0;
        }
        if (mobile.contains("444")) {
            int i = 3333 / 0;
            delay(5);
        }
        if (mobile.contains("555")) {
            delay(5);
        }
        return employees;
    }

    private List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
//        Reader reader = Files.newBufferedReader(Paths.get("employees.json"));
//        employees =new Gson().fromJson(reader, new TypeToken<List<Employee>>() {}.getType());
//        employees = new ObjectMapper().readValue(new File("employees.json"), new TypeReference<List<Employee>>() {});

        employees.add(Employee.builder().id(1).name("abc1").email("abc1@test.com").phone("0512-11111").salary(5000).build());
        employees.add(Employee.builder().id(2).name("abc2").email("abc2@test.com").phone("0512-22222").salary(5000).build());
        employees.add(Employee.builder().id(3).name("abc3").email("abc3@test.com").phone("0512-33333").salary(5000).build());
        employees.add(Employee.builder().id(4).name("abc4").email("abc4@test.com").phone("0512-44444").salary(5000).build());
        employees.add(Employee.builder().id(5).name("abc5").email("abc5@test.com").phone("0512-55555").salary(5000).build());
        return employees;
    }

}
