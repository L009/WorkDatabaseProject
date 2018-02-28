package project.dbSevice.dataSets;

import javax.persistence.*;

/**
 * Created by leonid on 11.05.17.
 */

@Entity
@Table(name = "users")
public class UsersDataset {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "login", unique = true, updatable = false)
    public String login;

    @Column(name = "password", unique = false, updatable = true)
    public String password;


    public UsersDataset()
    {

    }

    public UsersDataset(String login, String password)
    {
        this.id = -1;
        this.login=login;
        this.password=password;
    }

    public UsersDataset(long id, String login, String password)
    {
        this.id = id;
        this.login=login;
        this.password=password;
    }
}