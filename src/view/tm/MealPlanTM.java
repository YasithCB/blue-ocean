package view.tm;

public class MealPlanTM {
    private String name;
    private String mealCode;
    private double price;

    public MealPlanTM() {
    }

    public MealPlanTM(String name, String mealCode, double price) {
        this.name = name;
        this.mealCode = mealCode;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealCode() {
        return mealCode;
    }

    public void setMealCode(String mealCode) {
        this.mealCode = mealCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}
