//Cheuk Hang Ku
//Student ID: 3291914
//COMP 2240-A3
import java.util.*;
public class Memory {
    private int memSize;
    public ArrayList<Integer> frameList;
    Memory(int size){
        this.memSize = size;
        this.frameList = new ArrayList<>(size);
    }

    public boolean addP(int p){
        if(frameList.size()< memSize){
            frameList.add(p);
            return true;
        }
        return false;
    }
    public void setP(int in, int p){
        frameList.set(in, p);
    }
    public ArrayList<Integer>getFrameList(){
        return frameList;
    }
    public int size(){
        return memSize;
    }
    public int getframeListSize(){
        return frameList.size();
    }
    public int getP(int in){
        return frameList.get(in);
    }
    public ArrayList<Integer> getAll(){
        return frameList;
    }
    public boolean Full(){
        if(frameList.size() < memSize){
            return false;
        }
        return true;
    }
}
