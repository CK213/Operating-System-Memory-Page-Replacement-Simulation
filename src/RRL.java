//Cheuk Hang Ku
//Student ID: 3291914
//COMP 2240-A3
import java.util.*;
public class RRL {

        private Process curJ;
        private LinkedList<Process> readyQ= new LinkedList<Process>();


        private int timeQ;
        boolean loop=true;
        LRU[] mem;
        ArrayList<Process> job;

        //Construction
        public RRL(ArrayList<Process> job,int timeQ,LRU[] mem){
            this.job = job;
            this.timeQ = timeQ;
            this.mem = mem;
        }
        //start running algorithm
        public void run(){
            int cpuTime = 0;
            int pTimeQ = timeQ;
            for (int i = 0; i < job.size(); i++){
                if (mem[i].hasP(job.get(i).pageEle())){
                    readyQ.offer(job.get(i));
                }else{
                    //set the first fault
                    job.get(i).setFaultTime(cpuTime);
                }
            }
            cpuTime++;
            //check if all the tasks finished
            while(loop){
                loop=false;
                for (int i = 0;i < job.size();i++){
                    if (job.get(i).finished()){
                        loop=true;
                    }else{
                        //set turnaround time
                        job.get(i).settt(cpuTime);
                    }
                }
                for (int i = 0;i < job.size();i++){
                    //when swapping is occurring
                    if( job.get(i).getReadyT() == cpuTime && job.get(i).finished()){
                        readyQ.offer(job.get(i));
                        int pageNum=job.get(i).pageEle();
                        if(!mem[i].addP(pageNum)){
                            mem[i].addLRU(pageNum);
                        }
                    }
                }
                //When fault occurs
                //when there are something in the ready queue
                if (!readyQ.isEmpty()) {
                    curJ = readyQ.poll();
                    int iD = curJ.getId() - 1;
                    this.curJ.getPage();
                    if (curJ.finished()) {
                        //quantum do the RR scheduling
                        if (mem[iD].hasP(curJ.pageEle())) {
                            if (pTimeQ > 1 && curJ.finished()) {
                                readyQ.addFirst(curJ);
                                pTimeQ--;
                            } else {
                                pTimeQ = timeQ;
                                readyQ.offer(curJ);
                            }
                        } else {
                            curJ.setFaultTime(cpuTime + 1);
                        }
                    }
                }
                cpuTime++;
            }

            System.out.println("LRU - Fixed:");
            System.out.println("PID   Process Name      Turnaround Time     #Faults   Fault Times");
            for (int i=0;i<job.size();i++){
                int PID=i+1;
                String name=job.get(i).getName();
                int faults=job.get(i).getFaults();
                int turnaroundTime=job.get(i).tt();
                String timelist=job.get(i).getFaultTime();
                timelist=timelist.substring(0,timelist.length()-1);
                System.out.println(PID+"     "+name+"      "+ turnaroundTime+"                 "+faults+"        {"+timelist+"}");
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

    }
