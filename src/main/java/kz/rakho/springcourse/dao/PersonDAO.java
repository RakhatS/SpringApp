package kz.rakho.springcourse.dao;


import kz.rakho.springcourse.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;



import java.util.List;

@Component
public class PersonDAO {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    private static final String URL ="jdbc:mysql://localhost:7777/first_db";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "";
//
//    private static Connection connection;
//
//    static {
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Person> index() {
//        List<Person> people = new ArrayList<>();
//        try  {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT *FROM person;";
//            ResultSet resultset = statement.executeQuery(SQL);
//            while (resultset.next()){
//                Person person = new Person();
//                person.setId(resultset.getInt("id"));
//                person.setName(resultset.getString("name"));
//                person.setAge(resultset.getInt("age"));
//                person.setEmail(resultset.getString("email"));
//                people.add(person);
//            }
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return people;
        return jdbcTemplate.query("SELECT *FROM person;",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
//        Person person= null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement
//                    ("SELECT *FROM person WHERE id = ?;");
//            preparedStatement.setInt(1,id);
//            ResultSet resultset = preparedStatement.executeQuery();
//            resultset.next();
//            person= new Person();
//            person.setId(resultset.getInt("id"));
//            person.setName(resultset.getString("name"));
//            person.setAge(resultset.getInt("age"));
//            person.setEmail(resultset.getString("email"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return person;
        return jdbcTemplate.query("SELECT *FROM person WHERE id = ?;",new Object[]{id},new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {



//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO person (name,age,email)VALUES(?,?,?);");
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2,person.getAge());
//            preparedStatement.setString(3,person.getEmail());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
        jdbcTemplate.update("INSERT INTO person (name,age,email)VALUES(?,?,?);",person.getName(),person.getAge(),person.getEmail());

    }

    public void update(int id, Person updatedPerson) {

//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement
//                    ("UPDATE person SET name = ?,email = ?,age = ? WHERE id = ?;");
//            preparedStatement.setInt(4,id);
//            preparedStatement.setInt(3,updatedPerson.getAge());
//            preparedStatement.setString(2, updatedPerson.getEmail());
//            preparedStatement.setString(1,updatedPerson.getName());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        jdbcTemplate.update("UPDATE person SET name = ?,email = ?,age = ? WHERE id = ?;",updatedPerson.getName(),updatedPerson.getEmail(),updatedPerson.getAge(),id);

    }

    public void delete(int id) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement
//                    ("DELETE FROM person WHERE id = ?;");
//            preparedStatement.setInt(1,id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
          jdbcTemplate.update("DELETE FROM person WHERE id = ?;",id);
    }
}
