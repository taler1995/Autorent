package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Class User
 * <p>
 * Created by yslabko on 08/11/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String login;
    private String name;
    private String surname;
    private String password;
    private Date birthday;
    private String status;
    private String role;
    private String pasportLett;
    private long passportId;
    private String country;
    private String identification;
    private int drivingExp;
    private int numberOfOrders;
    private int numberOfAccidents;
    private long phoneNumber;
    private String email;

    public User(String name, String surname, String login, String password, Date birthday,
                String pasportLett, Integer passportId, String country, String identification,
                Integer drivingExp, Integer phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.pasportLett = pasportLett;
        this.passportId = passportId;
        this.country = country;
        this.identification = identification;
        this.drivingExp = drivingExp;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }
}

