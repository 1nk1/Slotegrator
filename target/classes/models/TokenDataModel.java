package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenDataModel {
    @SerializedName("token_type")
    @Expose
    public String token_type;

    @SerializedName("expires_in")
    @Expose
    public int expires_in;

    @SerializedName("access_token")
    @Expose
    public String access_token;

    @SerializedName("refresh_token")
    @Expose
    public String refresh_token;
}
