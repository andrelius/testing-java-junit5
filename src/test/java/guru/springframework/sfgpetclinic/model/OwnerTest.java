package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("New York");
        owner.setTelephone("1234567890");

        assertAll("Properties Test",
                () ->
                    assertAll("Person Properties",
                        () -> assertEquals("Joe", owner.getFirstName()),
                        () ->  assertEquals("Buck", owner.getLastName())
                    ),
                () ->
                    assertAll("Owner Properties",
                        () -> assertEquals("New York", owner.getCity()),
                        () -> assertEquals("1234567890", owner.getTelephone())
                    )
        );

        // hamcrest
        assertThat(owner.getCity(), is("New York"));
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("Value Source Test")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }


    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("ENUM Source Test")
    @EnumSource(OwnerType.class)
    void testEnumSource(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("CSV Source Test")
    @CsvSource({
            "FL, 1, 1", "OH, 2, 2", "MI, 3, 3"
    })
    void testCSVSource(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("CSV File Source Test")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void testCSVFileSource(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("Method Provider Source Test")
    @MethodSource("getArgs")
    void testMethodSource(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }

    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @DisplayName("Arguments Source Test")
    @ArgumentsSource(CustomArgsProvider.class)
    void testArgumentsSource(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + " : " + val2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
            Arguments.of("FL", 100, 100),
            Arguments.of("OH", 200, 200),
            Arguments.of("MI", 300, 300)
        );
    }
}
