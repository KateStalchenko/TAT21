package app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Katsiaryna Stalchanka
 * @since 01-Dec-18
 */
public class Phone {

    String id = "";
    String ownerId = "";
    String phoneNumber = "";

    public Phone() {
        this.id = "0";
        this.ownerId = "";
        this.phoneNumber = "";
    }

    public Phone(String id, String ownerId, String phoneNumber) {
        this.id = id;
        this.ownerId = ownerId;
        this.phoneNumber = phoneNumber;
    }

    public Phone(String ownerId, String number) {
        this.ownerId = ownerId;
        this.phoneNumber = number;
    }

    public String validateNumber(String phoneNUM) {
        if (!phoneNUM.isEmpty()) {
            Matcher matcher = Pattern.compile("[0-9-+#]{2,50}").matcher(phoneNumber);
            return "";
        }
        return "Проверьте введенный номер. Он должен состоять из цифр и знаков \"+\" \"-\" \"#\".";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
