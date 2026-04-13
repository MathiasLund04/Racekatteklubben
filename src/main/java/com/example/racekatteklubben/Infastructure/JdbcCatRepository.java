package com.example.racekatteklubben.Infastructure;

import com.example.racekatteklubben.Domain.Cat;
import com.example.racekatteklubben.Domain.ICatRepository;
import com.example.racekatteklubben.Domain.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCatRepository implements ICatRepository {

    private final JdbcTemplate jdbcTemp;

    public JdbcCatRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemp = jdbcTemplate;
    }


    public Cat getCatById(int id) {
        String sql = """
                    SELECT 
                        Name,
                        Owner,
                        Breed,
                        EMSCode,
                        Age,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  id = ?
                    """;
        try {
            return jdbcTemp.queryForObject(sql, (rs, rowNum) -> {
                Cat cat = new Cat();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("Name"));

                Member m = new Member();
                m.setEmail(rs.getString("Owner"));
                cat.setOwner(m);

                cat.setBreed(rs.getString("Breed"));
                cat.setEmsCode(rs.getString("Ems_code"));
                cat.setAge(rs.getInt("Age"));
                cat.setFather(rs.getString("Father"));
                cat.setMother(rs.getString("Mother"));
                cat.setBreeder(rs.getString("Breeder"));
                return cat;
            }, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<Cat> getCats(){return List.of();}

    public List<Cat> getCatsByOwner(Member owner){
        String sql = """
                    SELECT 
                        Name,
                        Owner,
                        Breed,
                        EMSCode,
                        Age,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Owner = ?
                    """;
        try {
            return jdbcTemp.query(sql, (rs, rowNum) -> {
                Cat cat = new Cat();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("Name"));

                //Da Owner er en foreign key som referer til en members email og et cat objekt indeholder en member, så skal man lige oprette en member ud fra den hentede owner i DB
                Member m = new Member();
                m.setEmail(rs.getString("Owner"));
                cat.setOwner(m);

                cat.setBreed(rs.getString("Breed"));
                cat.setEmsCode(rs.getString("Ems_code"));
                cat.setAge(rs.getInt("Age"));
                cat.setFather(rs.getString("Father"));
                cat.setMother(rs.getString("Mother"));
                cat.setBreeder(rs.getString("Breeder"));
                return cat;
            }, owner.getEmail());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<Cat> getCatsByBreed(String breed){
        String sql = """
                    SELECT 
                        Name,
                        Owner,
                        Breed,
                        EMSCode,
                        Age,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Breed = ?
                    """;
        try {
            return jdbcTemp.query(sql, (rs, rowNum) -> {
                Cat cat = new Cat();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("Name"));

                Member m = new Member();
                m.setEmail(rs.getString("Owner"));
                cat.setOwner(m);

                cat.setBreed(rs.getString("Breed"));
                cat.setEmsCode(rs.getString("Ems_code"));
                cat.setAge(rs.getInt("Age"));
                cat.setFather(rs.getString("Father"));
                cat.setMother(rs.getString("Mother"));
                cat.setBreeder(rs.getString("Breeder"));
                return cat;
            }, breed);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Cat> getCatsByMother(String mother){
        String sql = """
                    SELECT 
                        Name,
                        Owner,
                        Breed,
                        EMSCode,
                        Age,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Mother = ?
                    """;
        try {
            return jdbcTemp.query(sql, (rs, rowNum) -> {
                Cat cat = new Cat();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("Name"));

                Member m = new Member();
                m.setEmail(rs.getString("Owner"));
                cat.setOwner(m);

                cat.setBreed(rs.getString("Breed"));
                cat.setEmsCode(rs.getString("Ems_code"));
                cat.setAge(rs.getInt("Age"));
                cat.setFather(rs.getString("Father"));
                cat.setMother(rs.getString("Mother"));
                cat.setBreeder(rs.getString("Breeder"));
                return cat;
            }, mother);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public List<Cat> getCatsByFather(String father){
        String sql = """
                    SELECT 
                        Name,
                        Owner,
                        Breed,
                        EMSCode,
                        Age,
                        Father,
                        Mother,
                        Breeder
                    FROM cat
                    WHERE  Father = ?
                    """;
        try {
            return jdbcTemp.query(sql, (rs, rowNum) -> {
                Cat cat = new Cat();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("Name"));

                Member m = new Member();
                m.setEmail(rs.getString("Owner"));
                cat.setOwner(m);

                cat.setBreed(rs.getString("Breed"));
                cat.setEmsCode(rs.getString("Ems_code"));
                cat.setAge(rs.getInt("Age"));
                cat.setFather(rs.getString("Father"));
                cat.setMother(rs.getString("Mother"));
                cat.setBreeder(rs.getString("Breeder"));
                return cat;
            }, father);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void addCat(Cat cat){
        String sql = """
                    INSERT INTO cat
                    (Name, Ownerl, Breed, EmsCode, Age, Father, Mother, Breeder)
                    values (?, ?, ?, ?, ?, ?, ?, ?)
                    """;
        jdbcTemp.update(sql,
                cat.getName(),
                cat.getOwner() != null ? cat.getOwner() : null,
                cat.getBreed(),
                cat.getEmsCode(),
                cat.getAge(),
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
                    EMSCode = ?,
                    Age = ?,
                    Father = ?,
                    Mother = ?,
                    Breeder = ?
                WHERE id = ?
                """;

        jdbcTemp.update(sql,
                cat.getName(),
                cat.getOwner().getEmail(),
                cat.getBreed(),
                cat.getEmsCode(),
                cat.getAge(),
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
