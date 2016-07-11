package com.spring.meituan.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by biantao on 16/7/11.
 * 缺点,只能作用在单个数据源上,如果需要作用在多个数据源上,则需要分布式事物处理
 */
public class JdbcTransaction {

    public static void doInsert(DataSource dataSource) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "insert into tb_user (name) values ('transaction')";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                // 执行不成功,回滚
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println("数据库连接异常" + e1);
                }
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("执行异常" + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("数据库连接异常" + e);
                }
            }
        }

    }

    public static void doInsertRollback(DataSource dataSource) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = "insert into tb_user (name) values ('transaction1')";
            statement.executeUpdate(sql);
            statement.executeUpdate("insert into tb_user (nofield) values ('test')");
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                // 执行不成功,回滚
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println("数据库连接异常" + e1);
                }
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("执行异常" + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("数据库连接异常" + e);
                }
            }
        }

    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:applicationContext-datasource.xml");
        DataSource dataSource = (DataSource) applicationContext.getBean("dbcpDataSource");
//        doInsert(dataSource);
        doInsertRollback(dataSource);

    }
}
