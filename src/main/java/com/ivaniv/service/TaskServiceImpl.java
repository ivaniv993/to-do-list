package com.ivaniv.service;

import com.ivaniv.domain.Task;
import com.ivaniv.domain.TaskStatus;
import com.ivaniv.service.db.MySQLDataSourceService;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by iivaniv on 13.10.2016.
 */
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private MySQLDataSourceService dataSourceService = new MySQLDataSourceService();

    @Override
    public List<Task> getAll() {
        return execute("select * from task");
    }


    @Override
    public List<Task> getFinishedTasks() {
        String query = String.format("select * from task where status='%s'", TaskStatus.FINISHED.name());
        return execute(query);
    }

    @Override
    public void updateStatus(Long id, TaskStatus status) {
        String query = String.format("update task set status='%s' where id=%d", status.name(), id);
        update(query);
    }


    @Override
    public Task getById(Long id) {
        String query = String.format("select * from task where id=%d",id );
        List<Task> tasks = execute(query);
        if ( tasks.isEmpty() )
            return null;
        return execute(query).get(0);
    }


    @Override
    public void update(String query) {
        Connection con = null;
        Statement stmt = null;
        DataSource dataSource = dataSourceService.getInstance();

        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();
            int i = stmt.executeUpdate(query);

        } catch (SQLException e) {
            log.error("Can`t update ",e);
        }finally{
            DaoUtills.closeResources(con, stmt, null);
        }
    }


    @Override
    public void save(Task task) {
        Connection con = null;
        PreparedStatement preparedStmt = null;
        DataSource dataSource = dataSourceService.getInstance();

        try {
            String insertQuery = "insert into task (name, priority, date, status) values (?,?,?,?)";
            con = dataSource.getConnection();
            preparedStmt = con.prepareStatement(insertQuery);
            preparedStmt.setString(1, task.getName());
            preparedStmt.setInt(2, task.getPriority());
            preparedStmt.setTimestamp(3, (Timestamp) task.getDeadLine());
            preparedStmt.setString(4, task.getStatus().name());
            preparedStmt.executeUpdate();

        } catch (SQLException e) {
            log.error("Can`t save ", e);
        }finally{
            DaoUtills.closeResources(con, preparedStmt, null);
        }
    }


    private List<Task> execute( String query ){

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        DataSource dataSource = dataSourceService.getInstance();

        try {
            con = dataSource.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            return rowMapper(rs);
        } catch (SQLException e) {
            log.error("Can`t execute operation ", e);
            return new ArrayList<>();
        }finally{
            DaoUtills.closeResources(con, stmt, rs);
        }
    }

    private List<Task> rowMapper( ResultSet rs ) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        while(rs.next()){
            Task task = new Task();
            task.setId(rs.getLong("id"));
            task.setName(rs.getString("name"));
            task.setDeadLine(rs.getTimestamp("date"));
            task.setStatus(TaskStatus.valueOf(rs.getString("status")));
            tasks.add(task);
        }
        return tasks;
    }

}
