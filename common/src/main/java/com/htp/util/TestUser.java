package com.htp.util;

import java.util.ArrayList;
import java.util.List;

public class TestUser {

    private Long id;
    private String name;
    private String surname;
    public final int MAX_DEPTH = 5;
    private List<TestUser> friends;

    private static int lastFriendsCount = 0;

    public List<TestUser> getFriends() {
        return friends;
    }

    public int getLastFriendsCount() {
        return lastFriendsCount;
    }

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
        lastFriendsCount = 0;
        printFriends(0);
    }

    private void printFriends(int currentDepth) {
        if (currentDepth >= MAX_DEPTH) {
            return;
        }
        lastFriendsCount++;
        String offset = "";
        for (int i = 0; i < currentDepth * 2; i++) {
            offset += ' ';
        }
        System.out.println(offset + this.toString());
        for (TestUser friend : friends) {
            friend.printFriends(currentDepth + 1);
        }
    }

    @Override
    public String toString() {
        return "{" + "name='" + name + '\'' + '}';
    }

}
