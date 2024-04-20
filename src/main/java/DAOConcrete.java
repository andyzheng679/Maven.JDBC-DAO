import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOConcrete implements DAO<User>{
    @Override
    public User findById(int id) {
        Connection connection = Main.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id=" + id);

            if(rs.next())
            {
                User user = new User();

                user.setId( rs.getInt("id") );
                user.setLastname( rs.getString("lastname") );
                user.setFirstname( rs.getString("firstname") );
                user.setDeckname(rs.getString("deckname"));
                user.setAge( rs.getInt("age") );

                return user;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection connection = Main.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");

            while(rs.next())
            {
                User user = new User();

                user.setId( rs.getInt("id") );
                user.setLastname( rs.getString("lastname") );
                user.setFirstname( rs.getString("firstname") );
                user.setDeckname(rs.getString("deckname"));
                user.setAge( rs.getInt("age") );

                userList.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userList;
    }

    @Override
    public User update(User dto) {


        try (Connection connection = Main.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("UPDATE MTG SET lastname = ?, firstname = ?, deckname = ?, age = ? WHERE id = ?")){
            pstmt.setString(1, dto.getLastname());
            pstmt.setString(2, dto.getFirstname());
            pstmt.setString(3, dto.getDeckname());
            pstmt.setInt(4, dto.getAge());
            pstmt.setInt(5, dto.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User updated successfully.");
                return dto;
            } else {
                System.out.println("No user was updated.");
                return null;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public User create(User dto) {

        try (Connection connection = Main.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (lastname, firstname, deckname, age) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters from the User object
            pstmt.setString(1, dto.getLastname());
            pstmt.setString(2, dto.getFirstname());
            pstmt.setString(3, dto.getDeckname());
            pstmt.setInt(4, dto.getAge());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        dto.setId(generatedKeys.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
                return dto;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {

        try (Connection connection = Main.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("DELETE FROM MTG WHERE id = ?")) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found with id: " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Error deleting user: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}