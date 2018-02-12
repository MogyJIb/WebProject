package by.gstu.training.models;

import java.io.Serializable;

public class DbEntity implements Serializable {
    private long id;

    public DbEntity(){

    }

    public DbEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static DbEntity getInstance(DbEntity entity){
        return new DbEntity(entity.getId());
    }

    @Override
    public String toString() {
        return "id=" + id +", ";
    }
}
