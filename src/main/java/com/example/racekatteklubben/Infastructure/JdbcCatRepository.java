package com.example.racekatteklubben.Infastructure;

import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.Gender;
import com.example.racekatteklubben.Domain.ICatRepository;
import com.example.racekatteklubben.Domain.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcCatRepository implements ICatRepository {

    private final JdbcTemplate jdbcTemp;

    public JdbcCatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemp = jdbcTemplate;
    }

    private final RowMapper<Cat> catRowMapper = (rs, rowNum) -> {

        Cat cat = new Cat();

        cat.setId(rs.getInt("Id"));
        cat.setName(rs.getString("Name"));

        Member owner = new Member();

        owner.setEmail(rs.getString("Owner"));
        cat.setOwner(owner);

        cat.setBreed(rs.getString("Breed"));
        cat.setGender(Gender.valueOf(rs.getString("Gender")));
        cat.setColor(rs.getString("Color"));


        LocalDate dateOfBirth = rs.getObject("DateOfBirth", LocalDate.class);
        LocalDate dateOfDeath = rs.getObject("DateOfDeath", LocalDate.class);

        cat.setDateOfBirth(dateOfBirth);
        cat.setDateOfDeath(dateOfDeath);
        cat.setDead(rs.getBoolean("IsDead"));

        cat.setFather(rs.getString("Father"));
        cat.setMother(rs.getString("Mother"));
        cat.setBreeder(rs.getString("Breeder"));

        return cat;

    };

    @Override
    public Cat getCatById(int id) {
        String sql = """
                    SELECT 
                        Id,
                        Name,
                        Owner,
                        Breed,
                        Gender,
                        color,
                        dateOfBirth,
                        isDead,
                        dateOfDeath,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  id = ?
                    """;
        try {
            return jdbcTemp.queryForObject(sql,catRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Cat> getCatsByOwner(Member owner){
        String sql = """
                    SELECT 
                        Id,
                        Name,
                        Owner,
                        Breed,
                        Gender,
                        Color,
                        DateOfBirth,
                        IsDead,
                        DateOfDeath,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Owner = ?
                    """;
        try {
            return jdbcTemp.query(sql, catRowMapper, owner.getEmail());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Cat> getCatsByBreed(String breed){
        String sql = """
                    SELECT 
                        Id,
                        Name,
                        Owner,
                        Breed,
                        Gender,
                        Color,
                        DateOfBirth,
                        IsDead,
                        DateOfDeath,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Breed = ?
                    """;
        try {
            return jdbcTemp.query(sql, catRowMapper, breed);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Cat> getCatsByMother(String mother){
        String sql = """
                    SELECT 
                        Id,
                        Name,
                        Owner,
                        Breed,
                        Gender,
                        Color,
                        DateOfBirth,
                        IsDead,
                        DateOfDeath,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Mother = ?
                    """;
        try {
            return jdbcTemp.query(sql, catRowMapper, mother);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Cat> getCatsByFather(String father){
        String sql = """
                    SELECT 
                        Id,
                        Name,
                        Owner,
                        Breed,
                        Gender,
                        Color,
                        DateOfBirth,
                        IsDead,
                        DateOfDeath,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Father = ?
                    """;
        try {
            return jdbcTemp.query(sql, catRowMapper, father);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void addCat(Cat cat){
        String sql = """
                    INSERT INTO cat
                    (Name, Owner, Breed, Gender, Color, DateOFBirth, IsDead, DateOfDeath, Father, Mother, Breeder)
                    values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
        jdbcTemp.update(sql,
                cat.getName(),
                cat.getOwner() != null ? cat.getOwner().getEmail() : null,
                cat.getBreed(),
                cat.getGender().name(),
                cat.getColor(),
                cat.getDateOfBirth(),
                cat.isDead(),
                cat.getDateOfDeath(),
                cat.getFather(),
                cat.getMother(),
                cat.getBreeder()
                );
    }
    public void updateCat(int id, Cat cat){
        String sql = """
                UPDATE cat
                SET
                    Name = ?,
                    Owner = ?,
                    Breed = ?,
                    Gender = ?,
                    Color = ?,
                    DateOFBirth = ?,
                    IsDead = ?,
                    DateOfDeath = ?,
                    Father = ?,
                    Mother = ?,
                    Breeder = ?
                WHERE id = ?
                """;

        jdbcTemp.update(sql,
                cat.getName(),
                cat.getOwner().getEmail(),
                cat.getBreed(),
                cat.getGender().name(),
                cat.getColor(),
                cat.getDateOfBirth(),
                cat.isDead(),
                cat.getDateOfDeath(),
                cat.getFather(),
                cat.getMother(),
                cat.getBreeder(),
                id
        );
    }
    public void removeCat(int id){
        String sql = """
                DELETE FROM cat
                WHERE id = ?
                """;
        jdbcTemp.update(sql, id);
    }

}
