package Tasks;


import lombok.Cleanup;

import java.io.FileWriter;
import java.io.IOException;

public class ComparatorTask {
    public static void main(String[] args) {
        String htmlText = """
                <table>
                             <tr>
                                <th>ID</th>
                                <th>name</th>
                                <th>gender</th>
                                <th>age</th>
                             </tr>
                        
                             <tr>
                                 <td>1</td>
                                 <td>Tarra Prohaska</td>
                                 <td>MALE</td>
                                 <td>16</td>
                             </tr>
                                 <td>2</td>
                                 <td>Allen Walker</td>
                                 <td>FEMALE</td>
                                 <td>29</td>
                             </tr>
                             <tr>
                                 <td>3</td>
                                 <td>Jack Davis DVM</td>
                                 <td></td>MALE</td>
                                 <td>17</td>
                             </tr>
                        </table>
                        
                """;

        try {
            @Cleanup
            FileWriter fileWriter = new FileWriter("table.html");
            fileWriter.write(htmlText);
            System.out.println("File created");
        } catch (IOException e) {
            System.out.println("Something wrong" + e.getMessage());
        }
    }
}
