package org.esgi;

import java.util.*;

public class Friend {
    private Collection<Friend> friends;
    private String email;

    public Friend(String email) {
        this.email = email;
        this.friends = new ArrayList<Friend>();
    }

    public String getEmail() {
        return email;
    }

    public Collection<Friend> getFriends() {
        return friends;
    }

    public void addFriendship(Friend friend) {
        friends.add(friend);
        friend.getFriends().add(this);
    }

    public boolean canBeConnected(Friend friend) {
//        if (this.friends.contains(friend)) {
//            return true;
//        }
//        for (Friend f : this.friends) {
//            return f.canBeConnected(friend);
//        }
//        return false;
//
        Set<Friend> alreadyVisited = new HashSet<>();
        Queue<Friend> queue = new ArrayDeque<>();
        queue.add(this);

        Friend currentFriend;

        while (!queue.isEmpty()) {
            currentFriend = queue.remove();

            if (currentFriend.equals(friend)) {
                return true;
            } else {
                alreadyVisited.add(currentFriend);
                queue.addAll(currentFriend.getFriends());
                queue.removeAll(alreadyVisited);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Friend a = new Friend("A");
        Friend b = new Friend("B");
        Friend c = new Friend("C");

        a.addFriendship(b);
        b.addFriendship(c);

        System.out.println(a.canBeConnected(c));
    }
}