package com.bridgelabz.RestAssuredSpotify;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
    String Token = "";

    @BeforeTest // set the Token
    public void setup() {
        Token = "Bearer BQB7zsnbxd-qS5ZLWw5-VT_v90-CvKr-gSKcbg8xvdJe59HCmPcciVPvrqGFwKt4LA6RqGGePu-W2Kj7sBohVe4HUwpcVtgJkjRAKtUKzHxxWS1Ipq_UD30WxeiUB3i-II-_g7TQNuzW8hkS86NrqJpCRHu9Q4cu6RG8wHsFI_PB9k-5B5EAB8ugDYW1ikcZjB2K4Smw1BDanO5g_ZekIWvDwfDk7Mo-mrfwr8dnZ2vSCYVP5beAF0r2ek_Mon-ns7JUrP4U8VSDYahPFZ-RyUjsnLq-v4N-nfImTrkC";
    }

    @Test
    public void get_current_user_profile() { // get current user profile using get request
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", Token)
                .when()
                .get("https://api.spotify.com/v1/me");

        response.prettyPrint();
        int statusCode=response.getStatusCode();
        String userId = response.path("id");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("bu9yalxad2wzqjhm294upqeb0",userId);
    }

    @Test
    public void get_User_Profile2() { // get user profile using get request with hardCode UserId
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", Token)
                .when()
                .get("https://api.spotify.com/v1/users/bu9yalxad2wzqjhm294upqeb0");

        response.prettyPrint();

        int statusCode = response.getStatusCode();
        String userId = response.path("id");
        String name = response.path("display_name");

        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("bu9yalxad2wzqjhm294upqeb0",userId);
        Assert.assertEquals("Mayur",name);
    }

    @Test
    public void createPlayList() { //create playList using Post request

        JSONObject request = new JSONObject();
        request.put("name", "MyPlayList");
        request.put("description", "Bollywood Songs");
        request.put("public", "true");

        Response response = given()
                //.body(request.toJSONString())
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", Token)
                .body("{\"name\":\"DDLJ SONGS\",\"description\":\"OLD MOVIE SONGS\",\"public\":\"false\"}")
                .when()
                .post("https://api.spotify.com/v1/users/bu9yalxad2wzqjhm294upqeb0/playlists");

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        String name = response.path("name");
        String type = response.path("type");

        Assert.assertEquals(201,statusCode);
        Assert.assertEquals("DDLJ SONGS",name);
        Assert.assertEquals("playlist",type);
    }

    @Test
    public void add_items_in_playlist() {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", Token)
                .when()
                .post("https://api.spotify.com/v1/playlists/0ibWqOyk7D7p8ZLB924idC/tracks?uris=spotify:track:018eOid2aGaPdxon7T6GsC");

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201,statusCode);


    }
}