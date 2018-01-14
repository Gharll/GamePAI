package server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String keyWords;
	private ArrayList <String> args;
	private ArrayList <Object> objects;
	
	public CommandMessage(){
		
	}
	
	public CommandMessage(String keyWords){
		this.keyWords = keyWords;
	}
	
	public void setKeyWords(String keyWords){
		this.keyWords = keyWords;
	}
	
	public void setArgs(String ... args){
		this.args = new ArrayList<String>(Arrays.asList(args));
	}
	
	public void setArgs(ArrayList<String> args){
		this.args = args;
	}
	
	public void addArg(String arg){
		if(args == null){
			args = new ArrayList<String>();
		}
		args.add(arg);
	}
	
	public void addArgumentObject(Object o){
		if(objects == null){
			objects = new ArrayList<Object>();
		}
		objects.add(o);
	}
	
	public void setArgumentObjects(Object ... objects){
		this.objects = new ArrayList<>(objects.length);
		for(Object o : objects){
			this.objects.add(o);
		}
		
		//old (fail)
		//this.objects = new ArrayList<Object>(Arrays.asList(objects));
	}
	
	public void setArgumentObjects(ArrayList <Object> objects){
		this.objects = objects;
	}
	
	public String getKeyWords(){
		return keyWords;
	}
	
	public ArrayList <String> getArgs(){
		return args;
	}
	
	public ArrayList <Object> getObjects(){
		return objects;
	}
	
	public String getConcatenatedArgs(){
		String result = "";
		for(String arg : args){
			result += arg;
		}
		return result;
	}
}
