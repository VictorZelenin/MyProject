package model;

/**
 * Created by User on 13.10.2015.
 *  @Version 1.0
 */
public class Model {
    private DatabaseWorker databaseWorker;


    public Model() {
        // створюємо об'єкт для роботи із БД
        databaseWorker = DatabaseWorker.getInstance();

    }
    public DatabaseWorker getDatabaseWorker() {
        return databaseWorker;
    }


    
}
