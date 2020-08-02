import com.htp.util.FriendsList;
import com.htp.util.TestUser;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class FriendsListTest {

    @Test
    public void testFriendsCount() {
        TestUser headUser = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        TestUser childUser0 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        TestUser childUser1 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        headUser.addFriend(childUser0);
        headUser.addFriend(childUser1);
        TestUser grandChildUser01 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        TestUser grandChildUser02 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        TestUser grandChildUser03 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        childUser0.addFriend(grandChildUser01);
        childUser0.addFriend(grandChildUser02);
        childUser0.addFriend(grandChildUser03);
        TestUser grandChildUser11 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        TestUser grandChildUser12 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        TestUser grandChildUser13 = new TestUser(new Random().nextLong(), UUID.randomUUID().toString(), UUID.randomUUID().toString());
        childUser1.addFriend(grandChildUser11);
        childUser1.addFriend(grandChildUser12);
        childUser1.addFriend(grandChildUser13);

        headUser.printFriends();

        assertEquals(headUser.getLastFriendsCount(), 9);
    }

    @Test
    public void testFriendsCountWithUserGenerating() {
        FriendsList friendsList = new FriendsList();
        TestUser testUser = friendsList.generateUser(3);
        testUser.printFriends();
        assertEquals(testUser.getLastFriendsCount(), countFriends(testUser, testUser.MAX_DEPTH));
    }

    private int countFriends(TestUser user, int maxDepth) {
        if (maxDepth <= 0)
            return 0;
        int count = 1;
        for (TestUser friend : user.getFriends()) {
            count += countFriends(friend, maxDepth - 1);
        }
        return count;
    }
}
