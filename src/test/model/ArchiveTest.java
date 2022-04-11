package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArchiveTest {

    Archive arc;

    @BeforeEach
    void setup() { arc = new Archive(); }

    @Test
    void addCollectionEmptyTest() {
        Collection col = new Collection("First");
        arc.addCollection(col);
        List<Collection> l = arc.getArchive();
        assertEquals(l.size(),1);
    }

    @Test
    void addCollectionNonEmptyTest() {
        for (int i = 0; i < 3; i++) {
            Collection fill = new Collection("Fill "+i);
            arc.addCollection(fill);
        }
        Collection col = new Collection("Last");
        arc.addCollection(col);
        List<Collection> l = arc.getArchive();
        assertEquals(l.size(),4);
    }

    @Test
    void getCollectionByIndexTest() {
        for (int i = 0; i < 3; i++) {
            Collection fill = new Collection("Fill "+i);
            arc.addCollection(fill);
        }
        assertNotNull(arc.getCollectionByIndex(2));
    }

}
