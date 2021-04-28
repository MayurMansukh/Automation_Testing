package com.bridgelabz.RestAssuredSpotify;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
    String token = " ";
    String user_id = " ";

    @BeforeTest // set the Token
    public void setup() {
        token = "Bearer BQCRUBHMrUNxSio8zOENg_aFkoLfi-LC7KdqvFHmiyvRgWdtsNTbpcFmo9Me9uWl-l-PsVNn6HA6Mr39KeNBSTT-DzMj81GNoX1hB_B4g46TNJS5W9RoemdmdnxHoP6Stg3lhggWNGYXFbnvE4-o46pN5E4lqQUvjHKxuujdGPV1IM455Lti0cewegTBC_cUV9edSwE1k3r7ZmV5GigVVgcMccjq06awy2rO9GTbGIFxkCMDHEzuc3GiB5Cs2mxa3SwCtUxqecXBYosrFLExCrfy8FxQYDbAd9UYhyVX";
    }

    @Test(priority = 1)
    public void get_current_user_profile() { // get current user profile using get request
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/me");

        response.prettyPrint();
        int statusCode=response.getStatusCode();
        user_id = response.path("id");
        Assert.assertEquals(200,statusCode);
        Assert.assertEquals("bu9yalxad2wzqjhm294upqeb0",user_id);


    }

    @Test(priority = 2)
    public void get_User_Profile2() { // get user profile using get request with hardCode UserId
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/users/"+user_id+"/");

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
                .header("Authorization", token)
                .body("{\"name\":\"DDLJ SONGS\",\"description\":\"OLD MOVIE SONGS\",\"public\":\"false\"}")
                .when()
                .post("https://api.spotify.com/v1/users/"+user_id+"/playlists");

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
                .header("Authorization", token)
                .when()
                .post("https://api.spotify.com/v1/playlists/0ibWqOyk7D7p8ZLB924idC/tracks?uris=spotify:track:018eOid2aGaPdxon7T6GsC");

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(201,statusCode);


    }
}