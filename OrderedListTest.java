package Assignemnt2;

import java.util.Scanner;

public class OrderedListTest {

    public int menu(Scanner s) {
        int action;
        System.out.println("Please choose your action:");
        System.out.println("1: Insert");
        System.out.println("2: Remove");
        System.out.println("3: PrintAll");
        System.out.println("4: Exit");

        action = s.nextInt();
        return action;
    }

    public static void main(String[] args) {
        int action = 0;
        OrderedListTest test = new OrderedListTest();
        IntegerOrderedList intList = null;
        StringOrderedList stringList = null;
        Scanner s = new Scanner(System.in);
        while (action != 4) {
            try {
                action = test.menu(s);
            } catch (Exception e) {
                System.out.println("Insert integer value");
                s.nextLine();
            }
            if (action >= 1 && action <= 4) {

                if (action == 1) {
                    System.out.println("Insert the value:");
                    if (s.hasNextInt()) {
                        if (intList == null) {
                            int data = s.nextInt();
                            intList = new IntegerOrderedList(data);
                        } else {
                            intList.insert(s.nextInt());
                        }

                    } else if (!s.hasNextInt()) {
                        if (stringList == null) {
                            String data = s.next();
                            stringList = new StringOrderedList(data);
                        } else {
                            stringList.insert(s.next());
                        }
                    }
                } else if (action == 2) {
                    System.out.println("Choose which list you would like to edit: (1) Integer (2) String");
                    try {
                        int data = s.nextInt();
                        if (data == 1) {
                            System.out.println("Type in the number you want to delete");
                            int input;
                            try {
                                input = s.nextInt();
                                intList.remove(input);
                            } catch (Exception e) {
                                System.out.println("Enter an integer");
                            }
                        } else if (data == 2) {
                            System.out.println("Type in the string you want to delete");
                            String input;
                            try {
                                input = s.next();
                                stringList.remove(input);
                            } catch (Exception e) {
                                System.out.println("Enter a string value");
                            }
                        } else {
                            System.out.println("Please enter value either 1 or 2");
                        }
                    } catch (Exception e) {
                        System.out.println("Action has to be integer");
                        s.nextLine();
                    }
                } else if (action == 3) {
                    try {
                        System.out.println("Integer List:");
                        System.out.println(intList.toString());
                    } catch (Exception e) {
                        System.out.println("Integer list is empty");
                    }
                    try {
                        System.out.println("String List:");
                        System.out.println(stringList.toString());
                    } catch (Exception e) {
                        System.out.println(("String list is empty"));
                    }

                } else {
                    System.out.println("Goodbye");
                    System.exit(0);
                }

            } else {
                System.out.println("Please input number 1-4");
            }

        }
    }
}

class StringOrderedList extends OrderedList {

    public StringOrderedList() {
        super();
    }

    public StringOrderedList(String data) {
        super();
        this.insert(data);
    }

    @Override
    protected int compare(Object obj1, Object obj2) {
        String str1 = String.valueOf(obj1.toString());
        String str2 = String.valueOf(obj2.toString());
        return str1.compareToIgnoreCase(str2);
    }
}

class IntegerOrderedList extends OrderedList {

    public IntegerOrderedList() {
        super();
    }

    public IntegerOrderedList(int data) {
        super();
        this.insert(data);
    }

    @Override
    protected int compare(Object obj1, Object obj2) {
        int int1 = Integer.parseInt(obj1.toString());
        int int2 = Integer.parseInt(obj2.toString());
        int result = Integer.compare(int1, int2); // initialising the variable
        return result;
    }
}