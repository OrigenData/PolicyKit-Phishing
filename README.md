PolicyKit Phishing
=========================
**Description:** Solicita la contraseña root del sistema Linux para falsas actualizaciones, la contraseña es almacenada en texto plano para ser utilizada por el atacante.

**License:** GNU General Public License v3.0

**Source code:** https://github.com/OrigenData/PolicyKit-Phishing	

**Documentation:** https://www.origendata.com/

###### Screenshot
![](screenshot/screenshot1.png)
![](screenshot/screenshot2.png)
![](screenshot/screenshot3.png)

### Nota
El area de trabajo se almacena en  `$HOME/.local/policyKit-agent-01`, si te hes necesario cambiarlo puedes modificar las rutas en la clase **ArchiveData.java**.

### Dependencies (Test on Fedora 30)

**PolicyKit Phishing** requiere que se instalen las siguientes librerias para ejecutar:
* GTK 3.20
* java-gnome 4.1.3
* OpenJDK 12
