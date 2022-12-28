package entities.plants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.concurrent.CopyOnWriteArrayList;


@NoArgsConstructor
@EqualsAndHashCode

public class Plant {
    private double mass=1;
   private   int numberOfAnimalsInCage=200;
   public static int numberOfStart=200;

    @EqualsAndHashCode.Include
    public static String name="Plant";

    public static int getNumberOfStart() {
        return numberOfStart;
    }

    public double getMass() {
        return mass;
    }

    public static CopyOnWriteArrayList<Plant> replica(){
       CopyOnWriteArrayList<Plant> plants=new CopyOnWriteArrayList<>();
      for (int i=0; i<20; i++){plants.add(new Plant());}
        return plants;
   }

}
