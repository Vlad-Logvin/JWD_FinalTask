package by.logvin.onlinestore.dao.connection;

import java.sql.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public final class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POLL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initPoolData() throws ConnectionPoolException {
        if (connectionQueue == null) {
            try {
                Class.forName(driverName);
                givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
                connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
                for (int i = 0; i < poolSize; i++) {
                    connectionQueue.add(DriverManager.getConnection(url, user, password));
                }
            } catch (SQLException e) {
                throw new ConnectionPoolException("SQLException in ConnectionPool", e);
            } catch (ClassNotFoundException e) {
                throw new ConnectionPoolException("Can't find database driver class", e);
            }
        }
    }

    public Connection getConnection() throws ConnectionPoolException {
        Connection connection = null;
        try {
            synchronized (this) {
                connection = connectionQueue.take();
                givenAwayConQueue.add(connection);
            }
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Interrupted exception while taking connection", e);
        }
        return connection;
    }

    public boolean removeConnection(Connection connection) {
        boolean isRemove = false;
        if (connection != null) {
            synchronized (this) {
                givenAwayConQueue.remove(connection);
                isRemove = connectionQueue.add(connection);
            }
        }
        return isRemove;
    }

    public void closePoolData() throws ConnectionPoolException {
        try {
            closeConnectionQueue(connectionQueue);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection pool exceptions throws while closing connection queue", e);
        }
        try {
            closeConnectionQueue(givenAwayConQueue);
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection pool exceptions throws while closing given away connection queue", e);
        }
    }

    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
        Connection connection = null;
        while ((connection = queue.poll()) != null) {
            connection.close();
        }
    }

}
