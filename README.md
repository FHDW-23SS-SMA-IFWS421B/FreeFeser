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
Grenzt das System, an dem Sie arbeiten, von externen Kommunikationspartnern (Nachbarsystemen und Benutzern) ab. Zeigt die externen Schnittstellen aus Sicht des Business (immer) und aus Sicht der Technologie (optional)

Motivation
Die fachlichen und technischen Schnittstellen zur Kommunikation gehören zu den kritischsten Aspekten eines Systems. Stellen Sie sicher, dass Sie diese komplett verstanden haben.

Form
Verschiedene Optionen:
Diverse Kontextdiagramme
Listen von Kommunikationsbeziehungen mit deren Schnittstellen
Fachlichter Kontext
Festlegung aller Kommunikationsbeziehungen (Nutzer, IT-Systeme, …) mit Erklärung der fachlichen Ein- und Ausgabedaten oder Schnittstellen. Zusätzlich (bei Bedarf) fachliche Datenformate oder Protokolle der Kommunikation mit den Nachbarsystemen.

Motivation
Alle Beteiligten müssen verstehen, welche fachlichen Informationen mit der Umwelt ausgetauscht werdenw.

Form
Alle Diagrammarten, die das System als Blackbox darstellen und die fachlichen Schnittstellen zu den Nachbarsystemen beschreiben. Alternativ oder ergänzend können Sie eine Tabelle verwenden. Der Titel gibt den Namen Ihres Systems wieder; die drei Spalten sind: Kommunikationsbeziehung, Eingabe, Ausgabe.

Technischer Kontext
Technische Schnittstellen (Kanäle, Übertragungsmedien) zwischen dem System und seiner Umwelt. Zusätzlich eine Erklärung (mapping), welche fachlichen Ein- und Ausgaben über welche technischen Kanäle fließen.

Motivation
Viele Stakeholder treffen Architekturentscheidungen auf Basis der technischen Schnittstellen des Systems zu seinem Kontext. Insbesondere bei der Entwicklung von Infrastruktur oder Hardware sind diese technischen Schnittstellen durchaus entscheidend.

Form
Beispielsweise UML Deployment-Diagramme mit den Kanälen zu Nachbarsystemen, begleitet von einer Tabelle, die Kanäle auf Ein-/Ausgaben abbildet.

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
