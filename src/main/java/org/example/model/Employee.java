package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Employee {

    public int id;
    public String name;
    public int salary;
    public String department;
    public String email;
    public String phone;
}