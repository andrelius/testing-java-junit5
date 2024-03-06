package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

@Tag("controllers")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface ControllerTests {

    default void beforeAll() {
        System.out.println("Doing before all");
    }
}
