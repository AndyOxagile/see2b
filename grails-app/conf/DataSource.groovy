dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
}

def defaultConnectionProperties =  [
        maxActive: 100,
        maxIdle: 25,
        minIdle: 5,
        initialSize: 5,
        maxAge: 10 * 60000,
        minEvictableIdleTimeMillis: 60000,
        timeBetweenEvictionRunsMillis: 60000,
        maxWait: 10000,
        validationQuery: "select 1",
        validationQueryTimeout: 3,
        validationInterval: 15000,
        testWhileIdle: true,
        testOnBorrow: true,
        testOnReturn: false,
        jdbcInterceptors: "ConnectionState;StatementCache(max=200)"
]

// environment specific settings
environments {
    development{
        dataSource {
            logSql = false
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            dbCreate = "create-drop"
            url = "jdbc:mysql://localhost:3306/seeb?sessionVariables=wait_timeout=28800&amp;sessionVariables=interactive-timeout=28800&amp;autoReconnect=true&amp;characterEncoding=utf8&amp;cacheCallableStmts=true&amp;cachePrepStmts=true&amp;useServerPrepStmts=false"
            username = 'becker'
            password = 'becker'
            properties = defaultConnectionProperties
            configClass = HibernateFilterDomainConfiguration
        }
    }
    production {
        dataSource {
            logSql = false
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            dbCreate = "create-drop"
            url = "jdbc:mysql://localhost:3306/seeb?sessionVariables=wait_timeout=28800&amp;sessionVariables=interactive-timeout=28800&amp;autoReconnect=true&amp;characterEncoding=utf8&amp;cacheCallableStmts=true&amp;cachePrepStmts=true&amp;useServerPrepStmts=false"
            username = 'root'
            password = 'root'
            properties = defaultConnectionProperties
            configClass = HibernateFilterDomainConfiguration
        }

    }
}
