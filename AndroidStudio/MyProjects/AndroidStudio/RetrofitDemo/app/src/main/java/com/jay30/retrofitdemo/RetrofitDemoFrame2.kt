package com.jay30.retrofitdemo

import retrofit2.http.GET;
import retrofit2.http.Path;


public final class SimpleService {
    public static final String API_URL = "https://api.github.com";

    public static
    class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions)
        {
            this.login = login;
            this.contributions = contributions;
        }
    }


}

interface GitHub {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);
}