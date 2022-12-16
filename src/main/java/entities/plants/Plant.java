package entities.plants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.concurrent.CopyOnWriteArrayList;

@Data
@NoArgsConstructor
@EqualsAndHashCode

public class Plant {
    private double mass=1;
   private   int numberOfAnimalsInCage=200;
   private   int numberOfStart=200;

    @EqualsAndHashCode.Include
    public static String name="Plant";


    public CopyOnWriteArrayList<Plant> replica(){
       CopyOnWriteArrayList<Plant> plants=new CopyOnWriteArrayList<>();
      for (int i=0; i<20; i++){plants.add(new Plant());}
       System.out.println("Plant be fruitful and multiply");
      return plants;
   }

}
