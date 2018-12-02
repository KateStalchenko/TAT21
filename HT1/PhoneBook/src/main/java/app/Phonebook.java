package app;

import util.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Phonebook {

    // Хранилище записей о людях.
    private HashMap<String, Person> persons = new HashMap<>();

    // Объект для работы с БД.
    private DBWorker db = DBWorker.getInstance();

    // Указатель на экземпляр класса.
    private static Phonebook instance = null;

    // Метод для получения экземпляра класса (реализован Singleton).
    public static Phonebook getInstance() throws SQLException {
        if (instance == null) {
            instance = new Phonebook();
        }

        return instance;
    }

    // При создании экземпляра класса из БД извлекаются все записи.
    protected Phonebook() throws SQLException {
        ResultSet db_data = this.db.getDBData("SELECT * FROM `person` ORDER BY `surname` ASC");
        while (db_data.next()) {
            this.persons.put(db_data.getString("id"),
                    new Person(db_data.getString("id"),
                            db_data.getString("name"),
                            db_data.getString("surname"),
                            db_data.getString("middlename")));
        }
    }

    // Добавление записи о человеке.
    public boolean addPerson(Person person) {
        String query;

        // У человека может не быть отчества.
        if (!person.getSurname().equals("")) {
            query = "INSERT INTO `person` (`name`, `surname`, `middlename`) VALUES ('" + person.getName() + "', '" + person.getSurname() + "', '" + person.getMiddlename() + "')";
        } else {
            query = "INSERT INTO `person` (`name`, `surname`) VALUES ('" + person.getName() + "', '" + person.getSurname() + "')";
        }

        Integer affectedRows = this.db.changeDBData(query);

        // Если добавление прошло успешно...
        if (affectedRows > 0) {
            person.setId(this.db.getLastInsertId().toString());
            // Добавляем запись о человеке в общий список.
            this.persons.put(person.getId(), person);
            return true;
        } else {
            return false;
        }
    }

    // Обновление записи о человеке.
    public boolean updatePerson(Person person) {
        Integer idFiltered = Integer.parseInt(person.getId());
        StringBuilder query = null;

        // У человека может не быть отчества.
        if (!person.getSurname().equals("")) {
            query.append("UPDATE `person` SET `name` = '" + person.getName() + "', `surname` = '" + person.getSurname() + "', `middlename` = '" + person.getMiddlename() + "' WHERE `id` = " + idFiltered);
        } else {
            query.append("UPDATE `person` SET `name` = '" + person.getName() + "', `surname` = '" + person.getSurname() + "' WHERE `id` = " + idFiltered);
        }

        Integer affected_rows = this.db.changeDBData(query.toString());

        // Если обновление прошло успешно...
        if (affected_rows > 0) {
            // Обновляем запись о человеке в общем списке.
            this.persons.put(person.getId(), person);
            return true;
        } else {
            return false;
        }
    }


    // Удаление записи о человеке.
    public boolean deletePerson(String id) {
        if ((id != null) && (!id.equals("null"))) {
            int filtered_id = Integer.parseInt(id);

            Integer affected_rows = this.db.changeDBData("DELETE FROM `person` WHERE `id`=" + filtered_id);

            // Если удаление прошло успешно...
            if (affected_rows > 0) {
                // Удаляем запись о человеке из общего списка.
                this.persons.remove(id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean addPhone(Phone phoneNumber) {
        String query = "INSERT INTO `phone` (`owner`, `number`) VALUES ('" + phoneNumber.ownerId + "', '" + phoneNumber.getPhoneNumber() + "')";

        Integer affected_rows = this.db.changeDBData(query);
        if (affected_rows > 0) {
            phoneNumber.setId(this.db.getLastInsertId().toString());
            this.persons.get(phoneNumber.getOwnerId()).getPhones().put(phoneNumber.getId(), phoneNumber);
            return true;
        } else {
            return false;
        }
    }

    public boolean deletePhone(String id, String ownerId) {
        if ((id != null) && (!id.equals("null"))) {
            int phoneId = Integer.parseInt(id);
            Integer affected_rows = this.db.changeDBData("DELETE FROM `phone` WHERE `id`=" + phoneId);
            if (affected_rows > 0) {
                this.persons.get(ownerId).getPhones().remove(id);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean updatePhone(Phone phone) {
        Integer phoneId = Integer.parseInt(phone.getId());
        String query = "UPDATE `phone` SET `number` = '" +
                phone.getPhoneNumber() + "' WHERE `id` = " +
                phoneId;

        Integer affectedRows = this.db.changeDBData(query);
        if (affectedRows > 0) {
            try {
                this.persons.get(phone.getOwnerId()).getPhones().put(phone.getId(), new Phone(phone.getId(), phone
                        .getOwnerId(), phone.getPhoneNumber()));
            } catch (Exception e) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public Phone getPhone(String ownerId, String id) {
        return getPerson(ownerId).getPhones().get(id);
    }

    // +++++++++++++++++++++++++++++++++++++++++
    // Геттеры и сеттеры
    public HashMap<String, Person> getContents() {
        return persons;
    }

    public Person getPerson(String id) {
        return this.persons.get(id);
    }

    // Геттеры и сеттеры
    // -----------------------------------------

}
