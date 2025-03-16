# Spring cloud gateway

This project is designed to show how spring cloud gateway works with dynamic routing.

![alt text](https://github.com/demirshb/api-gateway-project/blob/main/flow.png?raw=true)

## Run

To run this project, make sure clone the repository to your host machine.

locate the docker-compose.yaml file. run the following command

```bash
  docker-compose up
```

## Usage/Examples
there are 4 microservices.

### user-service

user service has 2 enpoints.
```bash
/api/v1/auth/register
/api/v1/auth/login
```


register request:
```javascript
{
    "firstName" : "john",
    "lastName" : "doe",
    "email" : "john@doe.com",
    "password": "1234"
}
```

login request:
```javascript
{
    "email" : "john@doe.com",
    "password": "1234"
}
```

All request must send over api-gateway.

### order-service

order service has 2 endpoints. when order is created, ORDER_CREATED notification will be sent to notification service over kafka broker

```bash
/api/v1/order/create-order
/api/v1/order/get-order
```

create order request:
```javascript
{
    "orderName" : "TV",
    "orderCount": 5
}
```

get order request:
```javascript
/api/v1/order/get-order?orderId=5
```

### notification-service

notification service will consume events from kafka, it will generate notification.

```bash
/api/v1/notification/get-notification
```

get notification request:
```javascript
/api/v1/notification/get-notification
```
### kafka-broker
kafka broker have 1 topic name order_created. order service will send event when the order is created.

### kafka-ui
http://localhost:8090/


### api-gateway

api-gateway has 4 endpoints
it will be running over port 8000

```javascript
http://localhost:8000/api/v1/auth/register
http://localhost:8000/api/v1/auth/login
http://localhost:8000/api/v1/order/create-order
http://localhost:8000/api/v1/order/get-order
http://localhost:8000/api/v1/notification/get-notification
```

the request will be the same as the services request.

```javascript
/api/v1/order/create-order
/api/v1/order/get-order
```

endpoints are secured. request must have bearer token in the Authorization header.

```javascript
curl --location 'http://localhost:8000/api/v1/order/create-order' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaWhhYkBzaWhhYi5jb20iLCJpYXQiOjE3NDA0MDQwNzQsImV4cCI6MTc0MDQwNzY3NCwiY2xpZW50SWQiOjE0fQ.vLmzopI6_GDditDIKke59U3LnUASRBe0sk0cwK__gFs' \
--data '{
    "orderName" : "TV",
    "orderCount": 5
}'
```


## Contributing

Contributions are always welcome!

See `contributing.md` for ways to get started.

Please adhere to this project's `code of conduct`.
