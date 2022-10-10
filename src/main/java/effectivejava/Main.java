package effectivejava;

import effectivejava.item2_builder.CustomCoffee;

public class Main {
    public static void main(String[] args) {
        // Coffee mocha = new Mocha();
        // Coffee mocha2 = Coffee.newMocha();
        CustomCoffee customCoffee = new CustomCoffee.Builder(100, 200).addFlavor("vanilla").build();
    }
}
