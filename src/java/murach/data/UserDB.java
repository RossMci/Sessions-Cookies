package murach.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import murach.business.User;
/**
 *
 * @author Mary
 */
public class UserDB {
    public static int insert(User User)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //This method adds a new record to the Customer table in the database
        String query = 
                "INSERT INTO user (firstName, lastName, emailAddress)  " + 
                "VALUES (?, ?, ?)";
        try
        {        
            ps = connection.prepareStatement(query);
            
            ps.setString(1, User.getFirstName());
            ps.setString(2, User.getLastName());
            ps.setString(3, User.getEmail());
            
            return ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
