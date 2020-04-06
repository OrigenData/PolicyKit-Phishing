package org.policykit.phishing.chronic;

public interface ArchiveData {
	
	
	//*** System ***\\
	String  DESKTOP_ENVIRONMENT = System.getenv("XDG_CURRENT_DESKTOP");
	
	//*** Directorios ***\\
	String HOME = System.getProperty("user.home");
	String NAME_GLADE = "application.glade";
	String SHELTER = HOME+"/.local/share/policyKit-agent-01/";		 		// Carpeta raiz de archivo JAR
	//String RESOURCE_GLADE_PROJECT = "src/main/java/com/application/view/";	// Project Eclipse
	String RESOURCE_GLADE_SHELTER = SHELTER+NAME_GLADE;  						// Archivo Glade en root
	String RESOURCE_GLADE_JAR = "/org/policykit/phishing/view/application.glade";
	String PASSWORD_ROOT = SHELTER+"0cap0.dat";
	//String RESOURCE_GLADE_JAR = "../view/application.glade";


}
