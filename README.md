# Wüstenrot c'IT@Work Demo

Das ist der Code zu unserer c'IT@Work Demo, Open-Source zur Verfügung gestellt.

Die produktive Applikation findest du [hier](https://watson-at-wds.eu-de.mybluemix.net/).

## Worum geht's?
Diese Applikation ist ein Show-Case, wie man künstliche Intelligenz in Java anbinden kann. Zusätzlich dazu haben wir ein Angular2-Frontend in Typescript entwickelt.  Als AI (Artificial Intelligence) wird IBM Watson verwendet.

## Die Komponenten
Folgende Komponenten wurden für diese Applikation verwendet.

### IBM Watson Conversation Service
Für unseren Chatbot (Svibo) verwenden wir den Watson Conversation Service.

Details dazu und wie das funktioniert findest du direkt bei der offiziellen [Watson Conversation Service Dokumentation](https://console.bluemix.net/docs/services/conversation/index.html#about).

Für unsere Demo haben wir Svibo mit den notwendigen Intents trainiert und einen einfachen Dialog gebaut.

### IBM Watson Visual Recognition Service
Für die Bild-Erkennungskomponente haben wir den Watson Visual-Recognition Service verwendet. Dazu haben wir eigene Classfier und Klassifizierungsklassen definiert und diese mit den entsprechenden Trainingssets trainiert.

Ein kurzes Tutorial sowie notwendige Links um beginnen zu können findest du direkt auf der offiziellen [IBM Homepage zum VR-Service](https://www.ibm.com/watson/services/visual-recognition/).

### Das Frontend
Da wir unsere Frontend-Komponenten mit Angular entwickeln haben wir auch diese Demo als [Angular-Applikation](https://github.com/wdsgithub/cit-at-work-2017/tree/master/frontend) entwickelt. Hierbei orientieren wir uns an den Best-Practices von Google (Google entwickelt das Angular-Framework).

Für die Anbindung des Backends schreiben wir die Components in Typescript. Der Typescript Code transipiliert schlussendlich zu JavaScript. Der große Vorteil von TypeScript ist, dass man typisiert entwickeln kann und somit vorab Typ-Inkompatibilitäten vermeiden kann.

### Das Backend
Wir sind leidenschaftliche Java Entwickler. In der WDS wird Java seit knapp 20 Jahren bereits entwickelt, entsprechend halten wir uns auch bezüglich Java immer am neuesten Stand. Das [Backend](https://github.com/wdsgithub/cit-at-work-2017/tree/master/backend) der Applikation und die tatsächliche Kommunikation mit der Watson-AI erfolgt in Java. 

Die Applikation selbst ist eine Spring-Boot-Webapplikation, welche REST-Services zur Verfügung stellt, die wiederum die Anfragen aus dem Frontend an Watson weiterleiten. 
Die App ist sowohl als eigenständige Spring-Boot App ausführbar (Fat-/ Ueber-JAR), was sich ausgezeichnet für lokale Tests anbietet. Zusätzlich ist es möglich, die Applikation als WAR-File auf einem Servlet-Container zu betreiben. Das Build der Java-App erfolgt über Maven.

### Das Deplyoment
Die Applikation wird auf der BlueMix-Plattform (Cloud) von IBM betrieben. Dazu haben wir eine Tomcat-Instanz als Cloud-Foundry-Anwendung im BlueMix erstellt und die Spring-Boot Applikation als einfaches WAR-File mittels Cloud-Foundry-CLI deployed.
Eine Anleitung, wie das funktioniert, stellt IBM selbst [hier](https://www.ibm.com/developerworks/cloud/library/cl-bluemix-fundamentals-create-and-deploy-a-java-app-to-the-cloud/index.html#step2) zur Verfügung.

## Let's get started...
Zuerst brauchst du einen BlueMix-Account sowie die notwendigen Watson-Services. Hast du keinen, kannst du bei IBM einen Account gratis erstellen.

Einfach git clone https://github.com/wdsgithub/cit-at-work-2017

In den Frontend-Folder wechseln und npm install und ng serve durchführen (Node-Installation ist Voraussetzung!).

Danach in der Java-App den eigenen API-Key eintragen.
Dann die Spring-Boot-Main-Class ausführen und App aufrufen: http://localhost:8080

Viel Spaß & Happy Coding! :sunglasses:

## Maintainer
* Sebastian Schlote, Wüstenrot Datenservice GmbH
* Ivan Jovic, Wüstenrot Datenservice GmbH
