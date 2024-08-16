package API_Automation_Testing;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Swagger_petstore {

    int orderid;
    int petid;@Test(priority=1)
    public void Create_user() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 1031,\n" +
                        "  \"username\": \"swa\",\n" +
                        "  \"firstName\": \"swapnil\",\n" +
                        "  \"lastName\": \"yadav\",\n" +
                        "  \"email\": \"mail\",\n" +
                        "  \"password\": \"123\",\n" +
                        "  \"phone\": \"no\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .post("https://petstore.swagger.io/v2/user");
        response.prettyPrint();
    }

    @Test(priority=2)
    public void Create_user_with_list() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 11,\n" +
                        "    \"username\": \"swapnil01\",\n" +
                        "    \"firstName\": \"Swapnil\",\n" +
                        "    \"lastName\": \"Patil\",\n" +
                        "    \"email\": \"swapnil.patil@example.com\",\n" +
                        "    \"password\": \"password123\",\n" +
                        "    \"phone\": \"123-456-7890\",\n" +
                        "    \"userStatus\": 1\n" +
                        "  }\n" +
                        "]\n")
                .when().
                post("https://petstore.swagger.io/v2/user/createWithList");
        response.prettyPrint();

    }

    @Test(priority=3)
    public void Get_user_by_username() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/swa");
        response.prettyPrint();

    }

    @Test(priority=4)
    public void updating_user() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"username\": \"swa\",\n" +
                        "  \"firstName\": \"swapnil\",\n" +
                        "  \"lastName\": \"yadav\",\n" +
                        "  \"email\": \"mail123\",\n" +
                        "  \"password\": \"123\",\n" +
                        "  \"phone\": \"no\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/user/swa");
        response.prettyPrint();
    }

    @Test(priority=5)
    public void login() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/login?username=123&password=123");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority=6)
    public void logout(){
        Response response = given()
                .header("Accept","*/*")
                .header("Content-Type","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/user/logout");
        response.prettyPrint();
        response.then().statusCode(200);


    }

    @Test(priority=7)
    public void add_pet() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"small\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"small\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority=8)
    public void update_pet() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"small\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 1,\n" +
                        "      \"name\": \"small\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"sold\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority=9)
    public void find_by_status() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test(priority=10)
    public void find_by_pet_id() {
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/"+petid);
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test(priority=11)
    public void updatePet(){
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"Dognation\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }
    @Test(priority = 12)
    public void uploadFile(){
        File file =new File("C:\\Users\\DELL\\Downloads\\dog.png");
        Response res = given()
                .header("Accept","application/json")
                .header("Content-Type"," multipart/form-data")
                .multiPart(file)
                .when()
                .post(" https://petstore.swagger.io/v2/pet/1/uploadImage");
        res.prettyPrint();
        res.then().statusCode(200);
        Assert.assertEquals(res.statusCode(),200);
    }


    @Test(priority=13)
    public void Return_pet_inventory_by_status(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test(priority=14)
    public void Place_order_for_pet(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .header("Accept-Encoding","gzip, deflate, br")
                .body("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"petId\": 1,\n" +
                        "  \"quantity\": 1,\n" +
                        "  \"shipDate\": \"2024-08-09T11:43:40.351Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order   ");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test(priority=15)
    public void find_purchase_order_by_id(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .get("https://petstore.swagger.io/v2/store/order/"+orderid);
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test(priority=16)
    public void Delete_order(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/1");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test(priority=17)
    public void delete_pet(){
        Response response = given()
                .header("Accept", "*/*")
                .header("Content-Type", "application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/pet/1");
        response.prettyPrint();
        response.then().statusCode(200);
    }

}
