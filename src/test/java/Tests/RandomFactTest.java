package Tests;

import RequestSender.RequestSender;
import ResponseDto.FactItem;
import RestAssured.RestAssuredSpec;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class RandomFactTest {

    private final static String BASE_URL = "http://catfact.ninja";

    @Test
    public void randomFactTestOne() throws IOException, InterruptedException {

        RequestSender requestSender = new RequestSender();
        var fact = requestSender.get("/fact", FactItem.class);
        var lenText = fact.getFact().length();
        var lenValue = fact.getLength();
        Assert.assertEquals(lenValue.intValue(), lenText);
    }

    @Test
    public void randomFactTestTwo() {
        RestAssuredSpec.setUpSpec(RestAssuredSpec.requestSpec(BASE_URL), RestAssuredSpec.responseSpec());

        var fact = given()
                .when()
                .get("/fact")
                .then().log().all()
                .extract().body().as(FactItem.class);

        var lenText = fact.getFact().length();
        var lenValue = fact.getLength();

        Assert.assertEquals(lenValue.intValue(), lenText);
    }
}