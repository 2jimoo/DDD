package effectivejava.item2_builder;

public class CustomCoffee {
    private int espresso;
    private int water;
    private int sugar;
    private String flavor;

    private CustomCoffee(Builder builder) {
        this.espresso = builder.espresso;
        this.water = builder.water;
        this.sugar = builder.sugar;
        this.flavor = builder.flavor;
    }

    public static class Builder {

        private final int espresso;
        private final int water;
        private int sugar = 0;
        private String flavor = "mint";

        public Builder(int espresso, int water) {
            this.espresso = espresso;
            this.water = water;
        }

        public Builder addSugar(int sugar) {
            this.sugar = sugar;
            return this;
        }

        public Builder addFlavor(String flavor) {
            this.flavor = flavor;
            return this;
        }

        public CustomCoffee build() {
            return new CustomCoffee(this);
        }
    }
}
