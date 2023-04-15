# FreeFeser

### Layout

Generated using [DbSchema](https://dbschema.com)

![img](images/Layout.svg)

## Tables

1. [AvailableBot](#availablebot) 2. [BotMessage](#table-botmessage)
   3. [Chatbot](#table-chatbot) 4. [ChatMessage](#table-chatmessage)
   5. [User](#table-user)

### AvailableBot

|             |          |         |
|-------------|----------|---------|
| * &#128273; | id       | serial  |
| * &#11016;  | bot\_id  | integer |
| * &#11016;  | user\_id | integer |
| *           | enabled  | boolean |

##### Indexes

|           |                  |       |
|-----------|------------------|-------|
| &#128273; | pk\_availablebot | ON id |

##### Foreign Keys

|  |    |                                                |
|--|----|------------------------------------------------|
|  | fk | ( bot\_id ) ref [Chatbot](#table-chatbot) (id) |
|  | fk | ( user\_id ) ref [User](#table-user) (id)      |

### Table BotMessage

|             |              |           |
|-------------|--------------|-----------|
| * &#128273; | id           | serial    |
| * &#11016;  | sender\_id   | integer   |
| * &#11016;  | receiver\_id | integer   |
| *           | message      | text      |
| *           | timestamp    | timestamp |

##### Indexes

|           |                |       |
|-----------|----------------|-------|
| &#128273; | pk\_botmessage | ON id |

##### Foreign Keys

|  |    |                                                   |
|--|----|---------------------------------------------------|
|  | fk | ( sender\_id ) ref [Chatbot](#table-chatbot) (id) |
|  | fk | ( receiver\_id ) ref [User](#table-user) (id)     |

### Table ChatMessage

|             |              |           |
|-------------|--------------|-----------|
| * &#128273; | id           | serial    |
| * &#11016;  | sender\_id   | integer   |
| * &#11016;  | receiver\_id | integer   |
| *           | message      | text      |
| *           | timestamp    | timestamp |

##### Indexes

|           |                 |       |
|-----------|-----------------|-------|
| &#128273; | pk\_chatmessage | ON id |

##### Foreign Keys

|  |    |                                               |
|--|----|-----------------------------------------------|
|  | fk | ( sender\_id ) ref [User](#table-user) (id)   |
|  | fk | ( receiver\_id ) ref [User](#table-user) (id) |

### Table Chatbot

|                       |         |             |
|-----------------------|---------|-------------|
| * &#128273;  &#11019; | id      | serial      |
| * &#128269;           | name    | varchar(50) |
| *                     | enabled | boolean     |

##### Indexes

|           |                    |         |
|-----------|--------------------|---------|
| &#128273; | pk\_chatbot        | ON id   |
| &#128269; | unq\_chatbot\_name | ON name |

### Table User

|                       |          |              |
|-----------------------|----------|--------------|
| * &#128273;  &#11019; | id       | serial       |
| * &#128269;           | username | varchar(50)  |
| *                     | password | varchar(255) |

##### Indexes

|           |                     |             |
|-----------|---------------------|-------------|
| &#128273; | pk\_user            | ON id       |
| &#128269; | unq\_user\_username | ON username |
