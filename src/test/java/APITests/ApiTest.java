package APITests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PlayerModel;
import models.TokenDataModel;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class ApiTest extends BaseApiTest {

    @Test
    public void shouldBeReturnToken() {

        TokenDataModel tokenData = getTokenData();

        Assert.assertEquals(5, Integer.toString(tokenData.expires_in).length());
        Assert.assertNotEquals(tokenData.access_token, null);
        Assert.assertNotEquals(tokenData.token_type, null);
    }

    @Test
    public void registrationNewPlayer() {

        TokenDataModel token = getTokenData();
        PlayerModel playerModel = getPlayerData(token);

        Assert.assertNotEquals(playerModel.id, 0);
        Assert.assertNotEquals(playerModel.name, null);
        Assert.assertNotEquals(playerModel.surname, null);
        Assert.assertNotEquals(playerModel.email, null);
        Assert.assertNotEquals(playerModel.password, null);
    }

    @Test
    public void userShouldBeAuthorize() {

        TokenDataModel token = getTokenData();
        PlayerModel playerModel = getPlayerData(token);

        authorize(playerModel);
   }

    @Test
    public void userShouldReceiveOwnData() {

        TokenDataModel token = getTokenData();
        PlayerModel playerModel = getPlayerData(token);
        TokenDataModel freshToken = authorize(playerModel);

       Response response = given().headers(
                "Authorization",
                "Bearer " + freshToken.access_token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .when()
                .get(confProp.TEST_API() + "v2/players/" + playerModel.id);

        Assert.assertEquals(200, response.then().extract().statusCode());
   }

   @Test
    public void whenUserRequestAnotherPlayersDataResponseShouldBe404() {

        TokenDataModel tokenFirstPlayer = getTokenData();
        PlayerModel playerFirstModel = getPlayerData(tokenFirstPlayer);

        authorize(playerFirstModel);

        TokenDataModel tokenSecondPlayer = getTokenData();
        PlayerModel playerSecondModel = getPlayerData(tokenSecondPlayer);

        TokenDataModel freshToken = authorize(playerSecondModel);


        Response response = given().headers(
               "Authorization",
               "Bearer " + freshToken.access_token,
               "Content-Type",
               ContentType.JSON,
               "Accept",
               ContentType.JSON)
               .when()
               .get(confProp.TEST_API() + "v2/players/" + playerFirstModel.id);

       Assert.assertEquals(404, response.then().extract().statusCode());
   }
}