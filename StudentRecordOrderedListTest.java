package Assignemnt2;

import java.util.Scanner;

public class StudentRecordOrderedListTest {

    public static int menu(Scanner s) {
        int action = 0;
        System.out.println("Please choose your action:");
        System.out.println("1: Insert");
        System.out.println("2: Remove");
        System.out.println("3: Include average");
        System.out.println("4: Print one student record");
        System.out.println("5: Print All");
        System.out.println("6: Exit");
        try {
            action = s.nextInt();
        } catch (Exception exception) {
            System.out.println("Wrong action");
            s.nextLine();
        }
        return action;
    }

    public static String getSurname(Scanner s) {
        System.out.println("Insert surname");
        return s.next();
    }

    public static String getName(Scanner s) {
        System.out.println("Insert name");
        return s.next();
    }

    public static int getStudentNo(Scanner s) {
        int id = 1;
        System.out.println("Insert studentNo");
        while (true) {
            try {
                id = s.nextInt();
                //if((StudentRecord)list.find(std).data == id){
                //    System.out.println("this ID already exists");
                // } else {
                break;
                //}
            } catch (Exception InputMismatchExemption) {
                System.out.println("Id has to be integer value");
                s.nextLine();
            }
        }
        return id;
    }

    public static float getAverage(Scanner s) {
        float average;
        System.out.println("Insert student average");
        while (true) {
            try {
                average = s.nextFloat();
                break;
            } catch (Exception InputMismatchExemption) {
                System.out.println("Average has to be float value");
                s.nextLine();
            }
        }
        return average;
    }


    public static void main(String[] args) {
        int action = 1;
        String surname, name;
        int studentNo;
        float average;
        StudentRecord studentRecord = new StudentRecord();
        StudentRecordOrderedList list = new StudentRecordOrderedList();
        Scanner s = new Scanner(System.in);
        while (action != 6) {
            action = menu(s);
            if (action == 1) {
                // StudentRecord
                surname = getSurname(s);
                name = getName(s);
                studentNo = getStudentNo(s);
                StudentRecord update = new StudentRecord(surname, name, studentNo, 0);
                if (list.find(update) == null) {
                    list.insert(update);
                }
            } else if (action == 2) {
                surname = getSurname(s);
                name = getName(s);
                StudentRecord toCheck = new StudentRecord(surname, name, 0, 0);
                list.remove(toCheck);
            } else if (action == 3) {
                surname = getSurname(s);
                name = getName(s);
                average = getAverage(s);

                StudentRecord toFind = new StudentRecord(surname, name, 0, 0);
                if (list.find(toFind) != null) {
                    StudentRecord toInsert = new StudentRecord(surname, name, 0, 0);
                    toInsert = (StudentRecord) (list.find(toFind)).data;
                    list.remove(toFind);
                    toInsert.averageMark = average;
                    list.insert(toInsert);
                } else {
                    System.out.println("No such Student Record");
                }
            } else if (action == 4) {
                surname = getSurname(s);
                name = getName(s);
                StudentRecord toFind = new StudentRecord(surname, name, 0, 0);
                StudentRecord existing = (StudentRecord) (list.find(toFind)).data;
                if (existing != null) {
                    System.out.println(existing);
                } else {
                    System.out.println("No such Student Record");
                }
            } else if (action == 5) {
                System.out.println(list);
            } else if(action == 6){
                System.out.println("goodbye");
            }

        }

    }

}

class StudentRecordOrderedList extends OrderedList {


    @Override
    protected int compare(Object obj1, Object obj2) {

        String string1 = ((StudentRecord) obj1).surname + " " + ((StudentRecord) obj1).name;
        String string2 = ((StudentRecord) obj2).surname + " " + ((StudentRecord) obj2).name;

        return string1.compareTo(string2);
    }
}

class StudentRecord {
    public String surname;
    public String name;
    public int studentNo;
    public float averageMark;

    public String toString() {
        return "\n" + surname + " " + name + " " + studentNo + " " + averageMark + "\n";
    }


    public StudentRecord(String mySurname, String myName, int myStudentNo, float myAverageMark) {
        surname = mySurname;
        name = myName;
        studentNo = myStudentNo;
        averageMark = myAverageMark;
    }

    public StudentRecord() {
        this("", "", 0, 0);
    }
}
