package dsaTutorial3;

/**
 * Created by bbr on 08.09.15.
 */

import java.io.*;
import java.util.Iterator;

/**
 * This is a template code for Assignment #1
 * for DSA course: Master
 *
 * @author Stanislav I. Protasov
 * @company Innopolis University
 */

public class MySuperBrowserEngineM {

    /**
     * TODO implement class representing point
     * This is a class that represents a real 2D point
     */
    public static class Point2D {
        private double x = 0.0;
        private double y = 0.0;

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * This is a class that represents rectangle
     * in Cartesian coordinates
     */
    public static class Rectangle {
        private String tag = "";
        private Point2D lowerLeft;
        private Point2D upperTop;

        /**
         * TODO implement. Tag stores a word attributed to the rectangle
         */

        public String getTag() {
            return this.tag;
        }

        /**
         * TODO implement constructor for rectangle
         *
         * @param lowerLeft lower left angle of rectangle
         * @param upperTop  upper top angle or rectangle
         * @param tag       word that is represented by rectangle
         */

        public Rectangle(Point2D lowerLeft, Point2D upperTop, String tag) {
            this.lowerLeft = lowerLeft;
            this.upperTop = upperTop;
            this.tag = tag;
        }

        /**
         * TODO implement
         * Methods returns 4 pairs of points. Each pair represents an edge or rectangle
         *
         * @return returns Point2D[4][2] array
         */

        public Point2D[][] getEdges() {
            Point2D[][] result = new Point2D[4][2];

            result[0][0] = lowerLeft;
            result[0][1] = new Point2D(upperTop.getX(), lowerLeft.getY());

            result[1][0] = new Point2D(upperTop.getX(), lowerLeft.getY());
            result[1][1] = upperTop;

            result[2][0] = upperTop;
            result[2][1] = new Point2D(lowerLeft.getX(), upperTop.getY());

            result[3][0] = new Point2D(lowerLeft.getX(), upperTop.getY());
            result[3][1] = lowerLeft;

            return result;
        }
    }

    /**
     * TODO implement at least these methods
     * Generic implementation MyList<Type> is graded.
     * 'implements Iterable' will give you bonus scores.
     * Additional method implementation can give you bonus scores.
     */

    public static class MyList<E> implements Iterable<E> {

        /**
         * The implementation of Iterable interface.
         *
         * @return returns the new Iterator of MyLinkedList class.
         */

        @Override
        public Iterator<E> iterator() {
            return new Iterator<E>() {

                private Link currLink = first;

                @Override
                public boolean hasNext() {
                    return currLink.next != null;
                }

                @Override
                public E next() {
                    E result = (E) currLink.data;
                    currLink = currLink.next;
                    return result;
                }
            };
        }

        class Link<E> {
            Link next = null;
            E data = null;

            public Link(E data) {
                this.data = data;
            }
        }

        private Link first = null;
        private int size = 0;

        public MyList() {
            first = null;
        }

        public int getSize() {
            return size;
        }

        @SuppressWarnings("unchecked")
        public <E> E getFirst() {
            return (E) this.first.data;
        }

