package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
import org.skife.jdbi.v2.unstable.BindIn;

import java.util.List;

public interface UserDao {
    @SqlUpdate("CREATE TABLE users (login VARCHAR(15) PRIMARY KEY, nom VARCHAR(100), prenom VARCHAR(100), token VARCHAR(250), groupe varchar(1), rang INTEGER NOT NULL  DEFAULT 1)")
    void createUserTable();
    
    @SqlUpdate("CREATE TABLE tp (tpid INTEGER PRIMARY KEY AUTOINCREMENT, titre VARCHAR(100), categ VARCHAR(100), path VARCHAR(100), acess integer)")
    void createTpTable();
    
    @SqlUpdate("CREATE TABLE progres (login varchar(15),tpid integer, progress integer,nbcompil integer)")
    void createProgresTable();

    @SqlUpdate("insert into users (login,nom,prenom,token, groupe) values (:login, :nom, :prenom, :token, :groupe)")
    @GetGeneratedKeys
    int insertUser(@BindBean() User user);
    
    @SqlUpdate("insert into tp (tpid,categ,titre,path) values (:id, :categ, :titre, :path)")
    @GetGeneratedKeys
    int insertTp(@BindBean() Tp tp);
    
    @SqlUpdate("insert into progres (login,tpid,nbcompil,progress) values (:login, :id,0,0)")
    int insertProgress(@BindBean() StatisticEtu statétu);
    
    @SqlUpdate("update progres set nbcompil=(nbcompil+1) where login=:login and tpid=:tpid")
    @GetGeneratedKeys
    int incrementCompil(@Bind("login") String login,@Bind("tpid") int tpid);
    
    @SqlUpdate("update progres set progress=:progress where login=:login and tpid=:tpid")
    int updateProgression(@Bind("login") String login,@Bind("progress") int progress,@Bind("tpid") int tpid);       
    
    @SqlUpdate("update users set groupe=:groupe where login=:login")
    int updateGroupeEtu(@Bind("login") String login,@Bind("groupe") char groupe);

    @SqlQuery("select * from users where groupe=:groupe")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByGroupe(@Bind("groupe") String groupe);
    
    @SqlQuery("select tpid from tp where categ=:categ and titre=:titre")
    @RegisterMapperFactory(BeanMapperFactory.class)
    int getidbytitreandcateg(@Bind("categ") String categ,@Bind("titre") String titre);

    @SqlQuery("select * from users where token=:token")
    @RegisterMapperFactory(BeanMapperFactory.class)
    User findByToken(@Bind("token") String token);
    
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
    
    @SqlQuery("select categ,titre,nbcompil,progress from progres,tp where progres.tpid=tp.tpid and login=:login")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<StatisticEtu> getStatEtu(@Bind("login") String login);


    void close();
}
