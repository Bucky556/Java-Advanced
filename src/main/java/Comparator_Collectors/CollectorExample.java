package Comparator_Collectors;

import Streams.StreamAPI.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class CollectorExample {
    public static void main(String[] args)throws Exception {
        Path path = Path.of("src/main/resources/employees.json");
        String jsonData = Files.readString(path);
        Gson gson = new Gson();
        Type type = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> employees = gson.fromJson(jsonData, type);
        String collect = employees.stream().collect(new ToXMLCollector());
        System.out.println(collect);
    }
}
    class ToXMLCollector implements Collector<Employee, StringBuffer, String> {
        final String xmlstr = """
                \n\t<employee id='%s'> 
                \t\t<fullName>%s</fullName>
                \t\t<gender>%s</gender>
                \t\t<age>%s</age>
                \t</employee›""";

        public Supplier<StringBuffer> supplier() {
            return StringBuffer::new;
        }

        public BiConsumer<StringBuffer, Employee> accumulator() {
            return (sb, e) -> sb.append(String.format(xmlstr, e.getId(), e.getFullName(),e.getGender(),e.getAge()));
        }

        public BinaryOperator<StringBuffer> combiner() {
            return (sb1, sb2) -> sb1.append(sb2.toString());
        }

        public Function<StringBuffer, String> finisher() {
            return sb -> String.format("<employees>%s\n</employees>", sb.toString());
        }

        public Set<Characteristics> characteristics() {
            return EnumSet.of(Characteristics.CONCURRENT);
        }
    }