package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("CREATE TABLE users (login VARCHAR(15) PRIMARY KEY, nom VARCHAR(100), prenom VARCHAR(100), token VARCHAR(250), groupe char, rang INTEGER NOT NULL  DEFAULT 1)")
    void createUserTable();
    
    @SqlUpdate("CREATE TABLE tp (tpid INTEGER PRIMARY KEY AUTOINCREMENT, titre VARCHAR(100), categorie VARCHAR(100), chemin VARCHAR(100))")
    void createTpTable();
    
    @SqlUpdate("CREATE TABLE progres (login varchar(15),tpid integer, progress integer,nbcompil integer,acess integer)")
    void createProgresTable();

    @SqlUpdate("insert into users (login,nom,prenom,token) values (:login, :nom, :prenom, :token)")
    @GetGeneratedKeys
    int insert(@BindBean() User user);

    @SqlQuery("select * from users where name = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByName(@Bind("name") String name);

    @SqlQuery("select * from users where search like :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> search(@Bind("name") String name);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();

    @SqlUpdate("delete from users where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();

    @SqlQuery("select * from users where login = :login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findById(@Bind("login") int id);

    void close();
}
