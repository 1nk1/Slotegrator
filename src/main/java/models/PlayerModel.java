package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerModel {
    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("country_id")
    @Expose
    public int country_id;

    @SerializedName("timezone_id")
    @Expose
    public String timezone_id;

    @SerializedName("username")
    @Expose
    public String username;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("surname")
    @Expose
    public String surname;

    @SerializedName("gender")
    @Expose
    public String gender;

    @SerializedName("phone_number")
    @Expose
    public String phone_number;

    @SerializedName("birthdate")
    @Expose
    public String birthdate;

    @SerializedName("bonuses_allowed")
    @Expose
    public boolean bonuses_allowed;

    @SerializedName("is_verified")
    @Expose
    public boolean is_verified;

    @SerializedName("password")
    @Expose
    public String password = "amFuZWRvZTEyMw==";

    @SerializedName("password_repeat")
    @Expose
    public String password_repeat;

    @SerializedName("password_change")
    @Expose
    public String password_change;
}
