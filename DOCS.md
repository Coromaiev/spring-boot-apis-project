# GameController API Documentation

## Base URL
```
/entities/games
```

---

### **Get Game by ID**
#### `GET /{id}`
Retrieve details of a game by its ID.

**Path Parameters:**
- `id` (Long): The ID of the game.

**Response:**
- `200 OK`: Returns a `GameDto` object with the game's details.
- `404 Not Found`: Game not found.

---

### **Get All Games**
#### `GET /all`
Retrieve a list of all games.

**Response:**
- `200 OK`: Returns a list of `GameDto` objects.
- `204 No Content`: No games available.

---

### **Get All Minimal Games**
#### `GET /all/minimal`
Retrieve a minimal view of all games.

**Response:**
- `200 OK`: Returns a list of `GameMinimalDto` objects.
- `204 No Content`: No games available.

---

### **Get All Detailed Games**
#### `GET /all/details`
Retrieve detailed information for all games.

**Response:**
- `200 OK`: Returns a list of `GameDetailsDto` objects.
- `204 No Content`: No detailed games available.

---

### **Get Game Details by ID**
#### `GET /{id}/details`
Retrieve detailed information about a specific game by its ID.

**Path Parameters:**
- `id` (Long): The ID of the game.

**Response:**
- `200 OK`: Returns a `GameDetailsDto` object.
- `404 Not Found`: Game not found.

---

### **Get Games by Host ID**
#### `GET /host/{id}`
Retrieve all games hosted by a specific host.

**Path Parameters:**
- `id` (Long): The ID of the host.

**Response:**
- `200 OK`: Returns a list of `GameMinimalDto` objects for the specified host.
- `204 No Content`: No games found for the host.

---

### **Get Games by Type**
#### `GET /type/{gameType}`
Retrieve all games of a specific type.

**Path Parameters:**
- `gameType` (GameType): The type of the game.

**Response:**
- `200 OK`: Returns a list of `GameMinimalDto` objects for the specified type.
- `204 No Content`: No games of the specified type available.

---

### **Create New Game**
#### `POST /new`
Create a new game.

**Request Body:**
- `GameCreateDto`: Contains the details of the new game (e.g., `date`, `gameType`).

**Response:**
- `201 Created`: Returns the created `GameDto` object.
- `400 Bad Request`: Invalid request body.
- `500 Internal Server Error`: Server error during creation.

---

### **Update Game**
#### `PUT /update`
Update an existing game.

**Request Body:**
- `GameUpdateDto`: Contains the updated game details.

**Response:**
- `200 OK`: Returns the updated `GameDto` object.
- `400 Bad Request`: Invalid update data or update failed.

---

### **Delete Game by ID**
#### `DELETE /{id}`
Delete a game by its ID.

**Path Parameters:**
- `id` (Long): The ID of the game to delete.

**Response:**
- `204 No Content`: Game successfully deleted.
- `404 Not Found`: Game not found.

---

### **Delete Games by Host ID**
#### `DELETE /host/{id}`
Delete all games associated with a specific host.

**Path Parameters:**
- `id` (Long): The ID of the host.

**Response:**
- `204 No Content`: Games successfully deleted.
- `410 Gone`: No games found for the host.

---

# ParticipationController API Documentation

## Base URL
```
/entities/participations
```

---

### **Get Participation by ID**
#### `GET /{participationId}`
Retrieve details of a participation by its ID.

**Path Parameters:**
- `participationId` (Long): The ID of the participation.

**Response:**
- `200 OK`: Returns a `ParticipationDto` object with the participation details.
- `404 Not Found`: Participation not found.

---

### **Get Participation Details by ID**
#### `GET /{participationId}/details`
Retrieve detailed information about a participation by its ID.

**Path Parameters:**
- `participationId` (Long): The ID of the participation.

**Response:**
- `200 OK`: Returns a `ParticipationDetailsDto` object.
- `404 Not Found`: Participation details not found.

---

### **Get Participations by Player ID**
#### `GET /player/{playerId}`
Retrieve all participations of a specific player.

**Path Parameters:**
- `playerId` (Long): The ID of the player.

**Response:**
- `200 OK`: Returns a list of `ParticipationMinimalDto` objects.
- `204 No Content`: No participations found for the player.

---

### **Get Participations by Game ID**
#### `GET /game/{gameId}`
Retrieve all participations for a specific game.

**Path Parameters:**
- `gameId` (Long): The ID of the game.

**Response:**
- `200 OK`: Returns a list of `ParticipationMinimalDto` objects.
- `404 Not Found`: No participations found for the game.

---

### **Get Participation by Player ID and Game ID**
#### `GET /player/{playerId}/game/{gameId}`
Retrieve a specific participation for a player in a particular game.

**Path Parameters:**
- `playerId` (Long): The ID of the player.
- `gameId` (Long): The ID of the game.

**Response:**
- `200 OK`: Returns a `ParticipationDto` object.
- `404 Not Found`: Participation not found.

---

### **Get Participations by Player ID and Victory Status**
#### `GET /player/{playerId}/{victory}`
Retrieve participations of a player filtered by victory status.

**Path Parameters:**
- `playerId` (Long): The ID of the player.
- `victory` (boolean): Whether the participation resulted in victory.

