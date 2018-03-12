package net.servodata.app.system.info;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:zhezhela@servodata.net">Oleksandr Zhezhela</a>
 */
public class DbInfo implements BeanNameAware, InitializingBean, DatabaseMetaDataCallback {

    private static final Logger log = LoggerFactory.getLogger(DbInfo.class);

    // --- fields ---

    private String beanName;

    private String dataSourceName;

    private DataSource dataSource;

    // --- getters/setters ---

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Required
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // --- methods ---

    @Override
    public void afterPropertiesSet() throws Exception {
        JdbcUtils.extractDatabaseMetaData(dataSource, this);
    }

    @Override
    public Object processMetaData(DatabaseMetaData meta) throws SQLException {
        printDatabaseMetaData(meta);
        return null;
    }

    protected void printDatabaseMetaData(DatabaseMetaData metaData) throws SQLException {
        log.info("Data Source: " + (dataSourceName != null ? dataSourceName : beanName));
        log.info("RDBMS: " + metaData.getDatabaseProductName() + ", version: " + metaData.getDatabaseProductVersion());
        log.info("JDBC: " + metaData.getDriverName() + ", version: " + metaData.getDriverVersion());
        log.info("URL: '" + metaData.getURL() + "', user: '" + metaData.getUserName() + "', password: *****");
    }

}
