package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryManagerControllerTest {
    @Test
    void addItemSet() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.addItemTest("Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n", "$555.99", "1234554321", "Computer");
        String expected = "Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $555.99 Serial Number: 1234554321 Name: Computer";
        assertEquals(expected, actual);
    }

    @Test
    void deleteItemSet() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.deleteItemTest("Price: $1244.00 Serial Number: 1234567890 Name: TV\n" +
                "Price: $99.99 Serial Number: XXXXXXXXXX Name: Wall\n" +
                "Price: $32.55 Serial Number: HWEDFQWERT Name: Headphones\n" +
                "Price: $555.99 Serial Number: 1234554321 Name: Computer", "$555.99", "1234554321", "Computer");
        String expected = "Price: $1244.00 Serial Number: 1234567890 Name: TV\n" +
                "Price: $99.99 Serial Number: XXXXXXXXXX Name: Wall\n" +
                "Price: $32.55 Serial Number: HWEDFQWERT Name: Headphones\n";
        assertEquals(expected, actual);
    }

    @Test
    void editItemSet() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.editItemTest("Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                        "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                        "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $555.99 Serial Number: 1234554321 Name: Computer", "$555.99", "1234554321", "Computer", "$45.99", "1XX4554321", "Laptop");
        String expected = "Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $45.99 Serial Number: 1XX4554321 Name: Laptop";
        assertEquals(expected, actual);
    }

    @Test
    void editItemPriceSet() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.editItemPriceTest("Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $555.99 Serial Number: 1234554321 Name: Computer", "$555.99", "1234554321", "Computer", "$45.99");
        String expected = "Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $45.99 Serial Number: 1234554321 Name: Computer";
        assertEquals(expected, actual);
    }

    @Test
    void editItemSerialSet() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.editItemSerialTest("Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $555.99 Serial Number: 1234554321 Name: Computer", "$555.99", "1234554321", "Computer", "1XX4554321");
        String expected = "Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $555.99 Serial Number: 1XX4554321 Name: Computer";
        assertEquals(expected, actual);
    }

    @Test
    void editItemNameSet() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.editItemNameTest("Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $555.99 Serial Number: 1234554321 Name: Computer", "$555.99", "1234554321", "Computer",  "Laptop");
        String expected = "Price: $1244.00\tSerial Number: 1234567890\tName: TV\n" +
                "Price: $99.99\tSerial Number: XXXXXXXXXX\tName: Wall\n" +
                "Price: $32.55\tSerial Number: HWEDFQWERT\tName: Headphones\n" +
                "Price: $555.99 Serial Number: 1234554321 Name: Laptop";
        assertEquals(expected, actual);
    }

    @Test
    void extractPrice() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.extractPrice("$555.99 1234554321 Computer");
        String expected = "$555.99";
        assertEquals(expected, actual);
    }

    @Test
    void extractSerial() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.extractSerial("$555.99 1234554321 Computer");
        String expected = "1234554321";
        assertEquals(expected, actual);
    }

    @Test
    void extractName() {
        InventoryManagerController detector = new InventoryManagerController();
        String actual = detector.extractName("$555.99 1234554321 Computer");
        String expected = " Computer";
        assertEquals(expected, actual);
    }

    @Test
    void checkGoodName() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.checkNameLength("Computer");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void checkBadName() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.checkNameLength("C");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void checkNoName() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.checkNameLength("");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void checkGoodSerialNumber() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.checkSerialFormat("12345XXXXX");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void checkBadSerialNumber() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.checkSerialFormat("XXX45");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void saveTSVasTSV() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.saveAsTSV("TSV");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void saveNotTSVasTSV() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.saveAsTSV("JSON");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void saveHTMLasHTML() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.saveAsHTML("HTML");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void saveNotHTMLasHTML() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.saveAsHTML("JSON");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void saveJSONasJSON() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.saveAsJSON("JSON");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void saveNotJSONasJSON() {
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.saveAsJSON("TSV");
        int expected = 2;
        assertEquals(expected, actual);
    }

    @Test
    void loadTSVSuccessful(){
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.loadSuccess("TSV");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void loadHTMLSuccessful(){
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.loadSuccess("HTML");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void loadJSONSuccessful(){
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.loadSuccess("JSON");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    void loadRandomNotSuccessful(){
        InventoryManagerController detector = new InventoryManagerController();
        int actual = detector.loadSuccess("mp3");
        int expected = 2;
        assertEquals(expected, actual);
    }
}