**Response:**
- `200 OK`: Returns a list of `ParticipationMinimalDto` objects.
- `404 Not Found`: No matching participations found.

---

### **Update Participation**
#### `PUT /update`
Update an existing participation.

**Request Body:**
- `ParticipationUpdateDto`: Contains the updated participation details.

**Response:**
- `200 OK`: Returns the updated `ParticipationDto` object.
- `400 Bad Request`: Invalid update data or update failed.

---

### **Create New Participation**
#### `POST /new`
Create a new participation.

**Request Body:**
- `ParticipationCreateDto`: Contains the details of the new participation.

**Response:**
- `201 Created`: Returns the created `ParticipationDto` object.
- `400 Bad Request`: Invalid request body.

---

### **Delete Participation by ID**
#### `DELETE /delete/{id}`
Delete a participation by its ID.

**Path Parameters:**
- `id` (Long): The ID of the participation to delete.

**Response:**
- `204 No Content`: Participation successfully deleted.
- `410 Gone`: Participation already deleted or not found.

---

### **Delete Participation by Player ID and Game ID**
#### `DELETE /delete/{playerId}/{gameId}`
Delete a participation by player ID and game ID.

**Path Parameters:**
- `playerId` (Long): The ID of the player.
- `gameId` (Long): The ID of the game.

**Response:**
- `204 No Content`: Participation successfully deleted.
- `410 Gone`: Participation already deleted or not found.

---

# PlayerController API Documentation

## Base URL
```
/entities/players
```

---

### **Get All Players**
#### `GET /all`
Retrieve a list of all players.

**Response:**
- `200 OK`: Returns a list of `PlayerMinimalDto` objects.
- `204 No Content`: No players found.

---

### **Get Player by ID**
#### `GET /{id}`
Retrieve details of a player by their ID.

**Path Parameters:**
- `id` (Long): The ID of the player.

**Response:**
- `200 OK`: Returns a `PlayerDto` object with the player's details.
- `404 Not Found`: Player not found.

---

### **Get Player by Pseudonym**
#### `GET /pseudo/{pseudonym}`
Retrieve details of a player by their pseudonym.

**Path Parameters:**
- `pseudonym` (String): The pseudonym of the player.

**Response:**
- `200 OK`: Returns a `PlayerDto` object with the player's details.
- `404 Not Found`: Player not found.

---

### **Get Player by Email**
#### `GET /email/{email}`
Retrieve details of a player by their email.

**Path Parameters:**
- `email` (String): The email address of the player.

**Response:**
- `200 OK`: Returns a `PlayerDto` object with the player's details.
- `404 Not Found`: Player not found.

---

### **Create New Player**
#### `POST /new`
Create a new player.

**Request Body:**
- `PlayerCreateDto`: Contains the details of the new player.

**Response:**
- `201 Created`: Returns the created `PlayerDto` object.
- `400 Bad Request`: Invalid request body or creation failed.

---

### **Delete Player by ID**
#### `DELETE /{id}`
Delete a player by their ID.

**Path Parameters:**
- `id` (Long): The ID of the player to delete.

**Response:**
- `204 No Content`: Player successfully deleted.
- `410 Gone`: Player already deleted or not found.

---

### **Update Player**
#### `PUT /update`
Update an existing player's details.

**Request Body:**
- `PlayerUpdateDto`: Contains the updated player details.

**Additional Functionality:**
- The total points of the player are recalculated based on their participations retrieved from the Games Management service.
- **External API Call:** Retrieves participations via the endpoint `GET http://localhost:7000/entities/participations/player/{playerId}`.

**Response:**
- `200 OK`: Returns the updated `PlayerDto` object.
- `404 Not Found`: Player not found or update failed.

# FriendController API Documentation

## Base URL
```
/entities/friends
```

---

### **Get Friend by ID**
#### `GET /{id}`
Retrieve details of a friendship by its ID.

**Path Parameters:**
- `id` (Long): The ID of the friendship.

**Response:**
- `200 OK`: Returns a `FriendDto` object with the friendship details.
- `404 Not Found`: Friendship not found.

---

### **Get Friends by Player ID**
#### `GET /player/{id}`
Retrieve all friends of a specific player.

**Path Parameters:**
- `id` (Long): The ID of the player.

**Response:**
- `200 OK`: Returns a list of `FriendMinimalDto` objects.
- `404 Not Found`: No friends found for the player.

---

### **Delete Friend by Player ID and Friend ID**
#### `DELETE /player/{playerId}/friend/{friendId}`
Delete a friendship by player ID and friend ID.

**Path Parameters:**
- `playerId` (Long): The ID of the player.
- `friendId` (Long): The ID of the friend.

**Response:**
- `204 No Content`: Friendship successfully deleted.
- `410 Gone`: Friendship already deleted or not found.

---

### **Delete Friend by ID**
#### `DELETE /{id}`
Delete a friendship by its ID.

**Path Parameters:**
- `id` (Long): The ID of the friendship to delete.

**Response:**
- `204 No Content`: Friendship successfully deleted.
- `410 Gone`: Friendship already deleted or not found.

---

### **Create New Friendship**
#### `POST /new`
Create a new friendship.

**Request Body:**
- `FriendCreateDto`: Contains the details of the new friendship.

**Response:**
- `201 Created`: Returns the created `FriendDto` object.
- `400 Bad Request`: Invalid request body or creation failed.

