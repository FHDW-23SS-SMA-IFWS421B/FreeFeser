## How to proceed?
### Step-by-Step

1. Create Java classes:
   - Create Java classes to represent entities such as User, ChatMessage, and ChatBot.
   - Annotate the classes with Hibernate annotations to map them to database tables.
   - Define relationships between entities, like the one-to-many relationship between User and ChatMessage.

2. Implement the user interface:
   - Create a console-based user interface to accept text commands and display output.
   - Use Java's input/output classes to handle user input and display output in the console.

3. Implement the persistence layer:
   - Configure Hibernate to connect to the database.
   - Create DAO (Data Access Object) classes to perform CRUD operations on the entities.
   - Use Hibernate APIs, like SessionFactory and Session, to interact with the database.
   - Implement methods to save chat messages, retrieve chat history for a user, and other necessary operations.

4. Implement the bot functionality:
   - Create an interface or base class for chatbots.
   - Implement specific chatbots by extending the base class or implementing the interface.
   - Define methods to handle different commands or questions and provide appropriate responses.
   - Consider implementing a BotManager class to manage the available bots and their activation status.

5. Handle user authentication and authorization:
   - Implement a login system where users can authenticate using their username and password.
   - Maintain user sessions and associate chat messages with the corresponding user.
   - Ensure that only authenticated users can access the chat functionality.

6. Load and activate bots dynamically:
   - Provide an API or interface for developers to add new chatbots to the system.
   - Allow developers to compile and deploy their bots independently.
   - Implement a mechanism to load the available bots dynamically during system startup.
   - Allow users to view the list of available bots and activate or deactivate them as desired.

7. Store chat messages in the database:
   - Utilize Hibernate's persistence capabilities to save chat messages in the database.
   - Define appropriate mappings and relationships between entities to store the chat history.
   - Consider implementing a mechanism to limit the number of chat messages retrieved per user to a maximum of 100.

8. Test the system:
   - Write unit tests to verify the functionality of different components, including user authentication, chat message storage, and bot responses.
   - Test the system with various scenarios and edge cases to ensure its robustness.
  
## ToDo's
1. ~~Username muss unique~~  FLO
2. Console anpassen ohne Debugs etc PHIL
3. ~~abstrakter ConsolePrinter PHIL~~
4. bessere Messages PHIL
5. ~~GSON und HTTP Client abstrahieren FLO~~
6. TextAnalyzer ausbauen und Albo erklären wie geht diese, in chatbotmanager+translationcommand anwenden FLO
7. ~~Command stuff weiter ausbauen PHIL~~
8. Bots implementieren
9. Active command PHIL
10. Config PHIL