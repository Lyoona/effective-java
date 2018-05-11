package learn180511.createAndDestroyingObjects.entity;

/**
 * Created by liyoumin on 2018/5/11.
 */
public class NutritionFacts2 {

    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;


    public static class Builder{

        private int servingSize;
        private int servings;
        private int calories;
        private int fat;
        private int sodium;
        private int carbohydrate;

        public Builder servingSize(int servingSize){
            this.servingSize = servingSize;
            return this;
        }

        public Builder servings(int servings){
            this.servings = servings;
            return this;
        }

        public Builder calories(int calories){
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat){
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium){
            this.sodium = sodium;
            return this;
        }

        public Builder carbohydrate(int carbohydrate){
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutritionFacts2 build(){
            //此处可添加参数检查
            return new NutritionFacts2(this);
        }
    }

    private NutritionFacts2(Builder builder){
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
