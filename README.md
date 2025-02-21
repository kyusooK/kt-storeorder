# 

## Model
www.msaez.io/#/123912988/storming/ktstoreorder

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- orderapp
- user
- marketing
- store


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- orderapp
```
 http :8088/orders id="id"requestInfo="requestInfo"price="price"OrderStatus = "ORDERPLACED"paymentId="paymentId"paymentStatus="paymentStatus"UserId := '{"id": 0}'StroeId := '{"id": 0}'orderInfo="orderInfo"
```
- user
```
 http :8088/users id="id"userName="userName"phoneNumber="phoneNumber"email="email"coupon="coupon"
```
- marketing
```
 http :8088/promotions id="id"promotionName="promotionName"content="content"startAt="startAt"endAt="endAt"StoreId := '{"id": 0}'
 http :8088/coupons id="id"couponName="couponName"content="content"
```
- store
```
 http :8088/menus id="id"menuName="menuName"price="price"menuInfo="menuInfo"ingredients="ingredients"
 http :8088/stores id="id"storeInfo="storeInfo"healthCertificate=" healthCertificate"storeApproval="storeApproval"orderInfo="orderInfo"requestInfo="requestInfo"FoodStatus = "ACCEPTED"reviewId="reviewId"MenuId := '{"id": 0}'OrderId := '{"id": 0}'marketInfo="marketInfo"storeLocation="storeLocation"
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
