import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    String pathFile = "src/main/resources/config.properties";
    FileInputStream fis;
    Properties property = new Properties();
    int sizeOfTheIslandIsHorizontal = 0;
    int sizeOfTheIslandIsVertical = 0;
    String conditionForStoppingTheSimulation = "";
    int durationOfTheSimulationCycle = 0;
    int numberOfBuffaloAtTheStart = 0;
    int numberOfCaterpillarAtTheStart = 0;
    int numberOfDeerAtTheStart = 0;
    int numberOfDuckAtTheStart = 0;
    int numberOfGoatAtTheStart = 0;
    int numberOfHogAtTheStart = 0;
    int numberOfHorseAtTheStart = 0;
    int numberOfMouseAtTheStart = 0;
    int numberOfRabbitAtTheStart = 0;
    int numberOfSheepAtTheStart = 0;
    int numberOfBearAtTheStart = 0;
    int numberOfBoaConstrictorAtTheStart = 0;
    int numberOfEagleAtTheStart = 0;
    int numberOfFoxAtTheStart = 0;
    int numberOfWolfAtTheStart = 0;
    int numberOfPlantAtTheStart = 0;
    int numberOfCubsInBear = 0;
    int numberOfCubsInBoaConstrictor = 0;
    int numberOfCubsInEagle = 0;
    int numberOfCubsInFox = 0;
    int numberOfCubsInWolf = 0;
    int numberOfCubsInBuffalo = 0;
    int numberOfCubsInCaterpillar = 0;
    int numberOfCubsInDeer = 0;
    int numberOfCubsInDuck = 0;
    int numberOfCubsInGoat = 0;
    int numberOfCubsInHog = 0;
    int numberOfCubsInHorse = 0;
    int numberOfCubsInMouse = 0;
    int numberOfCubsInRabbit = 0;
    int numberOfCubsInSheep = 0;

    public  void setConfigurations() throws Exception {

	try {
	    fis = new FileInputStream(pathFile);
	    property.load(fis);
	    sizeOfTheIslandIsHorizontal = Integer.parseInt(property.getProperty("sizeOfTheIslandIsHorizontal"));
	    sizeOfTheIslandIsVertical = Integer.parseInt(property.getProperty("sizeOfTheIslandIsVertical"));
	    conditionForStoppingTheSimulation = property.getProperty("conditionForStoppingTheSimulation");
	    durationOfTheSimulationCycle = Integer.parseInt(property.getProperty("durationOfTheSimulationCycle"));
	    numberOfBuffaloAtTheStart = Integer.parseInt(property.getProperty("numberOfBuffaloAtTheStart"));
	    numberOfCaterpillarAtTheStart = Integer.parseInt(property.getProperty("numberOfCaterpillarAtTheStart"));
	    numberOfDeerAtTheStart = Integer.parseInt(property.getProperty("numberOfDeerAtTheStart"));
	    numberOfDuckAtTheStart = Integer.parseInt(property.getProperty("numberOfDuckAtTheStart"));
	    numberOfGoatAtTheStart = Integer.parseInt(property.getProperty("numberOfGoatAtTheStart"));
	    numberOfHogAtTheStart = Integer.parseInt(property.getProperty("numberOfHogAtTheStart"));
	    numberOfHorseAtTheStart = Integer.parseInt(property.getProperty("numberOfHorseAtTheStart"));
	    numberOfMouseAtTheStart = Integer.parseInt(property.getProperty("numberOfMouseAtTheStart"));
	    numberOfRabbitAtTheStart = Integer.parseInt(property.getProperty("numberOfRabbitAtTheStart"));
	    numberOfSheepAtTheStart = Integer.parseInt(property.getProperty("numberOfSheepAtTheStart"));
	    numberOfBearAtTheStart = Integer.parseInt(property.getProperty("numberOfBearAtTheStart"));
	    numberOfBoaConstrictorAtTheStart = Integer.parseInt(property.getProperty("numberOfBoaConstrictorAtTheStart"));
	    numberOfEagleAtTheStart = Integer.parseInt(property.getProperty("numberOfEagleAtTheStart"));
	    numberOfFoxAtTheStart = Integer.parseInt(property.getProperty("numberOfFoxAtTheStart"));
	    numberOfWolfAtTheStart = Integer.parseInt(property.getProperty("numberOfWolfAtTheStart"));
	    numberOfPlantAtTheStart = Integer.parseInt(property.getProperty("numberOfPlantAtTheStart"));

	    numberOfCubsInBear = Integer.parseInt(property.getProperty("numberOfCubsInBear"));
	    numberOfCubsInBoaConstrictor = Integer.parseInt(property.getProperty("numberOfCubsInBoaConstrictor"));
	    numberOfCubsInEagle = Integer.parseInt(property.getProperty("numberOfCubsInEagle"));
	    numberOfCubsInFox = Integer.parseInt(property.getProperty("numberOfCubsInFox"));
	    numberOfCubsInWolf = Integer.parseInt(property.getProperty("numberOfCubsInWolf"));
	    numberOfCubsInBuffalo = Integer.parseInt(property.getProperty("numberOfCubsInBuffalo"));
	    numberOfCubsInCaterpillar = Integer.parseInt(property.getProperty("numberOfCubsInCaterpillar"));
	    numberOfCubsInDeer = Integer.parseInt(property.getProperty("numberOfCubsInDeer"));
	    numberOfCubsInDuck = Integer.parseInt(property.getProperty("numberOfCubsInDuck"));
	    numberOfCubsInGoat = Integer.parseInt(property.getProperty("numberOfCubsInGoat"));
	    numberOfCubsInHog = Integer.parseInt(property.getProperty("numberOfCubsInHog"));
	    numberOfCubsInHorse = Integer.parseInt(property.getProperty("numberOfCubsInHorse"));
	    numberOfCubsInMouse = Integer.parseInt(property.getProperty("numberOfCubsInMouse"));
	    numberOfCubsInRabbit = Integer.parseInt(property.getProperty("numberOfCubsInRabbit"));
	    numberOfCubsInSheep = Integer.parseInt(property.getProperty("numberOfCubsInSheep"));

	} catch (IOException e) {
	    System.err.println("ОШИБКА: Файл свойств отсуствует!");
	}
//	здесь сеттеры в классах для установки параметров и получения образца клетки
        Ark ark=new Ark();
	ark.setBuffalo(numberOfCubsInBuffalo,numberOfBuffaloAtTheStart);
	ark.setBear(numberOfCubsInBear,numberOfBearAtTheStart);
	ark.setCaterpillar(numberOfCubsInCaterpillar,numberOfCaterpillarAtTheStart);
	ark.setDeer(numberOfCubsInDeer,numberOfDeerAtTheStart);
	ark.setBoaConstrictor(numberOfCubsInBoaConstrictor,numberOfBoaConstrictorAtTheStart);
	ark.setDuck(numberOfCubsInDuck, numberOfDuckAtTheStart);
	ark.setEagle(numberOfCubsInEagle, numberOfEagleAtTheStart);
	ark.setFox(numberOfCubsInFox, numberOfFoxAtTheStart);
	ark.setGoat(numberOfCubsInGoat, numberOfGoatAtTheStart);
	ark.setHog(numberOfCubsInHog, numberOfHogAtTheStart);
	ark.setHorse(numberOfCubsInHorse, numberOfHorseAtTheStart);
	ark.setMouse(numberOfCubsInMouse, numberOfMouseAtTheStart);
	ark.setPlant(numberOfPlantAtTheStart);
	ark.setRabbit(numberOfCubsInRabbit, numberOfRabbitAtTheStart);
	ark.setSheep(numberOfCubsInSheep, numberOfSheepAtTheStart);
	ark.setWolf(numberOfCubsInWolf, numberOfWolfAtTheStart);
//	Cell cell=Cell.getInstance(ark);
//	cell.populate();
	Cell.setArk(ark);
	PlayingField.setSizeOfTheIslandIsHorizontal(sizeOfTheIslandIsHorizontal);
	PlayingField.setSizeOfTheIslandIsVertical(sizeOfTheIslandIsVertical);
	PlayingField.setConditionForStoppingTheSimulation(conditionForStoppingTheSimulation);
	PlayingField.setDurationOfTheSimulationCycle(durationOfTheSimulationCycle);
//	PlayingField.setCell(cell);
	PlayingField playingField=PlayingField.getInstance();
    }

}
