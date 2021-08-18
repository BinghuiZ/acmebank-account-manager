
# acmebank's Account-Manager

A program to demo APIs to get user's balance & transfer money to other users.


## Run Locally


Unzip file Go to the project directory

```bash
  cd ~/acmebank-account-manager

```

Start the server

```bash
  java -jar ./account-manager-0.0.1-SNAPSHOT.jar
```

  
## Demo

After starting the program,

go to http://localhost:8080/swagger-ui.html 

  
## API Reference

#### Get all user's balance

```http
  GET /list
```

#### Get item

```http
  GET /${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. 12345678 / 88888888 |

#### Transfer Money to other

```http
  POST ​/transfer
```

| Body Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `from`      | `int` | **Required**. 12345678 / 88888888 |
| `to`      | `int` | **Required**. 12345678 / 88888888 |
| `amount`      | `Dboule` | **Required**. the transfer amount |



  