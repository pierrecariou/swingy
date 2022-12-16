package com.pcariou.DAO;

import com.pcariou.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class HeroDAO implements DAO<Hero>
{
    private String url;
    private String username;
    private String password;

    public HeroDAO(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void create(Hero hero)
    {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            PreparedStatement statement = connection.prepareStatement("INSERT INTO heroes(name, class, level, experience, attack, defense, hit_points VALUES (?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, hero.getName());
            statement.setString(2, hero.getHeroClass());
            statement.setInt(3, hero.getLevel());
            statement.setInt(4, hero.getExperience());
            statement.setInt(5, hero.getAttack());
            statement.setInt(6, hero.getDefense());
            statement.setInt(7, hero.getHitPoints());

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    public List<Hero> readAll()
    {
        return null;
    }

    public void delete()
    {

    }
}
