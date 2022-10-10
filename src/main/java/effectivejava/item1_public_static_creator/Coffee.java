package effectivejava.item1_public_static_creator;

import java.lang.reflect.InvocationTargetException;

public interface Coffee {
    static Coffee newMocha() throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Coffee> coffeeClass = (Class<Coffee>) Class.forName("com.example.ddd.Mocha");
        return coffeeClass.getConstructor().newInstance();
    }
}
