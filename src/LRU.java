//Cheuk Hang Ku
//Student ID: 3291914
//COMP 2240-A3
import java.util.*;

public class LRU extends Memory{
    LRU(int size){
        super(size);
    }
    //poll the top list
    public void bottom(int i){
        int p = frameList.get(i);
        int end = frameList.size()-1;
        for(int j = i; j<end; j++){
            int next = j+1;
            frameList.set(j, frameList.get(next));
        }
        frameList.set(end,p);
    }
    public void addLRU(int p){
        super.setP(0, p);
        this.bottom(0);
    }
    //check if page exist
    public boolean hasP(int p){
        for(int i = 0; i< frameList.size(); i++){
            if(frameList.get(i)==p){
                this.bottom(i);
                return true;
            }
        }
        return false;
    }
}