        @SuppressWarnings("unchecked")
        public <E> E getLast() {
            if (!isEmpty()) {
                Link currentLink = first;
                while (currentLink != null) {

                    if (currentLink.next == null) {
                        return (E) currentLink.data;
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


        @SuppressWarnings("unchecked")
        public <E> E get(int index) {
            if (index == 0)
                return getFirst();

            if (index > 0 && index < size) {
                Link currLink = first;

                while (index-- != 0) {
                    currLink = currLink.next;
                }
                return (E) currLink.data;

            } else {
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
            } else {
                throw new IndexOutOfBoundsException("Wrong actual parameter!");
            }
        }

        @SuppressWarnings("unchecked")
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
                } else {
                    throw new IndexOutOfBoundsException("Wrong actual parameter!");
                }
            }
        }

        @SuppressWarnings("unchecked")
        public void addFirst(E data) {
            Link newLink = new Link(data);
            newLink.next = null;

            if (this.isEmpty()) {
                first = newLink;
                size++;
            } else {
                newLink.next = first;
                first = newLink;
                size++;
            }
        }

        @SuppressWarnings("unchecked")
        public void addLast(E data) {
            Link newLink = new Link(data);
            newLink.next = null;
            Link tempLink = first;

            if (this.isEmpty()) {
                first = newLink;
                size++;
            } else {
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
         *
         * @param anotherList - the list which should be compared with current list.
         * @return - returns boolean result of this comparison.
         */

        public boolean equals(MyList anotherList) {
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

        @SuppressWarnings("unchecked")
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

        @SuppressWarnings("unchecked")
        public <E> E deleteLast() {
            if (!isEmpty()) {

                Link currentLink = first;
                Link previousLink = first;

                while (currentLink != null) {

                    if (currentLink.next == null) {
                        Link resultLink = new Link(currentLink);
                        previousLink.next = null;
                        return (E) resultLink;
                    }

                    previousLink = currentLink;
                    currentLink = currentLink.next;
                }
            }
            return null;
        }


        /**
         * Deletes one element if it exists.
         *
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
         *
         * @param anotherList - the list which should be merged with current list.
         * @return returns a sum of lists or null if lists are empty.
         */

        public MyList catList(MyList anotherList) {
            Link thisCurrent = first;
            Link anotherCurrent = anotherList.first;
            MyList resultList;

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

            /*public void add(int i, Object o) { throw new Error("not implemented"); }
            public void addLast(Object o) { throw new Error("not implemented"); }
            public Object get(int i) { throw new Error("not implemented"); }
            public int size() { throw new Error("not implemented"); }
            public boolean isEmpty() { throw new Error("not implemented"); }*/

    /**
     * TODO implement at least these methods
     * NB Your implementation should extent MyList implementation.
     * Generic implementation is graded.
     */

    public static class MyStack<E> {

        private MyList<E> elements = null;
        private int size = 0;

        @SuppressWarnings("unchecked")
        public MyStack() {
            this.elements = new MyList();
            this.size = this.elements.getSize();
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void push(E element) {
            elements.addFirst(element);
            size++;
        }

        @SuppressWarnings("unchecked")
        public <E> E pop() {
            size--;
            return (E) elements.deleteFirst();
        }

    }

    /**
     * TODO implement
     * Intersection method checks if section ab intersects with section cd.
     *
     * @param a section 1 point 1
     * @param b section 1 point 2
     * @param c section 2 point 1
     * @param d section 2 point 2
     * @return true if sections intersect
     */
    public static boolean intersects(MySuperBrowserEngineM.Point2D a, MySuperBrowserEngineM.Point2D b, MySuperBrowserEngineM.Point2D c, MySuperBrowserEngineM.Point2D d) {
        // We describe the section AB as A+(B-A)*u and CD as C+(D-C)*v
        // then we solve A + (B-A)*u = C + (D-C)*v
        // let's use Kramer's rule to solve the task (Ax = B) where x = (u, v)^T
        // build a matrix for the equation

        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();

        // calculate determinant
        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];

        // substitute columns and calculate determinants
        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());

        // calculate the solution
        // even if det0 == 0 (they are parallel) this will return NaN and comparison will fail -> false
        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    /**
     * TODO implement a method that gets a stack that contains all rectangles that are affected by the line
     *
     * @param rects      rectangles to check
     * @param lineStart  starting point of the line
     * @param lineFinish end point of the line
     * @return
     */
    public static MyStack getAffected(MyList<Rectangle> rects, Point2D lineStart, Point2D lineFinish) {
            /*for (int i = 0; i < rects.getSize(); i++) {
                new Rectangle(new Point2D(2,2), new Point2D(2,4), "qwe").getEdges();
                rects.get(i)
            }*/
        int count = 0;
        MyStack<Rectangle> result = new MyStack<>();
        for (Rectangle rectangle : rects) {
            Point2D[][] edges = rectangle.getEdges();
            if (intersects(edges[0][0], edges[0][1], lineStart, lineFinish))
                count++;
            if (intersects(edges[1][0], edges[1][1], lineStart, lineFinish))
                count++;
            if (intersects(edges[2][0], edges[2][1], lineStart, lineFinish))
                count++;
            if (intersects(edges[3][0], edges[3][1], lineStart, lineFinish))
                count++;
            if (count > 0)
                result.push(rectangle);
            count = 0;
        }
        return result;
    }

    /**
     * Method takes text and calculates rectangles that represent words
     *
     * @param text          input string
     * @param oneLetterSize size of one letter in monospace font
     * @param startCorner   corner where text starts
     * @return list of rectangles associated with words in text
     */
    public static MyList getRectangles(String text, Point2D oneLetterSize, Point2D startCorner) {
        MyList list = new MyList();
        String[] words = text.split(" ");
        int position = 0;
        for (String word : words) {
            if (word.length() > 0) {
                Point2D ll = new Point2D(
                        startCorner.getX() + position * oneLetterSize.getX(),
                        startCorner.getY());
                Point2D ut = new Point2D(
                        ll.getX() + word.length() * oneLetterSize.getX(),
                        startCorner.getY() + oneLetterSize.getY());
                list.addLast(new Rectangle(ll, ut, word));
            }
            position += word.length() + 1;
        }
        return list;
    }


    public static void main(String[] args) throws Exception {
        String inputData = "";
        String inputCoords = "";
        Point2D textCorner, letterSize, lineStart, lineEnd;

        textCorner = letterSize = lineEnd = lineStart = new Point2D(0, 0);
        // TODO read input data here
        File inputFile = new File("browser.in");
        File outputFile = new File("browser.out");

        outputFile.createNewFile();
        outputFile.mkdirs();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile)));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile)));

        inputData = reader.readLine();
        inputCoords = reader.readLine();
        String[] temp = inputCoords.split(" ");
        Double[] tempCoords = new Double[8];

        for (int i = 0; i < temp.length; i++) {
            try {
                if (!temp[i].equals(" "))
                    tempCoords[i] = Double.parseDouble(temp[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        textCorner = new Point2D(tempCoords[0], tempCoords[1]);
        letterSize = new Point2D(tempCoords[2], tempCoords[3]);
        lineStart = new Point2D(tempCoords[4], tempCoords[5]);
        lineEnd = new Point2D(tempCoords[6], tempCoords[7]);

        MyList list = getRectangles(inputData, letterSize, textCorner);
        MyStack stack = getAffected(list, lineStart, lineEnd);
        while (!stack.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append(((Rectangle) stack.pop()).getTag() + " ");
            String word = builder.toString();
            writer.write(word);
            writer.flush();
                /*String word = "";
                word += " " +((Rectangle)stack.pop()).getTag();
                // TODO write output data */
            System.out.print(word);

        }
    }


}


/**
 * guid d1e43343-cbd6-426f-adeb-a17ebe335cee
 */

