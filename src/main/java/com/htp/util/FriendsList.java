package com.htp.util;

import java.util.Random;
import java.util.UUID;

public class FriendsList {
    public static void main(String[] args) {
        TestUser user = generateUser(5);
        user.printFriends();
    }

    public static TestUser generateUser(int depth) {
        TestUser user = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        int friendsCount = new Random().nextInt(2) + 1;
        if (depth != 0) {
            for (int i = 0; i < friendsCount; i++) {
                user.addFriend(generateUser(depth - 1));
            }
        }

        return user;
    }
}
