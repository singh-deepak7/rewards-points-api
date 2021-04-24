# rewards-points-api

Fetch Rewards Coding Exercise

## Requirements:

#### 1. Java JDK 8
#### 2. Maven 
	Link to download: http://maven.apache.org/download.cgi
	Install instructions: http://maven.apache.org/install.html
#### 3. STS (Optional)
	Link to downlaod: https://spring.io/tools


## Project Installation:

### Clone the project from repository
	1. Open Powershell or Terminal
	2. git clone 
	3. cd rewards-point-api
	4. mvn clean install

### Run the compiled project
	1. Open Powershell or Terminal
	2. cd rewards-point-api
	3. mvn spring-boot:run

### Can also import in STS (Spring Tool Suite) and right click on projecta nd select Run As -> Spring boot App.


## API Documentation: 

Once app is app Swagger can be access via link - http://localhost:8080/rewards-points-api

## Allowed Endpoints:

### 1. Get all transactions for all users:

#### Example:

#### Endpoint: 
	HTTP method GET -  http://localhost:8080/rewards-points-api/v1/all

#### Response:

```
  {
    "customers": [
        {
            "id": 1,
            "rewards": [
                {
                    "payer": "Amazon",
                    "points": 2000,
                    "timestamp": "2021-04-23T19:06:06.923+00:00"
                },
                {
                    "payer": "eBay",
                    "points": 200,
                    "timestamp": "2021-04-23T19:06:06.923+00:00"
                }
            ]
        }
    ]
}
```

#### Response Status:
	1. Successfull request : 200 OK 

### 2. Add points to user's account.

#### Example:

#### Endpoint: 
	HTTP method POST - http://localhost:8080/rewards-points-api/v1/balance/2

#### Request: 

Add below request in sequesnce: 

```
{ "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }
```
```
{ "payer": "UNILEVER", "points": 200, "timestamp": "2020-10-31T11:00:00Z" }
```
```
{ "payer": "DANNON", "points": -200, "timestamp": "2020-10-31T15:00:00Z" }
```
```
{ "payer": "MILLER COORS", "points": 10000, "timestamp": "2020-11-01T14:00:00Z" }
```
```
{ "payer": "DANNON", "points": 300, "timestamp": "2020-10-31T10:00:00Z" }
```

#### Response: 
```
Rewards added
```

#### Response Status:

	1. Successfull request: 201 CREATED
		
### 3. Spend points from a user

#### Example:

#### Endpoint: 
	HTTP method DELETE -  http://localhost:8080/rewards-points-api/v1/spend/2

#### Request: 
```
{ "points": 5000}
```

#### Response: 
```
{
    "UNILEVER": -200,
    "MILLER COORS": -4700,
    "DANNON": -100
}
```

#### Response Status:

	1. Successfull request: 200 OK

### 4. Show user's balance

#### Example:

#### Endpoint: 
	HTTP method GET - http://localhost:8080/rewards-points-api/v1/balance/2

#### Response:

```
{
    "UNILEVER": 0,
    "MILLER COORS": 5300,
    "DANNON": 1000
}
```

#### Response Status:

	1. Successfull request: 200 OK


### Erros:

#### User not found:

```
{
    "status": 404,
    "error": "CUSTOMER_NOT_FOUND",
    "message": "Customer not found"
}
```

#### Response Status:

	404 Not Found
	
#### Bad request:

```
{
    "status": 400,
    "error": "INVALID_INPUT",
    "message": "Invalid input"
}
```

#### Response Status:

	400 Bad Request

#### Server Error:

```
{
    "status": 500,
    "error": "INTERNAL_SERVER_ERROR",
    "message": "Something went wrong"
}
```

#### Response Status:

	500 Internal Server Error
