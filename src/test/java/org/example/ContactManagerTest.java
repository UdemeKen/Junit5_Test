package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public void setupAll(){
        System.out.println("Should Print Before all Tests.");
    }

    @BeforeEach
    public void setup(){
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Should Create Contact")
    @Disabled
    void shouldCreateContact() {
        contactManager.addContact("Aisha", "Moshood", "+809876543");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("Aisha") &&
                        contact.getLastName().equals("Moshood") &&
                        contact.getPhoneNumber().equals("+809876543"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Dora", "0908765432");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Chibuike", null, "0908765432");
        });
    }

    @Test
    @DisplayName("Should Not Create Contact When Phone Number is Null")
    public void shouldThrowRuntimeExceptionWhenPhoneNumberIsBlank(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("Arinze", "Dominique", null);
        });
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public  void tearDownAll(){
        System.out.println("Should be Executed at the End of the Test");
    }

    @Test
    @DisplayName("Should Create Contact")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on Mac OS")
    void shouldCreateContactOnlyOnMac() {
        contactManager.addContact("Aisha", "Moshood", "+809876543");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("Aisha") &&
                        contact.getLastName().equals("Moshood") &&
                        contact.getPhoneNumber().equals("+809876543"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Should Create Contact")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Disabled on Windows OS")
    void shouldCreateContactOnlyOnWindows() {
        contactManager.addContact("Aisha", "Moshood", "+809876543");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
        Assertions.assertTrue(contactManager.getAllContacts().stream()
                .filter(contact -> contact.getFirstName().equals("Aisha") &&
                        contact.getLastName().equals("Moshood") &&
                        contact.getPhoneNumber().equals("+809876543"))
                .findAny()
                .isPresent());
    }

    @Test
    @DisplayName("Test Contact Creation On Developer Machine")
    void shouldTextContactCreationOnDEV() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Aisha", "Moshood", "+809876543");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Nested
    class RepeatedNestedTest{
        @DisplayName("Repeat Contact Creation Test 5 Times")
        @RepeatedTest(value = 5, name = "Repeating Contact Creation Text {currentRepetition} of {totalRepetitions}")
        void shouldTextContactCreationRepeatedly() {
            contactManager.addContact("Aisha", "Moshood", "+809876543");
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
    }
    @Nested
    class ParameterizedNestedTest{
        @DisplayName("Repeat Contact Creation Test 5 Times")
        @ParameterizedTest
        @ValueSource(strings = {"+809876543", "+809876543", "+809876543"})
        void shouldTextContactCreationUsingValueSource(String phoneNumber) {
            contactManager.addContact("Aisha", "Moshood", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("CSV Source Case - Phone Number Should Match the Required Format")
        @ParameterizedTest
        @CsvSource({"+809876543", "+809876543", "+809876543"})
        void shouldTextPhoneNumberFormatUsingCSVSource(String phoneNumber) {
            contactManager.addContact("Aisha", "Moshood", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("CSV File Source Case - Phone Number Should Match the Required Format")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        void shouldTextPhoneNumberFormatUsingCSVFileSource(String phoneNumber) {
            contactManager.addContact("Aisha", "Moshood", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    @DisplayName("Method Source Case- Phone Number Should Match the Required Format")
    @ParameterizedTest
    @MethodSource("phoneNumberList")
    void shouldTextPhoneNumberFormatUsingMethodSource(String phoneNumber) {
        contactManager.addContact("Aisha", "Moshood", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    private List<String> phoneNumberList(){
        return Arrays.asList("+809876543", "+809876543", "+809876543");
    }
}