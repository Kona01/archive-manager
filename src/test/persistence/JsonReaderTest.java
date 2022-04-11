package persistence;

import model.Archive;
import model.Collection;
import model.Performance;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Archive ar = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyArchive() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyArchive.json");
        try {
            Archive ar = reader.read();
            List<Collection> data = ar.getArchive();
            assertEquals(0, data.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralArchive() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralArchive.json");
        try {
            Archive ar = reader.read();
            List<Collection> data = ar.getArchive();
            assertEquals(2, data.size());
            Collection c1 = data.get(0);
            assertEquals("Piano", c1.getName());
            List<Performance> performances = c1.getList();
            Performance p1 = performances.get(0);
            checkPerformance("Moonlight Sonata",2019,"Annual Spring Recital",p1);
        } catch(IOException e) {
            fail("Couldn't read from file");
        }
    }

}
