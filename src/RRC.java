//Cheuk Hang Ku
//Student ID: 3291914
//COMP 2240-A3
import java.util.*;

public class RRC {
    private Process curJob;
    private LinkedList<Process> readyQ = new LinkedList<>();
    private int timeQ;
    boolean loop = true;
    Clock[] mem;
    ArrayList<Process> job;

    public RRC(ArrayList<Process> job, int timeQ, Clock[] mem) {
        this.job = job;
        this.timeQ = timeQ;
        this.mem = mem;
    }
    //run the program
    public void run() {
        int cpuTime = 0;
        int pTimeQ = timeQ;
        for (int i = 0; i < job.size(); i++) {
                job.get(i).setFaultTime(cpuTime);
        }
        cpuTime++;
        while (loop) {
            loop = false;
            //run all processes
            for (int i = 0; i < job.size(); i++) {
                if (job.get(i).finished()) {
                    loop = true;
                } else {
                    //set turnaround time
                    job.get(i).settt(cpuTime + 7);
                }
            }
            for (int i = 0; i < job.size(); i++) {
                if (job.get(i).getReadyT() == cpuTime && job.get(i).finished()) {
                    readyQ.offer(job.get(i));
                    int pNum = job.get(i).pageEle();
                    if (!mem[i].addP(pNum)) {
                        mem[i].addClock(pNum);
                    }
                }
            }
            if (!readyQ.isEmpty()) {
                curJob = readyQ.poll();
                int iD = curJob.getId() - 1;
                this.curJob.getPage();
                if (curJob.finished()) {
                    //quantum do the RR scheduling
                    if (mem[iD].hasP(curJob.pageEle())) {
                        if (pTimeQ > 1 && curJob.finished()) {
                            readyQ.addFirst(curJob);
                            pTimeQ--;
                        } else {
                            pTimeQ = timeQ;
                            readyQ.offer(curJob);
                        }
                    } else {
                        curJob.setFaultTime(cpuTime + 1);
                    }
                }
            }
            cpuTime++;
        }
        System.out.println("Clock - Fixed:");
        System.out.println("PID   Process Name      Turnaround Time     #Faults   Fault Times");
        for (int i = 0; i < job.size(); i++) {
            int PID = i + 1;
            String name = job.get(i).getName();
            int faults = job.get(i).getFaults();
            int turnaroundTime = job.get(i).tt();
            String timelist = job.get(i).getFaultTime();
            timelist = timelist.substring(0, timelist.length() - 1);
            System.out.println(PID + "     " + name + "      " + turnaroundTime + "                 " + faults + "        {" + timelist + "}");
        }
    }
}
