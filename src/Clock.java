//Cheuk Hang Ku
//Student ID: 3291914
//COMP 2240-A3
public class Clock extends Memory{
    int pointer;
   Clock(int size){
       super(size);
   }
   public void addClock(int p){
       super.setP(0,p);
       moveP();
   }

    public boolean hasP(int p){
        for(int i = 0; i < frameList.size(); i++){
            if(frameList.get(i) == p){
                return true;
            }
        }
        return false;
    }

    public void moveP(){
        if(pointer<super.size()){
            pointer++;
        }else{
            pointer=0;
        }
    }
}
