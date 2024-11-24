package com.springbootproject.playermanagement;

import static org.mockito.ArgumentMatchers.isNotNull;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PlayerManagementDatabaseTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void testDatabaseConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            assertThat(connection).isNotNull();
            System.out.println("Connected to the database !");
        }
    }

    @Test
    void testTableExists() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            var resultSet = connection.getMetaData().getTables(null, null, "Games", null);
            assertThat(resultSet.next()).isTrue();
        }
    }
    
}
