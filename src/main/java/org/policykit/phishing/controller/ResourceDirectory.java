package org.policykit.phishing.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ResourceDirectory {
	

	@SuppressWarnings("unused")
	private String home = 	null;
	private String shelter = null;
	private String gladeJar = null;
	private String gladeShelter;
	@SuppressWarnings("unused")
	private String directory = null;
	@SuppressWarnings("unused")
	private String gladefile = null;
	
	public ResourceDirectory(String Home, String Shelter, String GladeShelter, String GladeJar) {
		
		this.home = Home;
		this.shelter = Shelter;
		this.gladeShelter = GladeShelter;
		this.gladeJar = GladeJar;

	}
	
	
	
	/**
	 * El metodo verificara si existe o no el directorio, 
	 * @author cmcgrath
	 * @param dir
	 */
	public boolean existsDirectory() {
		
		File folder = new File(gladeShelter);

		return folder.exists();
	}
	
	
	public void createShelter() {
		
		File folder = new File(shelter);
		folder.mkdirs();
	
	}
	
	/**
	 * Este metodo devuelve Verdadero o false si existe o no el archivo Glade
	 * @return Si existe o no el archivo Glade
	 */
	public boolean existsGlade() {
		
		String filePath = gladeShelter;
		byte[] bytesGlade = null;
		
		//Es el hash de Glade, si modificas tienes que actualizar esta variable
		String md5OrigenGlade = "6288f5b075b867337e2f3ee1cdebefaf";
		String md5FinishGlade = "";
     
        try {
        	
        	bytesGlade = Files.readAllBytes(Paths.get(filePath));
        	
        	MessageDigest fileGladeHash = null;
        	
        	fileGladeHash = MessageDigest.getInstance("MD5");
            
            byte[] bytesResumeGlade = fileGladeHash.digest(bytesGlade);

            BigInteger resumenNumero = new BigInteger(1, bytesResumeGlade);
            
            md5FinishGlade = resumenNumero.toString(16);
            
            
        } catch (NoSuchAlgorithmException | IOException e) {}
		
        boolean exists = (md5OrigenGlade.equals(md5FinishGlade))?true:false;
        
        return exists;		
	}
	
	/**
	 * Copiar el archivo Glade almacenado en el Jar y lo pega en el directorios HOME
	 */
    public void copyResourceGlade() {
    	
    	
        try {
        	
        	System.out.println(gladeJar);
        	System.out.println("info>    "+getClass().toString());

            InputStream input=getClass().getResourceAsStream(gladeJar);
            OutputStream output= new FileOutputStream(gladeShelter);
            
            byte [] buffer = new byte[1024];
            
            int bytesRead;
 
              while ((bytesRead = input.read(buffer,0,1024)) != -1) {
 
                    output.write(buffer, 0, bytesRead);
                }
 
              output.close();
              input.close();
 
         } catch(IOException e) {
            System.err.println(""+e.getMessage());
         }
      }

}
