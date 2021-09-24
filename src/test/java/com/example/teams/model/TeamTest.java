package com.example.teams.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    @Test
    public void setGetName() {
        Team t = new Team();
        t.setName("West Ham United");
        assertEquals("West Ham United",t.getName());
        t.setName("Chelsea FC");
        assertEquals("Chelsea FC",t.getName());
    }

    @Test
    public void setGetManager() {
        Team t = new Team();
        t.setManager("Big Ron");
        assertEquals("Big Ron",t.getManager());
        t.setManager("Big Sam");
        assertEquals("Big Sam",t.getManager());
    }

    @Test
    public void isEqual() {
        Team t1 = new Team();
        Team t2 = new Team();
        t1.setName("West Ham United");
        t2.setName("West Ham United");
        assertTrue(t1.equals(t2));
    }

    @Test
    public void notEqual() {
        Team t1 = new Team();
        Team t2 = new Team();
        t1.setName("West Ham United");
        t2.setName("Doncaster United");
        assertFalse(t1.equals(t2));
    }

    @Test
    public void hascodeEqual() {
        Team t1 = new Team();
        Team t2 = new Team();
        t1.setName("West Ham United");
        t2.setName("West Ham United");
        assertEquals(t1.hashCode(),(t2.hashCode()));
    }

    @Test
    public void hashcodeNotEqual() {
        Team t1 = new Team();
        Team t2 = new Team();
        t1.setName("West Ham United");
        t2.setName("Doncaster United");
        assertNotEquals(t1.hashCode(),(t2.hashCode()));
    }

}
