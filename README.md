# FreeFeser
**Mitglieder:**
* Philipp Elvin Friedhoff
* Marvin Bendix Kühne
* Florian Reckow

## Systemdokumentation
Es folgt eine Beschreibung der internen Arbeitsweise der Software, einschließlich der Schnittstellen und APIs Beschreibung der Code-Architektur und -Struktur.

### Einleitung
Willkommen zur Systemdokumentation für das Chatbot-Basissystem, das im Rahmen unseres studentischen Projekts für das Modul "Softwaremodeling & Architecture" entwickelt wurde. Diese Dokumentation bietet einen umfassenden Einblick in die Architektur, Funktionsweise und technischen Entscheidungen hinter unserem Chatbot-Basissystem. Sie dient als wertvolle Ressource, um Entwicklern, Projektbeteiligten und anderen Interessenten ein tiefes Verständnis für das System zu vermitteln.

#### Zweck der Dokumentation
Das Hauptziel dieser Dokumentation besteht darin, die Architektur unseres Chatbot-Basissystems detailliert zu beschreiben. Wir zeigen, wie die verschiedenen Module und Komponenten des Systems zusammenwirken, um eine flexible und erweiterbare Umgebung für Chatbots bereitzustellen. Darüber hinaus werden wir die Schnittstellen zwischen den Modulen, die Datenbankanbindung und die Möglichkeiten der Integration von externen Systemen über Chatbots hinweg beleuchten.

#### Kontext des Chatbot-Basissystems
Unser Chatbot-Basissystem entstand aus der Begeisterung für Chatbots und der Vision, unser Produktportfolio um diese innovative Technologie zu erweitern. Als studentisches Team hatten wir die spannende Aufgabe, einen lauffähigen Prototypen für ein Chatbot-Basissystem zu entwickeln. Dieses System bildet die Grundlage für die Ausführung verschiedener Chatbots und soll eine nahtlose Integration neuer Chatbots ermöglichen.

Die Kernfunktionen unseres Chatbot-Basissystems umfassen die Unterstützung der Laufzeitumgebung für Chatbots, die einfache Integration neuer Chatbots und die reibungslose Kommunikation mit externen Systemen über die Chatbot-Schnittstellen.

### Kontextabgrenzung
Die Kontextabgrenzung definiert die Schnittstellen und Kommunikationspartner im Rahmen des Chatbotsystems. Dies umfasst sowohl die Interaktionen mit externen Systemen als auch die Benutzerschnittstellen.

#### Fachlicher Kontext
Der fachliche Kontext des Chatbotsystems umfasst die Interaktionen zwischen dem Nutzer, den externen APIs und der internen Datenbank.

**Externe Kommunikationspartner**  

Die externen Kommunikationspartner des Systems sind:

- REST-APIs (Deepl, Wikipedia, Openweather):
Das System interagiert mit verschiedenen externen APIs, um auf Benutzeranfragen zu reagieren. Es sendet Anfragen an die APIs und verarbeitet die empfangenen Antworten, um die gewünschten Informationen bereitzustellen.

- Datenbank:
Die Datenbank speichert den Chatverlauf und Nutzerinformationen. Das System verwendet Hibernate, um die Kommunikation zwischen der Anwendung und der Datenbank zu verwalten.

- Endnutzer: 
Die Schnittstelle zwischen dem System und dem Endnutzer ist das Terminal. Der Nutzer gibt Anfragen über das Terminal ein, und das System sendet die entsprechenden Anfragen an die APIs.

**Fachliche Schnittstellen**  

Die fachlichen Schnittstellen bestehen aus:

- Nutzereingabe: 
Die Nutzereingabe erfolgt über das Terminal. Der Nutzer gibt Anfragen in Textform ein.

- Systemausgabe:
Das System gibt Antworten und Informationen in Textform über das Terminal aus.

- HTTP-Anfragen und -Antworten:
Das System sendet HTTP-Anfragen an die externen APIs und empfängt HTTP-Antworten, die verarbeitet werden, um die gewünschten Informationen zu extrahieren.

- Datenbankzugriff:
Das System verwendet Hibernate, um auf die H2-Datenbank zuzugreifen. Es speichert Chatnachrichten und Nutzerdaten in der Datenbank.

#### Technischer Kontext
Der technische Kontext des Systems beschreibt die technischen Schnittstellen und Kanäle zwischen den Komponenten.

