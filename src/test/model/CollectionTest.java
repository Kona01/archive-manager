package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionTest {

    Collection col;

    @BeforeEach
    void setup() {
        col = new Collection("C");
    }

    @Test
    void getNameTest() {
        assertEquals(col.getName(),"C");
    }

    @Test
    void addPerformanceEmptyTest() {
        Performance p = new Performance("P1",2021,"CPSC 210 Presentation");
        col.addPerformance(p);
        List<Performance> l = col.getList();
        assertEquals(l.size(), 1);
    }

    @Test
    void addPerformanceNonEmptyTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2000, "New Century Tour");
            col.addPerformance(fill);
        }
        Performance p = new Performance("P1",2021,"CPSC 210 Presentation");
        col.addPerformance(p);
        List<Performance> l = col.getList();
        assertEquals(l.size(), 5);
    }

    @Test
    void searchByYearEmptyTest() {
        assertEquals(col.searchByYear(2006), new ArrayList<Performance>());
    }

    @Test
    void searchByYearNoneTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2010, "Vancouver Olympics");
            col.addPerformance(fill);
        }
        assertEquals(col.searchByYear(2001), new ArrayList<Performance>());
    }

    @Test
    void searchByYearOneTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2000+i, "Yearly Thing");
            col.addPerformance(fill);
        }
        List<Performance> result = col.searchByYear(2002);
        assertEquals(result.size(),1);
    }

    @Test
    void searchByYearManyTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2000+i, "Yearly Thing");
            Performance match = new Performance(String.valueOf(i), 1999, "Unknown");
            col.addPerformance(fill);
            col.addPerformance(match);
        }
        List<Performance> result = col.searchByYear(1999);
        assertEquals(result.size(), 4);
    }

    @Test
    void searchByYearAllTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2020, "Pandemic Relief");
            col.addPerformance(fill);
        }
        List<Performance> result = col.searchByYear(2020);
        assertEquals(result.size(), 4);
    }

    @Test
    void searchByNameEmptyTest() {
        assertEquals(col.searchByName("Name"), new ArrayList<Performance>());
    }

    @Test
    void searchByNameNoneTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2010, "Vancouver Olympics");
            col.addPerformance(fill);
        }
        assertEquals(col.searchByName("Quatchi's Song"), new ArrayList<Performance>());
    }

    @Test
    void searchByNameOneTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2000+i, "Yearly Thing");
            col.addPerformance(fill);
        }
        List<Performance> result = col.searchByName("2");
        assertEquals(result.size(),1);
    }

    @Test
    void searchByNameManyTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2000+i, "Yearly Thing");
            Performance match = new Performance("Jingle Bells", 2000+i, "Christmas Eve Concert");
            col.addPerformance(fill);
            col.addPerformance(match);
        }
        List<Performance> result = col.searchByName("Jingle Bells");
        assertEquals(result.size(), 4);
    }

    @Test
    void searchByNameAllTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Popular Song", 2000+i, "Popular Artist Concert");
            col.addPerformance(fill);
        }
        List<Performance> result = col.searchByName("Popular Song");
        assertEquals(result.size(), 4);
    }

    @Test
    void searchByEventEmptyTest() {
        assertEquals(col.searchByEvent("Event Name"), new ArrayList<Performance>());
    }

    @Test
    void searchByEventNoneTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Traditional Song", 2000+i, "New Year's Eve");
            col.addPerformance(fill);
        }
        assertEquals(col.searchByEvent("Christmas Eve"), new ArrayList<Performance>());
    }

    @Test
    void searchByEventOneTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Generic Name", 2000+i, "Annual Event "+i);
            col.addPerformance(fill);
        }
        List<Performance> result = col.searchByEvent("Annual Event 2");
        assertEquals(result.size(),1);
    }

    @Test
    void searchByEventManyTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance(String.valueOf(i), 2000+i, "Yearly Thing");
            Performance match = new Performance("Christmas song "+i, 2000+i, "Christmas Eve Concert");
            col.addPerformance(fill);
            col.addPerformance(match);
        }
        List<Performance> result = col.searchByEvent("Christmas Eve Concert");
        assertEquals(result.size(), 4);
    }

    @Test
    void searchByEventAllTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Popular Song", 2000+i, "Popular Artist Concert");
            col.addPerformance(fill);
        }
        List<Performance> result = col.searchByEvent("Popular Artist Concert");
        assertEquals(result.size(), 4);
    }

    @Test
    void filterByYearEmptyTest() {
        assertEquals(col.filterByYear(2000,2009), new ArrayList<Performance>());
    }

    @Test
    void filterByYearNoneBothTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Song Name", 2000+i, "Some Event");
            col.addPerformance(fill);
        }
        assertEquals(col.filterByYear(1990,1999), new ArrayList<Performance>());
    }

    @Test
    void filterByYearNoneMinTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Song Name", 2000+i, "Some Event");
            col.addPerformance(fill);
        }
        assertEquals(col.filterByYear(2010,-1), new ArrayList<Performance>());
    }

    @Test
    void filterByYearNoneMaxTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Song Name", 2000+i, "Some Event");
            col.addPerformance(fill);
        }
        assertEquals(col.filterByYear(-1,1999), new ArrayList<Performance>());
    }

    @Test
    void filterByYearOneBothTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Song Name", 2000+i, "Some Event");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(1999,2000);
        assertEquals(result.size(), 1);
    }

    @Test
    void filterByYearOneMinTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Song Name", 2000+i, "Some Event");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(2003,-1);
        assertEquals(result.size(), 1);
    }

    @Test
    void filterByYearOneMaxTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Song Name", 2000+i, "Some Event");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(-1,2000);
        assertEquals(result.size(), 1);
    }

    @Test
    void filterByYearOneBothSameTest() {
        for (int i = 0; i < 4; i++) {
            Performance fill = new Performance("Song Name", 2000+i, "Some Event");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(2002,2002);
        assertEquals(result.size(), 1);
    }

    @Test
    void filterByYearManyBothTest() {
        for (int i = 0; i < 8; i++) {
            Performance fill = new Performance("Track "+i, 2000+i, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(2003,2006);
        assertEquals(result.size(), 4);
    }

    @Test
    void filterByYearManyMaxTest() {
        for (int i = 0; i < 8; i++) {
            Performance fill = new Performance("Track "+i, 2000+i, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(-1,2003);
        assertEquals(result.size(), 4);
    }

    @Test
    void filterByYearManyMinTest() {
        for (int i = 0; i < 8; i++) {
            Performance fill = new Performance("Track "+i, 2000+i, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(2005,-1);
        assertEquals(result.size(), 3);
    }

    @Test
    void filterByYearManyBothSameTest() {
        for (int i = 0; i < 5; i++) {
            Performance fill = new Performance("Track "+i, 2000+i, "Yearly Release");
            Performance match = new Performance("Set "+i, 2019, "Busy Year");
            col.addPerformance(fill);
            col.addPerformance(match);
        }
        List<Performance> result = col.filterByYear(2019,2019);
        assertEquals(result.size(), 5);
    }

    @Test
    void filterByYearAllBothTest() {
        for (int i = 0; i < 6; i++) {
            Performance fill = new Performance("Track "+i, 2000+i, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(1990,2020);
        assertEquals(result.size(), 6);
    }

    @Test
    void filterByYearAllMaxTest() {
        for (int i = 0; i < 6; i++) {
            Performance fill = new Performance("Track "+i, 2000+i, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(-1,2020);
        assertEquals(result.size(), 6);
    }

    @Test
    void filterByYearAllMinTest() {
        for (int i = 0; i < 6; i++) {
            Performance fill = new Performance("Track "+i, 2000+i, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(1990,-1);
        assertEquals(result.size(), 6);
    }

    @Test
    void filterByYearAllBothSameTest() {
        for (int i = 0; i < 3; i++) {
            Performance fill = new Performance("Track "+i, 2000, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(2000,2000);
        assertEquals(result.size(), 3);
    }

    @Test
    void filterByYearNeitherTest() {
        for (int i = 0; i < 3; i++) {
            Performance fill = new Performance("Track "+i, 2000, "Yearly Release");
            col.addPerformance(fill);
        }
        List<Performance> result = col.filterByYear(-1,-1);
        assertEquals(result, col.getList());
    }

}
