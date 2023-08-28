# Java/Maven-Guide
## Installationsanleitung

Diese Anleitung führt Sie durch den Prozess der Installation von Java auf sowohl einem Linux- als auch einem Windows-Betriebssystem. Java ist eine weit verbreitete Programmiersprache und Laufzeitumgebung, die für eine Vielzahl von Anwendungen verwendet wird.

Bevor Sie beginnen, stellen Sie sicher, dass Sie über Administratorrechte (unter Windows) oder ausreichende Berechtigungen (unter Linux) verfügen, um die Software auf Ihrem System zu installieren.

### Java
#### Installation unter Linux
1. **Öffnen Sie das Terminal**:  
   Gehen Sie zu Ihrem Anwendungsmenü oder drücken Sie `Ctrl` + `Alt` + `T`, um das Terminal zu öffnen.

2. **Aktualisieren Sie die Paketliste (optional, aber empfohlen)**:  
   Geben Sie den folgenden Befehl ein und drücken Sie `Enter`, um die Paketliste zu aktualisieren. Dies stellt sicher, dass Sie die neuesten verfügbaren Versionen erhalten.

   ```bash
   sudo apt update
   ```

3. **Installieren Sie Java**:  
   Geben Sie den folgenden Befehl ein und drücken Sie `Enter`, um die Standard-Java-Entwicklungsumgebung (JDK) zu installieren:

   ```bash
   sudo apt install default-jdk
   ```

4. **Überprüfen Sie die Installation**:  
   Nach Abschluss der Installation können Sie überprüfen, ob Java ordnungsgemäß installiert wurde, indem Sie den folgenden Befehl eingeben:

   ```bash
   java -version
   ```

#### Installation unter Windows
1. **Besuchen Sie die offizielle Java-Website**:  
Gehen Sie zu [java.com](https://www.java.com) in Ihrem Webbrowser.

2. **Laden Sie das Java-Installationsprogramm herunter**:  
   Auf der Java-Website wird Ihnen ein Download-Button angezeigt. Klicken Sie darauf, um das Java-Installationsprogramm herunterzuladen.

3. **Führen Sie das Installationsprogramm aus**:  
   Navigieren Sie zu dem Speicherort, an dem Sie die Datei heruntergeladen haben, und doppelklicken Sie auf das Installationsprogramm (.exe-Datei), um den Installationsvorgang zu starten.

4. **Befolgen Sie die Installationsanweisungen**:  
   Folgen Sie den Anweisungen des Installationsassistenten, um die Installation abzuschließen. Sie können in der Regel die Standardeinstellungen beibehalten, es sei denn, Sie haben spezifische Präferenzen.

5. **Überprüfen Sie die Installation**:  
   Öffnen Sie die Eingabeaufforderung, indem Sie `Cmd` + `R` drücken und "cmd" eingeben. Geben Sie dann den Befehl ein:

   ```bash
   java -version
   ```

### Maven
Maven ist ein leistungsstarkes Build-Management-Tool, das häufig für Java-Projekte verwendet wird. So können sie Maven installieren:

#### Installation unter Linux
1. **Öffnen Sie das Terminal**:  
   Öffnen Sie das Terminal mit den Schritten aus dem vorherigen Abschnitt.

2. **Führen Sie den folgenden Befehl aus**:  
   Geben Sie den folgenden Befehl ein und drücken Sie `Enter`, um Maven zu installieren:

   ```bash
   sudo apt install maven
   ```
   
3. **Überprüfen Sie die Installation:**  
   Nach Abschluss der Installation können Sie überprüfen, ob Maven korrekt installiert wurde, indem Sie den folgenden Befehl ausführen:

   ```bash
   mvn -version
   ```
   
#### Installation unter Windows
1. **Laden Sie das Maven-Installationsprogramm herunter:**  
   Besuchen Sie die [Apache Maven-Website](https://maven.apache.org/download.cgi) in Ihrem Webbrowser. Laden Sie das neueste Binärpaket (zip-Datei) herunter.

2. **Entpacken Sie das Archiv:**  
   Navigieren Sie zum Speicherort, an dem Sie die zip-Datei heruntergeladen haben. Entpacken Sie den Inhalt des Archivs in einen Ordner Ihrer Wahl (zum Beispiel C:\Program Files\Maven).

3. **Systemvariablen bearbeiten:**  
   Fügen Sie dem System-Path die Pfadangabe zum bin-Verzeichnis von Maven hinzu. Dies ermöglicht es der Eingabeaufforderung, Maven von überall aus zu starten.

4. **Überprüfen Sie die Installation:**  
   Öffnen Sie die Eingabeaufforderung und geben Sie den folgenden Befehl ein, um zu überprüfen, ob Maven korrekt installiert wurde:

   ```bash
   mvn -version
   ```
