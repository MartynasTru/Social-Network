package Assignemnt2;



abstract class OrderedList extends List {
    ListNode previous;
    ListNode current;

    protected abstract int compare(Object obj1, Object obj2);

    public ListNode find(Object data) {
        current = firstNode;
        if (current == null) {
            return null;
        }
        if (current.next == null) { // 1 element list
            if (compare(current.data, data) == 0) {
                return current;
            } else {
                return null;
            }
        }
        while (current != null) {
            if (compare(current.data, data) < 0) {
                current = current.next;
            } else if (compare(current.data, data) == 0){
               return current;
            } else {
                return null;
            }
        }

        return null;
    }

    public boolean insert(Object newData) {
        ListNode newNode;
        if (find(newData) != null) {
            System.out.println("This number already exists");
            return false;
        }
        if (firstNode == null) {
            firstNode = lastNode = new ListNode(newData, null);
            return true;
        } else {
            current = firstNode;
            previous = null;

            if (compare(newData, current.data) < 0) { // current < newData
                if (previous == null) {
                    ListNode newFirstNode = new ListNode(newData, firstNode);
                    firstNode = newFirstNode;
                    return true;
                } else {
                    newNode = new ListNode(newData, current);
                    previous.next = newNode;
                }
            }

            while (current != null) {
                if (compare(newData, current.data) > 0) {
                    if (current.next == null) {
                        ListNode newLastNode = new ListNode(newData, null);
                        lastNode.next = newLastNode;
                        lastNode = newLastNode;
                        return true;
                    }
                } else {
                    if (previous != null) {
                        previous.next = new ListNode(newData, current);
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
        }

        return true;
    }

    public ListNode remove(Object data) {
        if (data == null) {
            return lastNode;
        }
        if (firstNode == null) {
            return null;
        } else if (firstNode.next == null) {
            if (compare(firstNode.data, data) == 0) {
                firstNode = lastNode = null;
                return null;
            }
        } else {
            current = firstNode;
            previous = null;

            while (current != null) {
                if (compare(data, current.data) > 0) {
                    previous = current;
                    current = current.next;
                } else if (compare(data, current.data) == 0) {
                    System.out.println("0");
                    if (previous == null) {
                        firstNode = current.next;
                        return current;
                    } else {
                        previous.next = current.next;
                        return current;
                    }
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}

class List{

    protected ListNode firstNode; // the first node

    protected ListNode lastNode; // the last node

    protected String name; // a string name

    public void insertAtFront (Object newData) { // insert Object at front

        if (firstNode == null) // empty list
            firstNode = lastNode = new ListNode(newData, null);

        else {
            ListNode newFirstNode = new ListNode(newData, firstNode);
            firstNode = newFirstNode;
        }
    }

    public void insertAtBack (Object newData) { // insert Object at back

        if (firstNode == null) { // empty list
            firstNode = lastNode = new ListNode(newData, null);
        }

        else {
            ListNode newLastNode = new ListNode(newData, null);
            lastNode.next = newLastNode;
            lastNode = newLastNode;
        }
    }

    public Object removeFromFront () { // remove Object from front

        if (firstNode == null){ // empty list
            return null;
        }

        Object removedData = firstNode.data;

        if (firstNode == lastNode) { // only one list node
            firstNode = lastNode = null;
        }

        else {
            firstNode = firstNode.next;
        }

        return removedData;
    }

    public Object removeFromBack() { // remove Object from front

        if (lastNode == null){ // empty list
            return null;
        }

        Object removedData = lastNode.data;

        if (firstNode == lastNode) { // only one list node
            firstNode = lastNode = null;
        }

        else {
            ListNode current = firstNode;
            try {
                while (current.next != lastNode){
                    current = current.next;
                    lastNode = current;
                    current.next = null;
                }
            } catch (Exception e){
                ;
            }

        }

        return removedData;
    }

    // getFirst allows one to get a “handle” to navigate through the list in a loop
    public ListNode getFirst () {
        return firstNode;
    }

    @Override
    public String toString () { // print list content to string
        String output = new String();
        ListNode current = firstNode;

        output = (name + ":");
        while (current != null) {
            // we are implicitly calling the data object toString method
            output += (" " + current.data);
            current = current.next;
        }

        return output;
    }

    // constructors
    public List (String listName) {
        firstNode = lastNode = null;
        name = listName;
    }

    public List () {
        this(new String("list: "));
    } // end class List
}




class ListNode {

    // public instance variables as there is nothing to “protect”
    public Object data; // the contained data object
    public ListNode next; // the self-reference

    // constructors
    public ListNode (Object newData, ListNode newNext) {
        data = newData;
        next = newNext;
    }

    public ListNode () {
        data = null;
        next = null;
    }
} // end class ListNode


class ListTest extends  List {
    public static void main (String[] args) {
        List list = new List();
        list.insertAtBack(new Integer(2));
        list.insertAtBack(new Integer(-1));
        list.insertAtBack(new Integer(9));
        list.insertAtBack(new Integer(5));
        System.out.println(list); // should print “List: 2 -1 9 5”
        Object removedData = list.removeFromFront();
        System.out.println("removed data is:" + removedData);

        // should print 2
        removedData = list.removeFromBack();
        // we know removedData is of class Integer so
        // we “downcast” a superclass (Object) to a subclass (Integer)
        Integer i = (Integer) removedData;
        System.out.println("removed data is: " + i);
        // should print 5
        System.out.println(list);
        // should print “List: -1 9”
    }
} // end class ListTest



