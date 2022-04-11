package persistence;

import model.Performance;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonTest {
    protected void checkPerformance(String name, int year, String event, Performance performance) {
        assertEquals(name, performance.getName());
        assertEquals(year, performance.getYear());
        assertEquals(event, performance.getEvent());
    }
}
