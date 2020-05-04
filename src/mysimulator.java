//Cheuk Hang Ku
//Student ID: 3291914
//COMP 2240-A3
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class mysimulator {
    public static ArrayList<Process> pL = new ArrayList<>();
    public static ArrayList<Process> pC = new ArrayList<>();
    public static int F, Q, num;
    public static void main(String[] args) throws FileNotFoundException {
        //Read input
        String input = Arrays.toString(args);
        String t = input.replaceAll(",", "");
        t = t.replace("[", "");
        t = t.replace("]", "");
        String[] str = t.split(" ");
        F = Integer.parseInt(str[0]);
        Q = Integer.parseInt(str[1]);
        num = args.length - 2;
        for (int i = 0; i < num; i++) {
            FileReader file = null;
            BufferedReader bufferedReader = null;
            Queue<Integer> pageQueue = new LinkedList<Integer>();
            //load data from file
            try {
                //load Process data
                FileReader data = new FileReader(args[i + 2]);
                Scanner dataIn = new Scanner(data);
                String dataLine = dataIn.nextLine();
                //read next line
                while (dataIn.hasNextInt()) {
                    int pageNumber = Integer.parseInt(dataIn.next());
                    // put data into pages list
                    pageQueue.offer(pageNumber);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Process process = new Process(i + 1, args[i + 2], pageQueue);
            pL.add(process);
            pC.add(process);
        }
        for (int loop = 0; loop < 2; loop++) {
            //LRU
            if (loop == 0) {
                F /= pL.size();
                LRU[] memLRU = new LRU[pL.size()];
                for (int i = 0; i < pL.size(); i++) {
                    memLRU[i] = new LRU(F);
                }
                RRL lru = new RRL(pL, Q, memLRU);
                lru.run();
            }
            //Clock
            else {
                F /= pC.size();
                Clock[] memClock = new Clock[pC.size()];

                for (int i = 0; i < pC.size(); i++) {
                    memClock[i] = new Clock(F);
                }
                RRC clock = new RRC(pC, Q, memClock);
                clock.run();
            }
        }
    }
//        //Read file
//        File file1 = new File(f1);
//        File file2 = new File(f2);
//        File file3 = new File(f3);
//        //create scanner
//        Scanner scan1 = new Scanner(file1);
//        Scanner scan2 = new Scanner(file2);
//        Scanner scan3 = new Scanner(file3);
//        String in1 = "";
//        String in2 = "";
//        String in3 = "";
//        int num = 0;
//        for (int loop = 0; loop < 2; loop++) {
//            //Read the file
//            if (scan1.hasNextLine()) {
//                in1 = scan1.next();
//                //stop reading when "end" occurs
//                while (in1.compareToIgnoreCase("end") != 0) {
//                    //start reading
//                    //when it's begin skip the line
//                    if (in1.compareToIgnoreCase("begin") == 0) {
//                        in1 = scan1.next();
//                    } else
//                        //transfer all numbers in document into integer
//                        num = Integer.parseInt(in1);
//                    //add numbers into linked list
//                    p1.add(num);
//                    //copy one linked list
//                    p11.add(num);
//                    in1 = scan1.next();
//                }
//                name.add(1);
//            }
//            //read the second file
//            if (scan2.hasNextLine()) {
//                in2 = scan2.next();
//                //stop reading when "end" occurs
//                while (in2.compareToIgnoreCase("end") != 0) {
//                    //start reading
//                    //when it's begin skip the line
//                    if (in2.compareToIgnoreCase("begin") == 0) {
//                        in2 = scan2.next();
//                    } else
//                        //transfer all numbers in document into integer
//                        num = Integer.parseInt(in2);
//                    //add numbers into linked list
//                    p2.add(num);
//                    //copy one linked list
//                    p12.add(num);
//                    in2 = scan2.next();
//                }
//                name.add(2);
//            }
//            //read the third file
//            if (scan3.hasNextLine()) {
//                in3 = scan3.next();
//                //stop reading when "end" occurs
//                while (in3.compareToIgnoreCase("end") != 0) {
//                    //start reading
//                    //when it's begin skip the line
//                    if (in3.compareToIgnoreCase("begin") == 0) {
//                        in3 = scan3.next();
//                    } else
//                        //transfer all numbers in document into integer
//                        num = Integer.parseInt(in3);
//                    //add numbers into linked list
//                    p3.add(num);
//                    //copy one linked list
//                    p13.add(num);
//                    in3 = scan3.next();
//                }
//                name.add(3);
//            }
//            //First run the LRU
//            if(loop == 0) {
//                //create process put ID, name and text in the document into object Process
//                Process process1 = new Process(name.get(0), f1, p1);
//                Process process2 = new Process(name.get(1), f2, p2);
//                Process process3 = new Process(name.get(2), f3, p3);
//                //add processes to process list for LRU
//                processListL.add(process1);
//                processListL.add(process2);
//                processListL.add(process3);
//                F /= processListL.size();
//                LRU[] memLRU = new LRU[processListL.size()];
//                for (int i = 0; i < processListL.size(); i++) {
//                    memLRU[i] = new LRU(F);
//                }
//                RRL runLru = new RRL(processListL, Q, memLRU);
//                //start running the LRU
//                runLru.run();
//            }else//Run the clock
//                {
//                    //create process with ID, name and text in the document
//                Process process11 = new Process(name.get(0), f1, p11);
//                Process process22 = new Process(name.get(1),f2, p12);
//                Process process33 = new Process(name.get(2), f3, p13);
//                //put processes into the clock process list
//                processListC.add(process11);
//                processListC.add(process22);
//                processListC.add(process33);
//                Clock[] memClock = new Clock[processListC.size()];
//
//                for (int i = 0; i < processListC.size(); i++) {
//                    memClock[i] = new Clock(F);
//                }
//                F /= processListC.size();
//                RRC runClock = new RRC(processListC, Q, memClock);
//                //run clock
//                runClock.run();
//            }
//        }
    }
