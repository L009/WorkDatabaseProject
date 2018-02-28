package project.dbSevice.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import project.dbSevice.dataSets.UsersDataset;
import project.dbSevice.executor.HExecutor;

import java.sql.SQLException;

/**
 * Created by leonid on 09.05.17.
 */
public class HUsersDAO extends UsersDAO {

    HExecutor hExecutor;

    public HUsersDAO(Session session)
    {
        this.hExecutor = new HExecutor(session);
    }

    @Override
    public long registerUser(String login, String password) throws SQLException {
        return  hExecutor.execute(
                (sess)-> {
                    return (Long)sess.save(new UsersDataset(login, password));
                }
        );
    }

    @Override
    public boolean hasUserByLogin(String login) throws SQLException {
        UsersDataset usersDataset = hExecutor.execute(
                (sess)-> {
                    Criteria criteria = sess.createCriteria(UsersDataset.class);
                    return (UsersDataset)criteria.
                            add(Restrictions.eq("login", login)).
                            uniqueResult();
                });

        if(usersDataset != null)
            return true; else return false;
    }

    @Override
    public UsersDataset getUserByLoginAndPass(String login, String password) throws SQLException {

        UsersDataset usersDataset = hExecutor.execute(
                (sess)-> {
                    Criteria criteria = sess..createCriteria(UsersDataset.class);
                    return (UsersDataset)criteria.
                            add(Restrictions.eq("login", login)).
                            uniqueResult();
                });


        if(usersDataset != null && usersDataset.password.equals(password))
            return usersDataset; else return null;
    }
}
