package zarkoix.velocityPlate.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



public class ClassFinder{
	public static <T, R extends T> List<T> search(String packageName,  Class<T> type) throws InstantiationException, IllegalAccessException{
		
	//Preparation
	URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
	List<T> list = new ArrayList<T>();

	// Filter .class files.
	File[] files = new File(root.getFile()).listFiles(new FilenameFilter() {
		public boolean accept(File dir, String name) {
			return name.endsWith(".class");
		}
		});

	// Find classes implementing Class<T> type.
	for (File file : files) {
		String className = file.getName().replaceAll(".class$", "");
		Class<R> cls;
		try {
			cls = (Class<R>) Class.forName(packageName + "." + className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			cls = null;
		}
		System.out.println("Found class: " + cls.getSimpleName());
		//Does Class<R> cls extend type?
		if(cls != null && cls.getSuperclass() == type){
			//Yes, add cls to list
			System.out.println("AddING class: " + cls.getSimpleName());
			T elem = cls.newInstance();
			if(elem != null){
				list.add(elem);
				System.out.println("Added class: " + cls.getSimpleName());}
			else{
				if(cls == null){
					System.out.println("Did not add class: " + cls.getSimpleName() + " - was null");
				}else{
					System.out.println("Did not add class: " + cls.getSimpleName() + " - reason unknown");				
				}
			}
		}
	}
return list;	
}
}
