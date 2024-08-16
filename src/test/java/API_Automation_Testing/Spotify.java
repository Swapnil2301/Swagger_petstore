package API_Automation_Testing;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Spotify {
    String token ="BQCNRLodclVlb24nDfBftioCutH9wwhAP9QPpW8eTWc_NfAgBnsW4j4Y5S3_J70qGCWOpJSmCz_nxzLD8uT77v6dPd6buLCplOXj8aMDpTfpr1IkzMFZFteDTJydSi0_b_mPgBVa902FFl2wtZNpVS5jX5JsxfGU96B3bWnAPW44cZbdGgCmrw579PiOfaakh81qVt7ePt7Q6kwCO5k7U7hD9TvegrQaygNwBMj5ishwuNC1AGI1EPo03t2AMfsWWyoLiDhjkFmuu4ub2V_BEDjLkICjN7Q4CDFfna5P7rpygUgYRmnycB6R9oBNLRA7iaMIBgLKxQTuAOdc";
    String artist = "";
    String userid = "";
    String current_userid;


    //*******************************************************   User       ***************************************

    @Test(priority =1 )
    public void GetCurrentUsersProfile(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me");
        res.prettyPrint();
        current_userid=res.path("id");
        res.then().statusCode(200);
    }

    @Test(priority =2 )
    public void GetUsersTopItems(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/top/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =3 )
    public void FollowPlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/playlists/37i9dQZF1DX0XUfTFmNBRM/followers");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =4 )
    public void GetUsersProfile(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .pathParams("userid","31nakixlkshe2banry5tx3d2hnoe")
                .when()
                .get("https://api.spotify.com/v1/users/{userid}");
        res.prettyPrint();
        res.then().statusCode(200);



    }

    @Test(priority =5 )
    public void GetFollowedArtists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/following?type=artist");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =6 )
    public void followArtistsOrUsers() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"2CIMQHirSU0MQqyYHq0eOx\", \n" +
                        "        \"57dN52uHvrHOxijzpIgu3E\", \n" +
                        "        \"1vCWHaC5f2uS3yhpwWbIA6\", \n" +
                        "        \"74ASZWbe4lXaubB36ztrGX\"\n" +
                        "    ]\n" +
                        "}\n")
                .when()
                .put("https://api.spotify.com/v1/me/following?type=artist");
        res.prettyPrint();
        res.then().statusCode(204);
    }

    @Test(priority =7 )
    public void CheckIfUserFollowsArtistsorUsers(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=2CIMQHirSU0MQqyYHq0eOx");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =8 )
    public void CheckifCurrentUserFollowsPlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get(" https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers/contains");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =77 )
    public void UnfollowPlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/playlists/37i9dQZF1DX0XUfTFmNBRM/followers");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =76 )
    public void unfollowArtistsOrUsers() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"ids\": [\n" +
                        "        \"2CIMQHirSU0MQqyYHq0eOx\", \n" +
                        "        \"57dN52uHvrHOxijzpIgu3E\", \n" +
                        "        \"1vCWHaC5f2uS3yhpwWbIA6\", \n" +
                        "        \"74ASZWbe4lXaubB36ztrGX\"\n" +
                        "    ]\n" +
                        "}\n")
                .when()
                .delete("https://api.spotify.com/v1/me/following?type=artist");
        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 204);
    }




    //********************************************************************8Search**************************
    @Test(priority =9 )
    public void SearchforItem(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/search?q=remaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis&type=album");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    //*****************************************************************************88Markets***********
    @Test(priority =10 )
    public void GetAvailableMarkets(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/markets");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    //***************************************************************Genre************************
    @Test(priority =11 )
    public void GetAvailableGenreSeeds(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    //**************************************************************Chapter
    @Test(priority =12 )
    public void getChapter() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/episodes/5Xt5DXGzch68nYYamXrNxZ");

        res.prettyPrint();

    }

    @Test(priority =13 )
    public void getSeveralChapters() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/chapters?ids=3NYoZ4P5kw0NzYcGxxd8KE%2C5AVwZVawapvyhJUIx71pdJ%2C2aeoHewKpZ1nLGwrWf5Z7O"); // Valid chapter IDs

        res.prettyPrint();

    }


    //*************************************************************8Categories
    @Test(priority =14 )
    public void GetSeveralBrowseCategories(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories?locale=IN");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =15 )
    public void GetSingleBrowseCategory(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    //*****************************************************************    Artist  *********
    @Test(priority =16 )
    public void GetArtist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test       (priority =17 )//2CIMQHirSU0MQqyYHq0eOx%2C57dN52uHvrHOxijzpIgu3E%2C1vCWHaC5f2uS3yhpwWbIA6
    public void GetSeveralArtists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists?ids=4fEkbug6kZzzJ8eYX6Kbbp");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =18 )
    public void GetArtistsTopTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/top-tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =19 )
    public void GetArtistsAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/albums");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =20 )
    public void GetArtistsRelatedArtistsAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get(" https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/related-artists");
        res.prettyPrint();
        res.then().statusCode(200);
    }


    //**********************************************8    Albums
    @Test(priority =21 )
    public void GetAlbum(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =22 )
    public void GetSeveralAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/albums?ids=4aawyAB9vmqN3uQ7FjRGTy");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =23 )
    public void GetAlbumTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get(" https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =24 )
    public void GetUsersSavedAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/albums");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =25 )
    public void CheckUsersSavedAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/albums/contains?ids=1kDZSmw3mKQeAjcmPTLS3M");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =26 )
    public void GetNewReleases(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/new-releases");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =27 )
    public void SaveAlbumsforCurrentUser(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/albums?ids=1kDZSmw3mKQeAjcmPTLS3M");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =75 )
    public void RemoveUsersSavedAlbums(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/albums?ids=382ObEPsp2rxGrnsizN5TX%2C1A2GTWGtFfWp7KSQTwWOyo%2C2noRn2Aes5aoNVsU6iWThc");
        res.prettyPrint();
        res.then().statusCode(200);
    }



    //*********************************************88   Audiobooks  88************************
    @Test(priority =28 )
    public void GetanAudiobook(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =29 )
    public void GetSeveralAudiobooks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =30 )
    public void GetUsersSavedAudiobooks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =31 )
    public void GetAudiobookChapters(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =32 )
    public void CheckUsersSavedAudiobooks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci%2C1HGw3J3NxZO1TP1BTtVhpZ%2C7iHfbu1YPACw6oZPAFJtqe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 33)
    public void savedAudioBooksForCurrentUser(){

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{\"ids\": [\"18yVqkdbdRvS24c0Ilj2ci\", \"1HGw3J3NxZO1TP1BTtVhpZ\", \"7iHfbu1YPACw6oZPAFJtqe\"]}")
                .when().put("https://api.spotify.com/v1/me/audiobooks");


        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 74)
    public void removeUsersSavedAudiobooks(){

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().delete("https://api.spotify.com/v1/me/audiobooks?ids=18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe");


        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }




    //************************************      Show *********************************
    @Test(priority =34 )
    public void GetSeveralShows(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =35 )
    public void GetShow(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows/0sXfyB6o89daCtf4JkH7iP");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =36 )
    public void GetShowEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows/6h7u9VphsbDw1m0F2eQBIy/episodes");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =37 )
    public void GetUsersSavedShows(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 38)
    public void SaveShowsforCurrentUser(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/shows?ids=5CfCWKI5pZ28U0uOzXkDHe");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =73 )
    public void RemoveUsersSavedShows(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/me/shows?ids=0sXfyB6o89daCtf4JkH7iP");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =39 )
    public void CheckUsersSavedShows(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/shows/contains?ids=5CfCWKI5pZ28U0uOzXkDHe");
        res.prettyPrint();
        res.then().statusCode(200);
    }



    //**********************************************     tracks    ********************************
    @Test(priority =40 )
    public void GetTrack(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/tracks/5zCnGtCl5Ac5zlFHXaZmhy");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =41 )
    public void GetSeveralTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/tracks?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =42 )
    public void GetUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =43 )
    public void CheckUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks/contains?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =44 )
    public void SaveTracksforCurrentUser(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/tracks?ids=382ObEPsp2rxGrnsizN5TX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =45 )
    public void GetSeveralTracksAudioFeatures(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/audio-features?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =46 )
    public void GetTracksAudioFeatures(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/audio-features/11dFghVXANMlKmJXsNCbNl");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =47 )
    public void   GetRecommendations(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =72 )
    public void   RemoveUsersSavedTracks(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/tracks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
        res.prettyPrint();
        res.then().statusCode(200);
    }



    //****************************************8      Playlist   ****************************************
    @Test(priority =48 )
    public void GetPlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =49 )
    public void ChangePlaylistDetails(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "   \n" +
                        "    \"description\": \"Updated playlist description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/playlists/39r0c062m3bEQL6UA87jyT");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =50 )
    public void   GetPlaylistItems(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/tracks");
        res.prettyPrint();
        res.then().statusCode(200);
    }
//    @Test(priority =51 )
//    public void   UpdatePlaylistItems(){
//        Response res = given()
//                .header("Accept","/")
//                .header("Authorization","Bearer " +token)
//                .body("{\n" +
//                        "    \"range_start\": 1,\n" +
//                        "    \"insert_before\": 4,\n" +
//                        "    \"range_length\": 2\n" +
//                        "}\n")
//                .when()
//                .put("https://api.spotify.com/v1/playlists/"+playlistid+"/tracks");
//        res.prettyPrint();
//        res.then().statusCode(200);
//    }
    @Test(priority =52 )
    public void addItemsToPlaylist() {
        String playlistId = "39r0c062m3bEQL6UA87jyT";  // Replace with your playlist ID
        String trackUris = "spotify:track:1IHWl5LamUGEuP4ozKQSXZ,spotify:track:4iV5W9uYEdYUVa79Axb7Rh";

        Response res = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"uris\": [\"" + trackUris.replace(",", "\",\"") + "\"]}")
                .when()
                .post("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");
        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test(priority =53 )
    public void GetCurrentUsersPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =54 )
    public void   GetUsersPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/users/31nakixlkshe2banry5tx3d2hnoe/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =55 )
    public void   CreatePlaylist(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"name\": \"aaaaaaa\",\n" +
                        "    \"description\": \"New playlisaaat description\",\n" +
                        "    \"public\": false\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/yx7i6bw4suh49ddsd8up7rv9u/playlists");
        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test(priority =56 )
    public void   GetFeaturedPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =57 )
    public void   GetCategorysPlaylists(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner/playlists");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =58 )
    public void   GetPlaylistCoverImage(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/images");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =59 )
    public void  AddCustomPlaylistCoverImage(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("/9j/2wCEABoZGSccJz4lJT5CLy8vQkc9Ozs9R0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0cBHCcnMyYzPSYmPUc9Mj1HR0dEREdHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR0dHR//dAAQAAf/uAA5BZG9iZQBkwAAAAAH/wAARCAABAAEDACIAAREBAhEB/8QASwABAQAAAAAAAAAAAAAAAAAAAAYBAQAAAAAAAAAAAAAAAAAAAAAQAQAAAAAAAAAAAAAAAAAAAAARAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwAAARECEQA/AJgAH//Z")
                .when()
                .post("https://api.spotify.com/v1/playlists/39r0c062m3bEQL6UA87jyT/images");
        res.prettyPrint();
    }


    //     *********************************************** Episodes **********************
    @Test (priority =60 )
    public void   GetEpisode(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/episodes/4w2p5chl38Mp35dAubmjzX");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =61 )
    public void   GetSeveralEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/episodes?ids=2EtOmHpIHwfRefGGZbutMT");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =62 )
    public void   GetUsersSavedEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test    (priority =63 )//old
    public void  SaveEpisodesforCurrentUser(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .put("https://api.spotify.com/v1/me/episodes?ids=2EtOmHpIHwfRefGGZbutMT");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test     (priority =64 )//old
    public void   CheckUsersSavedEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .get("https://api.spotify.com/v1/me/episodes/contains?ids=2EtOmHpIHwfRefGGZbutMT");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 71)
    public void  RemoveUsersSavedEpisodes(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .when()
                .delete("https://api.spotify.com/v1/me/episodes?ids=3na8lYc2MmdGtCCz5cs4KM");
        res.prettyPrint();
        res.then().statusCode(200);
    }



    //*******************************   Player
    @Test(priority =65 )
    public void  TransferPlayback(){
        Response res = given()
                .header("Accept","*/*")
                .header("Authorization","Bearer " +token)
                .body("{\n" +
                        "    \"device_ids\": [\n" +
                        "        \"74ASZWbe4lXaubB36ztrGX\"\n" +
                        "    ]\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test(priority =66 )
    public void   GetCurrentlyPlayingTrack() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/currently-playing");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =67 )
    public void   GetAvailableDevices() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/devices");
        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority =68 )
    public void   StartResumePlayback() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"context_uri\": \"spotify:album:5ht7ItJgpBH7W6vJ5BqpPr\",\n" +
                        "    \"offset\": {\n" +
                        "        \"position\": 5\n" +
                        "    },\n" +
                        "    \"position_ms\": 0\n" +
                        "}")
                .when()
                .put("https://api.spotify.com/v1/me/player/play");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test(priority =69 )
    public void   GettheUsersQueue() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/queue");
        res.prettyPrint();
        res.then().statusCode(403);
    }

    @Test(priority =70 )
    public void   GetRecentlyPlayedTracks() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/recently-played");
        res.prettyPrint();
        res.then().statusCode(200);
    }
}
