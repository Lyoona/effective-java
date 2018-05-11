package learn180511.createAndDestroyingObjects.entity;

/**
 * Created by liyoumin on 2018/5/11.
 */

public class NutritionFacts1{
    private int servingSize = -1; // Required; no default value
    private int servings = -1; // Required; no default value
    private int calories = -1;
    private int fat = 0;
    private int sodium = 0;
    private int carbohydrate = 0;

    public NutritionFacts1(){}

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
