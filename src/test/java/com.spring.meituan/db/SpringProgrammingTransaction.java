package com.spring.meituan.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by biantao on 16/7/11.
 * Spring 编程式事务管理
 * 依赖transactionManager对操作进行管理,不需要进行commit和rollback操作
 */
public class SpringProgrammingTransaction {

    /**
     * 不需要rollback和commit
     * @param transactionManager
     * @param jdbcTemplate
     * @return
     */
    public static int doInsert(DataSourceTransactionManager transactionManager, final JdbcTemplate jdbcTemplate) {
        int result = -1;
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        result = transactionTemplate.execute(new TransactionCallback<Integer>() {
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                return jdbcTemplate.update("insert into tb_user (name) VALUES ('programming transaction')");
            }
        });
        return  result;
    }

    /**
     * 仍然使用jdbc rollback和commit
     * @param transactionManager
     * @param jdbcTemplate
     * @return
     */
    public static int doInsertWithCommit(DataSourceTransactionManager transactionManager, JdbcTemplate jdbcTemplate) {
        int result = -1;
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        try {
            result = jdbcTemplate.update("insert into tb_user (name) VALUES ('programming transaction')");
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            throw e;
        } finally {
            transactionManager.commit(status);
        }
        return  result;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-datasource.xml");
        DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) applicationContext.getBean("dataSourceTransactionManager");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        doInsert(dataSourceTransactionManager, jdbcTemplate);
    }

}
