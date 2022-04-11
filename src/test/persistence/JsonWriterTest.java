package persistence;

import model.Archive;
import model.Collection;
import model.Performance;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from this repo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Archive ar = new Archive();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyArchive() {
        try {
            Archive ar = new Archive();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyArchive.json");
            writer.open();
            writer.write(ar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyArchive.json");
            ar = reader.read();
            List<Collection> collections = ar.getArchive();
            assertEquals(0,collections.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralArchive() {
        try {
            Archive ar = new Archive();
            Collection c1 = new Collection("Pop/Rock Concerts");
            c1.addPerformance(new Performance("Radioactive",2015,"Imagine Dragons Concert"));
            c1.addPerformance(new Performance("Breathing Underwater - Metric",2015,"Imagine Dragons Concert"));
            ar.addCollection(c1);
            Collection c2 = new Collection("Country Concerts");
            c2.addPerformance(new Performance("Flatliner",2017,"Dierks Bentley Concert"));
            ar.addCollection(c2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralArchive.json");
            writer.open();
            writer.write(ar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralArchive.json");
            ar = reader.read();
            List<Collection> collections = ar.getArchive();
            assertEquals(2,collections.size());

            c1 = collections.get(0);
            assertEquals("Pop/Rock Concerts",c1.getName());
            List<Performance> performances = c1.getList();
            assertEquals(2,performances.size());

            Performance p1 = performances.get(0);
            Performance p2 = performances.get(1);
            checkPerformance("Radioactive",2015,"Imagine Dragons Concert",p1);
            checkPerformance("Breathing Underwater - Metric",2015,"Imagine Dragons Concert",p2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
