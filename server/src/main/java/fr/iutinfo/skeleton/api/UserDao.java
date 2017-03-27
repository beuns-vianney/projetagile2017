package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UserDao {
    @SqlUpdate("CREATE TABLE users (login VARCHAR(15) PRIMARY KEY, nom VARCHAR(100), prenom VARCHAR(100), token VARCHAR(250), groupe char, rang INTEGER NOT NULL  DEFAULT 1)")
    void createUserTable();
    
    @SqlUpdate("CREATE TABLE tp (tpid INTEGER PRIMARY KEY AUTOINCREMENT, titre VARCHAR(100), categ VARCHAR(100), path VARCHAR(100), acess integer)")
    void createTpTable();
    
    @SqlUpdate("CREATE TABLE progres (login varchar(15),tpid integer, progress integer,nbcompil integer)")
    void createProgresTable();

    @SqlUpdate("insert into users (login,nom,prenom,token) values (:login, :nom, :prenom, :token)")
    @GetGeneratedKeys
    int insertUser(@BindBean() User user);
    
    @SqlUpdate("insert into tp (tpid,categ,titre,path) values (:id, :categ, :titre, :path)")
    @GetGeneratedKeys
    int insertTp(@BindBean() Tp tp);
    
    @SqlUpdate("update users groupe=:groupe where login=:login")
    @GetGeneratedKeys
    int updateGroupeEtu(@BindBean("login") String login,@BindBean("groupe") char groupe);

    @SqlQuery("select * from users where groupe=:groupe")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByGroupe(@Bind("groupe") char groupe);

    @SqlUpdate("drop table if exists users")
    void dropUserTable();
    
    @SqlUpdate("drop table if exists tp")
    void dropTpTable();
    
    @SqlUpdate("drop table if exists progres")
    void dropProgresTable();

    @SqlUpdate("delete from users where login = :login")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from users order by login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<User> all();
    
    @SqlQuery("select * from users order by login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<StatisticEtu> statistiqueetu(@Bind("login") String login);

    @SqlQuery("select * from users where login = :login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByLogin(@Bind("login") String login);

    void close();
}
