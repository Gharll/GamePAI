package gameEngine;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Config")
public class Config {
	private int boardSize;
	private int matchesToWin;
	private int obstaclesQuantity;
	private static Config config;
	
	private Config(){
		
	}
	
	
	public static Config getInstance(){
		if(config == null){
			setup();
		}
		return config;
	}
	
	private static void setup(){
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Config.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			File xml = new File("src/gameEngine/config.xml");
			Config.config = (Config) unmarshaller.unmarshal(xml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@XmlElement(name = "boardSize")
	public void setBoardSize(int boardSize){
		this.boardSize = boardSize;
	}
	
	@XmlElement(name = "matchesToWin")
	public void setMatchesToWin(int matchesToWin){
		this.matchesToWin = matchesToWin;
	}
	
	@XmlElement(name = "obstaclesQuantity")
	public void setObstaclesQuantity(int obstaclesQuantity){
		this.obstaclesQuantity = obstaclesQuantity;
	}
	
	public int getBoardSize(){
		return boardSize;
	}
	
	public int getMatchesToWin(){
		return matchesToWin;
	}
	
	public int getObstaclesQuantity(){
		return obstaclesQuantity;
	}
}
