package dsaTutorial3;

import java.util.Iterator;

/**
 * Created by bbr on 01.09.15.
 */

public class MyLinkedList<E> implements Iterable<E> {

    /**
     * The implementation of Iterable interface.
     * @return returns the new Iterator of MyLinkedList class.
     */

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Link currLink = first;

            @Override
            public boolean hasNext()
            {
                return currLink.next != null;
            }

            @Override
            public E next() {
                E result = (E)currLink.data;
                currLink = currLink.next;
                return result;
            }
        };
    }

    class Link<E> {
        Link next = null;
        E data    = null;
        public Link(E data) {
            this.data = data;
        }
    }

    private Link first = null;
    private int size = 0;

    public MyLinkedList() {
        first = null;
    }

    public int getSize() {
        return size;
    }

    public <E> E getFirst() {
        return (E)this.first.data;
    }

    public <E> E getLast() {
        if (!isEmpty()) {
            Link currentLink = first;
            while (currentLink != null) {

                if (currentLink.next == null) {
                    return (E)currentLink.data;
                }
                currentLink = currentLink.next;

            }
        }
        return null;
    }

    public boolean isEmpty() {
        return (this.first == null);
    }

    public void delete(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Wrong actual parameter!");
        }

        if (isEmpty() && index == 0)
            deleteFirst();

        Link currLink = first;
        Link prevLink = null;

        while (index-- != 0) {
            prevLink = currLink;
            currLink = currLink.next;
        }

        prevLink.next = currLink.next;
        size--;

    }

    public void update(E data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Wrong actual parameter!");
        }

        if (isEmpty() && index == 0)
            addFirst(data);

        Link currLink = first;

        while (index-- != 0) {
            currLink = currLink.next;
        }

        currLink.data = data;
    }

    public <E> E get(int index) {
        if (index == 0)
            return getFirst();

        if (index > 0 && index < size) {
            Link currLink = first;

            while (index-- != 0) {
                currLink = currLink.next;
            }
            return (E)currLink.data;

        }
        else {
            throw new IndexOutOfBoundsException("Wrong actual parameter!");
        }
    }

    public Link getLinkByIndex(int index) {
        if (index == 0)
            return first;

        if (index > 0 && index < size) {
            Link currLink = first;

            while (index-- != 0) {
                currLink = currLink.next;
            }
            return currLink;
        }
        else {
            throw new IndexOutOfBoundsException("Wrong actual parameter!");
        }
    }
    public void insert(E data, int index) {
        if (this.isEmpty() && index == 0)
            addFirst(data);
        else {
            Link newLink = new Link(data);
            Link currLink = first;

            if (!isEmpty() && !(index > size + 1)) {
                Link tempLink;

                while (index-- != 1) {
                    currLink = currLink.next;
                }

                tempLink = currLink.next;
                currLink.next = newLink;
                newLink.next = tempLink;
                size++;
            }
            else {
                throw new IndexOutOfBoundsException("Wrong actual parameter!");
            }
        }
    }

    public void addFirst(E data) {
        Link newLink = new Link(data);
        newLink.next = null;

        if (this.isEmpty()) {
            first = newLink;
            size++;
        }
        else {
            newLink.next = first;
            first = newLink;
            size++;
        }
    }

    public void addLast(E data) {
        Link newLink = new Link(data);
        newLink.next = null;
        Link tempLink = first;

        if (this.isEmpty()) {
            first = newLink;
            size++;
        }
        else {
            while (tempLink != null) {

                if (tempLink.next == null) {
                    tempLink.next = newLink;
                    size++;
                    return;
                }

                tempLink = tempLink.next;
            }
        }
    }

    /**
     * Compares current list with another one.
     * @param anotherList - the list which should be compared with current list.
     * @return - returns boolean result of this comparison.
     */

    public boolean equals(MyLinkedList anotherList) {
        boolean result = false;
        if (this.size != anotherList.size) {
            return result;
        }

        Link thisCurrentLink = this.first;
        Link anotherCurrentLink = anotherList.first;

        while (thisCurrentLink != null) {

            if (thisCurrentLink.data.hashCode() != anotherCurrentLink.data.hashCode()) {
                return result;
            }

            thisCurrentLink = thisCurrentLink.next;
            anotherCurrentLink = anotherCurrentLink.next;
        }

        result = true;
        return result;
    }

    public boolean searchElement(E element) {
        boolean result = false;

        if (!isEmpty()) {
            Link currentLink = first;

            while (currentLink != null) {

                if (currentLink.data.hashCode() == element.hashCode()) {
                    result = true;
                    return result;
                }

                currentLink = currentLink.next;
            }
        }
        return result;
    }

    public <E> E deleteFirst() {
        if (!isEmpty()) {
            Link currentLink = new Link(first.data);
            first.data = null;
            first = first.next;
            size--;
            return (E) currentLink.data;
        }

        return null;
    }

    public <E> E deleteLast() {
        if (!isEmpty()) {

            Link currentLink = first;
            Link previousLink = first;

            while (currentLink != null) {

                if (currentLink.next == null) {
                    Link resultLink = new Link(currentLink);
                    previousLink.next = null;
                    return (E)resultLink;
                }

                previousLink = currentLink;
                currentLink = currentLink.next;
            }
        }
        return null;
    }


    /**
     * Deletes one element if it exists.
     * @param data - Link with data equals to the @param should be deleted.
     */

    public void deleteLink(E data) {
        Link currentLink = this.first;
        Link previousLink = this.first;

        while (currentLink.next != null) {//|| currentLink.data != data) {
            previousLink = currentLink;
            currentLink = currentLink.next;

            if (currentLink.data == data) {
                previousLink.next = currentLink.next;
                size--;
                return;
            }

            currentLink = currentLink.next;
        }
    }

    public String printList() {
        Link tempLink = this.first;
        String result = "";
        int i = 1;

        while (tempLink != null) {
            //result += " №" + i + ": " + tempLink.data.toString();
            result += tempLink.data.toString() + " ";
            tempLink = tempLink.next;
            i++;
        }
        return result;
    }

    /**
     * Merges this list with another one.
     * @param anotherList - the list which should be merged with current list.
     * @return returns a sum of lists or null if lists are empty.
     */

    public MyLinkedList catList(MyLinkedList anotherList) {
        Link thisCurrent = first;
        Link anotherCurrent = anotherList.first;
        MyLinkedList resultList;

        if (!this.isEmpty() && !anotherList.isEmpty()) {

            while (thisCurrent != null) {

                if (thisCurrent.next == null) {
                    thisCurrent.next = anotherCurrent;
                    resultList = this;
                    resultList.size = this.size + anotherList.size;
                    return resultList;
                }
                thisCurrent = thisCurrent.next;

            }
        }
        return null;
    }
}