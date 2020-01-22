package org.java.jvm;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptTest {
	
	private static final String SCRIPT = "var i = 1; return 3";
	
	private static ScriptEngineManager manager = new ScriptEngineManager();
	
	private static ScriptEngine engine = manager.getEngineByName("js");
	
	public static void main(String[] args) {
		while(true){
			StringBuilder sb = new StringBuilder();
			sb.append("function test(url){");
			sb.append(SCRIPT);
			sb.append("}");
			try {
				engine.eval(sb.toString());
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		}
	}
}
