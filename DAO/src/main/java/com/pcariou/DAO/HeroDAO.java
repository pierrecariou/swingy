package com.pcariou.DAO;

import com.pcariou.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.ArrayList;

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

            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO Hero(name, class, level, experience, attack, defense, hit_points)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?);") ) {
                statement.setString(1, hero.getName());
                statement.setString(2, hero.getHeroClass());
                statement.setInt(3, hero.getLevel());
                statement.setInt(4, hero.getExperience());
                statement.setInt(5, hero.getAttack());
                statement.setInt(6, hero.getDefense());
                statement.setInt(7, hero.getHitPoints());

                statement.executeUpdate();
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Hero hero)
    {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            try (PreparedStatement statement = connection.prepareStatement("UPDATE Hero SET name = ?, class = ?, level = ?, experience = ?, attack = ?, defense = ?, hit_points = ?"
                + " WHERE name = ?;") ) {
                statement.setString(1, hero.getName());
                statement.setString(2, hero.getHeroClass());
                statement.setInt(3, hero.getLevel());
                statement.setInt(4, hero.getExperience());
                statement.setInt(5, hero.getAttack());
                statement.setInt(6, hero.getDefense());
                statement.setInt(7, hero.getHitPoints());
                statement.setString(8, hero.getName());

                statement.executeUpdate();
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    public List<Hero> readAll()
    {
        List<Hero> heroes = new ArrayList<Hero>();

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            try (PreparedStatement statement = connection.prepareStatement("SELECT name, class, level, experience, attack, defense, hit_points FROM Hero ORDER BY level DESC;") ) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String heroClass = resultSet.getString("class");
                    int level = resultSet.getInt("level");
                    int experience = resultSet.getInt("experience");
                    int attack = resultSet.getInt("attack");
                    int defense = resultSet.getInt("defense");
                    int hitPoints = resultSet.getInt("hit_points");

                    heroes.add(new HeroFactory().newHero(name, heroClass, level, experience, attack, defense, hitPoints));
                }
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return heroes;
    }

    public void delete()
    {

    }
}
