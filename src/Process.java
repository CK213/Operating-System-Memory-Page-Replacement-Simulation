//Cheuk Hang Ku
//Student ID: 3291914
//COMP 2240-A3
import java.util.LinkedList;
import java.util.Queue;
public class Process{
    private int iD;
    private int readyT;

    private String name;
    Queue<Integer> page = new LinkedList<>();

    private int tt = -1;
    private int faults = 0;
    private String faultTime ="";

    Process(int iD, String name, Queue<Integer> page){
        this.iD = iD;
        this.name = name;
        this.page = page;
    }
    //getter and setter
    public int pageEle(){
        return page.element();
    }
    public int getPage(){
        return page.poll();
    }
    public Queue<Integer> getQ(){
        return page;
    }
    public int getQSize(){
        return page.size();
    }
    public boolean finished(){
        return !page.isEmpty();
    }
    public int getId(){
        return iD;
    }
    //for output
    public void setFaultTime(String data){
        faultTime += data + ", ";
    }
    //add 6 when swap the page
    public void setFaultTime(int time){
        readyT=time + 6;
        this.setFaultTime(Integer.toString(time));
        //add the number of faults
        faults++;
    }
    public String getFaultTime(){
        return faultTime;
    }
    public int getReadyT(){
        return readyT;
    }
    public int getFaults(){
        return faults;
    }
    public void settt(int time){
        if(tt< 0){
            tt = time;
        }
    }
    public int tt(){
        return tt;
    }
    public String getName(){
        return name;
    }
}
