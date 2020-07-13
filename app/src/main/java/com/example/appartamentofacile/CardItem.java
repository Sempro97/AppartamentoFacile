package com.example.appartamentofacile;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class which represents every card item with its information (image, place, data, description)
 */
@Entity(tableName = "item")
public class CardItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    private int id;
    @ColumnInfo(name = "start_date")
    private String startDate;
    @ColumnInfo(name = "end_date")
    private String endDate;
    @ColumnInfo(name = "item_title")
    private String title;
    @ColumnInfo(name = "item_description")
    private String description;

    public int getUserCreatorID() {
        return userCreatorID;
    }

    @ColumnInfo(name = "user_creator_id")
    private int userCreatorID;


    public CardItem(String startDate, String endDate,String title, String description,int userCreatorID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
        this.userCreatorID = userCreatorID;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
