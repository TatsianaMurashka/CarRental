package com.htp.util;

import java.util.ArrayList;
import java.util.List;

public class TestUser {

    private Long id;
    private String name;
    private String surname;
    private final int MAX_DEPTH = 5;
    private List<TestUser> friends;

    public TestUser(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.friends = new ArrayList<>();
    }

    public void addFriend(TestUser friend) {
        this.friends.add(friend);
    }

    public void printFriends() {
        printFriends(0);
    }

    @Override
    public String toString() {
        return "{" + "name='" + name + '\'' + '}';
    }

    private void printFriends(int currentDepth) {
        if (currentDepth >= MAX_DEPTH) {
            return;
        }
        String offset = "";
        for (int i = 0; i < currentDepth * 2; i++) {
            offset += ' ';
        }
        System.out.println(offset + this.toString());
        for (TestUser friend : friends) {
            friend.printFriends(currentDepth + 1);
        }
    }

}
