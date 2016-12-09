package andara.heatcliff.yuclean.Login;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by irfan on 10/21/2016.
 */
public class ItemObjectLoginTest {
    @SerializedName("data")
    public List<Children> data;

    public class Children {
        @SerializedName("id")
        public String id;

        @SerializedName("name")
        public String name;

        @SerializedName("username")
        public String username;

        @SerializedName("registered")
        public String registered;
    }

    @SerializedName("meta")
    public List<MetaChildren> meta;

    public class MetaChildren {
        @SerializedName("token")
        public String token;
    }
}
