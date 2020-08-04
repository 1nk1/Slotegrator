package APITests;

import io.restassured.http.ContentType;
import libs.ConfigProperties;
import libs.Generate;
import models.PlayerModel;
import models.TokenDataModel;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    public static ConfigProperties confProp = ConfigFactory.create(ConfigProperties.class);

    public TokenDataModel getTokenData() {
        String jsonBody = "" +
                "{\n \"grant_type\":\"client_credentials\"," +
                " \n \"scope\":\"guest:default\"\n}";

        return given().auth().preemptive().basic("front_2d6b0a8391742f5d789d7d915755e09e", "")
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(confProp.TEST_API() + "v2/oauth2/token")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody()
                .as(TokenDataModel.class);
    }

    public PlayerModel getPlayerData(TokenDataModel token) {

        String jsonBody = "{\n" +
                "\"username\": \"" + Generate.userName() + "\",\n" +
                "\"password_change\": \"amFuZWRvZTEyMw==\", " +
                "\"password_repeat\": \"amFuZWRvZTEyMw==\", " +
                "\"email\": \"" + Generate.email() + "\",\n" +
                "\"name\": \"" + Generate.name() + "\",\n" +
                "\"surname\": \"" + Generate.surName() + "\"\n" +
                "}";

        return given()
                .headers(
                        "Authorization",
                        "Bearer " + token.access_token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(confProp.TEST_API() + "v2/players")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .as(PlayerModel.class);
    }

    public TokenDataModel authorize(PlayerModel playerModel) {

        String jsonBody = "{\n" +
                "\"grant_type\":\"password\",\n" +
                " \"username\":\"" + playerModel.username + "\",\n" +
                "\"password\":\""+ playerModel.password + "\"\n" +
                "}";

       return given().auth().preemptive().basic("front_2d6b0a8391742f5d789d7d915755e09e", "")
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(confProp.TEST_API() + "v2/oauth2/token")
                .then()
                .extract()
                .body()
                .as(TokenDataModel.class);
    }
}
