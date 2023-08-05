1. ~~Sollen wir observables verwenden?~~ - nur, wenn es Sinn ergibt
2. ~~Command generisch args?~~ - muss nicht, da console zu stark abstrahiert aber zB benutzen wenn Console farbigen Output haben soll also gut
3. ~~HttpClient abstrahieren?~~  - http client abstrahieren heißt zB austauschbar durch web socket
4. ~~GSON abstrahieren?~~ - gut so wie es ist
5. ~~Factory für jede Klasse, aber kein Builder da unnötige, außer bei bots?~~
6. ~~Helper function static für text analyzer?~~ - statische Methoden erlaubt als helper -- maybe nlp properties in helper class packen wenn gleich
7. ~~Bot register in main?~~ - kein new in der Main höchstens für neue Bots -> über Factories abstrahieren zB Registrierung damit Code strukturierter