**Technische Schnittstellen**  

Die technischen Schnittstellen umfassen:

- Terminal:
Das Terminal dient als Schnittstelle zwischen dem Endnutzer und dem System. Es ermöglicht die Eingabe von Anfragen und die Anzeige von Antworten.

- HTTP-Verbindungen:
Das System verwendet HTTP, um Anfragen an die externen APIs zu senden und Antworten zu empfangen. Dies erfolgt über die entsprechenden Endpunkte der APIs.

- Datenbankverbindung:
Das System nutzt Hibernate, um auf die eingebettete H2-Datenbank zuzugreifen. Dies ermöglicht das Speichern und Abrufen von Chatverläufen und Nutzerinformationen.

#### Visualisierung
Das zugehörige Kontextdiagramm:

![Kontextdiagramm](documentation/context_diagram.svg)

#### Zusammenfassung
Die Kontextabgrenzung des Chatbotsystems zeigt die wesentlichen Schnittstellen und Kommunikationspartner auf fachlicher und technischer Ebene. Sie legt die Grundlage für das Verständnis der Interaktionen und bildet die Grundlage für die weiterführende Entwicklung und Integration.

### Lösungsstrategie
Kurzer Überblick über die grundlegenden Entscheidungen und Lösungsansätze, die Entwurf und Implementierung des Systems prägen. Hierzu gehören:

Technologieentscheidungen
Entscheidungen über die Top-Level-Zerlegung des Systems, beispielsweise die Verwendung gesamthaft prägender Entwurfs- oder Architekturmuster,
Entscheidungen zur Erreichung der wichtigsten Qualitätsanforderungen sowie
relevante organisatorische Entscheidungen, beispielsweise für bestimmte Entwicklungsprozesse oder Delegation bestimmter Aufgaben an andere Stakeholder.
Motivation

Diese wichtigen Entscheidungen bilden wesentliche „Eckpfeiler“ der Architektur. Von ihnen hängen viele weitere Entscheidungen oder Implementierungsregeln ab.

Form
Fassen Sie die zentralen Entwurfsentscheidungen kurz zusammen. Motivieren Sie, ausgehend von Aufgabenstellung, Qualitätszielen und Randbedingungen, was Sie entschieden haben und warum Sie so entschieden haben. Vermeiden Sie redundante Beschreibungen und verweisen Sie eher auf weitere Ausführungen in Folgeabschnitten.

### Bausteinsicht
Statische Zerlegung des Systems. Die Abstraktion des Sourcecodes, dargestellt als Hierarchie von “White-Boxes” (die wiederum kleinere Black-Boxes beinhalten), bis zu einem angemessenen Detaillierungsgrad

Motivation
Behalten Sie den Überblick über den Quellcode, indem Sie die statische Struktur des Systems durch Abstraktion verständlich machen. Damit ermöglichen Sie Kommunikation auf abstrakterer Ebene, ohne zu viele Implemenierungsdetails offenlegen zu müssen.

Form
Die Bausteinsicht ist eine hierarchische Sammlung von Blackboxen und Whiteboxen und deren Beschreibungen.

### Laufzeitsichten
Das Verhalten der Bausteine in Form von dynamischen Szenarien, die die wichtigsten Prozesse oder Features abdecken, Interaktionen an kritischen externen Schnittstellen oder “interessante” interne Abläufe und kritische Ausnahme- oder Fehlerfälle.

Motivation
Sie sollten verstehen, wie (Instanzen von) Bausteine(n) Ihres Systems ihre jeweiligen Aufgaben erfüllen und zur Laufzeit miteinander kommunizieren. Nutzen Sie diese Szenarien in der Dokumentation hauptsächlich für eine verständlichere Kommunikation mit denjenigen Stakeholdern, die die statischen Modelle (z.B. Bausteinsicht, Verteilungssicht) weniger verständlich finden.

Form
Für die Beschreibung von Szenarien gibt es zahlreiche Ausdrucksmöglichkeiten. Nutzen Sie beispielsweise:

Nummerierte Schrittfolgen oder Aufzählungen in Umgangssprache
Aktivitäts- oder Flussdiagramme
Sequenzdiagramme
BPMN (Geschäftsprozessmodell und -notation) oder EPKs (Ereignis-Prozessketten)
Zustandsautomaten
…

