package Streams.StreamAPI;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String fullName;
    private String gender;
    private int age;
    private int id;
}
