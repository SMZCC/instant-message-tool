/**
 * @author 流浪大法师
 * @time 2016-3-22 下午2:08:35
 * @email liuliangsir@gmail.com
 * @descript
 * @warning 注意将字符集设置成UTF-8
 */
package com.vince.controller.test;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine; 
import javax.script.ScriptEngineManager;
import javax.script.ScriptContext;
import javax.script.Bindings;
import javax.script.ScriptException;   /**  * 直接调用js代码  */
public class TestJavaScript {    
	public static void main(String[] args){
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		/**
		 * 在Java中直接调用js代码
		 * engine.eval("var a=3; var b=4;print (a+b);");
		 * */
		
		/**
		 * 在Java中绑定js变量
		 * engine.put("a", 4);
		 * engine.put("b", 3);
		 * Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
		 * Double result;
		 * try {
		 * 		result = (Double) engine.eval("a+b");
		 * 		System.out.println("result = " + result);
		 * 		engine.eval("c=a+b");    
		 * 		Double c = (Double)engine.get("c");
		 * 		System.out.println("c = " + c);
		 * }catch(ScriptException e){
		 * 		e.printStackTrace();
		 * }
		 * */
		String jsFileName = "src\\js\\xhr.js";   // 读取js文件      
		try {
			FileReader reader = new FileReader(jsFileName);//执行指定脚本
			engine.eval(reader);
			if(engine instanceof Invocable){ 
				Invocable invoke = (Invocable)engine;//调用merge方法，并传入两个参数
				Object a = invoke.invokeFunction("test", 1,2);
				System.out.println(a);
			}  
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }   
}