### Infrastruktursicht
Technische Infrastruktur mit Prozessoren, Systemtopologie, und die Abbildung der Software-Bausteine auf diese Infrastruktur.

Motivation
Software läuft nicht ohne Infrastruktur. Diese zugrundeliegende Infrastruktur beeinflusst Ihr System und/oder querschnittliche Lösungskonzepte, daher müssen Sie diese Infrastruktur kennen.

Form
Das oberste Verteilungsdiagramm könnte bereits in Ihrem technischen Kontext enthalten sein, mit Ihrer Infrastruktur als EINE Blackbox. Jetzt zoomen Sie in diese Infrastruktur mit weiteren Verteilungsdiagrammen hinein:

Die UML stellt mit Verteilungsdiagrammen (Deployment diagrams) eine Diagrammart zur Verfügung, um diese Sicht auszudrücken. Nutzen Sie diese, evtl. auch geschachtelt, wenn Ihre Verteilungsstruktur es verlangt.
Falls Ihre Infrastruktur-Stakeholder andere Diagrammarten bevorzugen, die beispielsweise Prozessoren und Kanäle zeigen, sind diese hier ebenfalls einsetzbar.

### Querschnittliche Konzepte
Übergreifende, generelle Prinzipien und Lösungsansätze, die in vielen Teilen der Architektur einheitlich benutzt werden. Konzepte beziehen sich oft auf mehrere Bausteine. Hier findet man Themen wie Domänenmodelle, Architekturmuster und -stile, Regeln zur Nutzung bestimmter Technologiestacks, etc.

Motivation
Konzepte bilden die Grundlage für konzeptionelle Integrität (Konsistenz, Homogenität) der Architektur und damit eine wesentliche Grundlage für die innere Qualität Ihrer Systeme. Manche dieser Themen lassen sich nur schwer als Baustein in der Architektur unterbringen (z.B. das Thema „Sicherheit“).

Form
Kann vielfältig sein:
Konzeptpapiere mit beliebiger Gliederung,
übergreifende Modelle/Szenarien mit Notationen, die Sie auch in den Architektursichten nutzen,
beispielhafte Implementierung speziell für technische Konzepte,
Verweise auf „übliche“ Nutzung von Standard-Frameworks (beispielsweise die Nutzung von Hibernate als Object/Relational Mapper).

### Schnittstellen
Mit welchen ext. Systemen kommuniziert die Anwendung. Definition und Beschreibung dieser Schnittstellen.

Motivation
Neben den Kontext der Schnittstellen ist die Spezifikation zu robusten Kommunikation entscheidend. Diese Spezifikation wird hier festgelegt.

Form
Tabellenform und/oder Verweis auf Schemata

### Risiken und technische Schulden
Bekannte Risiken und angehäufte technische Schulden. Welche potentiellen Probleme lauern im und um das System? Über welche Schwächen beklagen sich die Entwicklungsteams?

Erläutern Sie hier die Verletzung bekannter Prinzipien und weiteres Themen aus der Vorlesung.

### Erweiterungen
Anweisungen zum Erstellen von Erweiterungen oder zur Anpassung der Software
Als Erweiterung ist hier ein neuer Chatbot zu sehen.

### Fehlerbehebung
Eine Liste der häufigsten Fehler, die während der Verwendung der Software auftreten können, und wie man sie behebt.

### Installationsanleitung
Eine Schritt-für-Schritt-Anleitung zur Installation der Software
Anforderungen an die Systemumgebung
Voraussetzungen für die Installation

### Konfiguration
Eine Beschreibung der Konfigurationsoptionen der Software
Wie man die Konfiguration ändert und welche Auswirkungen dies auf die Funktionalität hat

## Bot-Dokumentation (Erweiterungen)
Je Bot soll eine eigene, kleine Dokumentation über den Aufbau/Struktur des Bot-Systems dargestellt werden.

Jede Bot-Dokumentation folgt dabei einer kleinen Systemdokumentation mit folgenden Punkten:

Kontextabgrenzung
Lösungsstrategie
Bausteinsicht
Laufzeitsicht
Schnittstellen
Je nach Ansatz sind noch weitere Punkte wichtig.

### TranslationBot

### WeatherBot

### WikiBot

## weitere Schritte
1. OpenWeather-API für Forecast implementieren/einbinden
2. Unit-/Integration-Tests schreiben
