package tr.com.orties.curse.comparators;

import java.util.Comparator;

import tr.com.orties.curse.model.Message;

/**
 * Created by Yunus on 01-Apr-16.
 */
public class MessageComparator implements Comparator<Message> {

    @Override
    public int compare(Message m1, Message m2) {
        return m1.getId() < m2.getId() ? -1 : m1.getId() == m2.getId() ? 0 : 1;
    }
}
