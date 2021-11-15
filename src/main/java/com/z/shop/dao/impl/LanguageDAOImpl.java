package com.z.shop.dao.impl;

import com.z.shop.dao.LanguageDAO;
import com.z.shop.entity.Language;
import com.z.shop.utils.DBManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAOImpl implements LanguageDAO {

    private static final Logger LOGGER = LogManager.getLogger(LanguageDAOImpl.class);
    private static DBManager dbManager = DBManager.getInstance();

    private static String CREATE = "INSERT INTO language (short_name, full_name) VALUE (?,?)";
    private static String READ_ALL = "SELECT * FROM language WHERE deleted = false";
    private static String READ_BY_ID = "SELECT * FROM language  WHERE id =? AND deleted = false";

    @Override
    public Language create(Language language) {

        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            int k = 0;
            preparedStatement.setString(++k, language.getShortName());
            preparedStatement.setString(++k, language.getFullName());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            language.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return language;
    }

    @Override
    public Language read(Integer id) {
        Language language = null;
        try ( Connection connection = dbManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID))  {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            language = getLanguageFromResultSet(resultSet);

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return language;
    }

    @Override
    public Language update(Language language) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Language> readAll() {
        List<Language> languageRecords = new ArrayList<>();
        try ( Connection connection = dbManager.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL))  {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                Language language = getLanguageFromResultSet(resultSet);
                languageRecords.add(language);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return languageRecords;
    }

    private static Language getLanguageFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String shortName = resultSet.getString("short_name");
        String fullName = resultSet.getString("full_name");
        boolean deleted = resultSet.getBoolean("deleted");

        Language language = new Language();
        language.setId(id);
        language.setShortName(shortName);
        language.setFullName(fullName);
        language.setDeleted(deleted);

        return  language;
    }
}
