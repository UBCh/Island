import entities.entitiy.Animal;
import entities.plants.Plant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResultReport {

    private String path = "result.txt";

    public ResultReport() {
    }

    public ResultReport(String path) {
	this.path = path;
    }

    public void writingToFile(String[] toStringForWrite) {

	StringBuilder stringBuilder = new StringBuilder();
	String[] tmp = toStringForWrite;
	for (int i = tmp.length-1; i >=0; i--) {
	    stringBuilder.append(tmp[i]).append("\n");
	}
	try {
	    FileWriter writer = new FileWriter(path, false);
	    stringBuilder.append("++++++++++++++++++++++step++++++++++++++++++++++++++++++++").append("\n");
	    writer.append(stringBuilder);
	    writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }







}
