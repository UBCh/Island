import entities.entitiy.Animal;
import entities.plants.Plant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResultReport {

    private String path = "result.txt";
    private int simulationStep = 1;

    public ResultReport() {
    }

    public ResultReport(String path) {
	this.path = path;
    }

    public void writingToFile(String[] toStringForWrite) {

	StringBuilder stringBuilder = new StringBuilder();
	String[] tmp = toStringForWrite;
	for (int i =0; i< tmp.length; i++) {
	    stringBuilder.append(tmp[i]).append("\n");
	}
	try {
	    FileWriter writer = new FileWriter(path, true);
	    stringBuilder.append("end of simulation step   "+simulationStep).append("\n");
	    writer.append(stringBuilder);
	    simulationStep++;
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }







}
