#customers-app
This is a console app implemented with Swing to connect to customers-api. The app features a button to load csv files from local drive and a send file button to post an array of Customers in Json format to customers-api POST /customers/save endpoint

![image](https://github.com/segovelo/customers-app/assets/44499182/b6dfbb73-022b-4043-87fd-c76792aa6f63)

When request is successful API will return response with 201 code and message in the payload:

![image](https://github.com/segovelo/customers-app/assets/44499182/4e829075-b850-44e3-bfed-f2aed35c8eb7)

In case the insertion into DB fails the customers-app will display response from API with 500 code and error message:

![image](https://github.com/segovelo/customers-app/assets/44499182/af96b722-47fb-4c5e-905f-19a824f3cc2e)

The second feature is the ability to retrieve a customer passing the customerRef in a text field and by clicking 
a get customer button the app will make GET request to API. When a customer is present in DB for the passed customer ref, API will return response with 200 code, message and customer found.

![image](https://github.com/segovelo/customers-app/assets/44499182/6522fdae-00ba-4487-a8b7-350bfc2e45b6)

The customers-app will handle response from API if there is no customer present in DB by displaying 404 code and message accorndingly.

![image](https://github.com/segovelo/customers-app/assets/44499182/5bc95ee5-75a5-4db5-bafd-173c95cea5de)

