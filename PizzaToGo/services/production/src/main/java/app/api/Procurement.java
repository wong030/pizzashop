import com.google.gson.annotations.SerializedName;

public class Procurement {
    @SerializedName("ingredients")
    private String[] ingredients;

    public Procurement(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }
}