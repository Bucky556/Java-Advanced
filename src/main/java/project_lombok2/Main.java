package project_lombok2;


import lombok.Cleanup;
import lombok.extern.java.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Log
public class Main {
    public static void main(String[] args) throws IOException {
        /*Employee employee = new Employee("Okilov Nodirjon",19);
        System.out.println(employee);*/

        /*Employee employee = Employee.builder().fullName("Nodirjon").age(19).build();
        System.out.println(employee);

        Manager manager = Manager.childBuilder()
                .fullName("Akbar")
                .age(21)
                .role("Software Engineer")
                .build();
        System.out.println(manager);*/

        //log.severe("Severe problem occured!!!");

        /*@Cleanup FileOutputStream outputStream = new FileOutputStream("test.txt");
        outputStream.write("Nodirjon".getBytes());*/
        @Cleanup FileInputStream fileInputStream = new FileInputStream("test.txt");
        int c;
        while ((c = fileInputStream.read()) != -1){
            System.out.println((char) c);
        }

    }
}
