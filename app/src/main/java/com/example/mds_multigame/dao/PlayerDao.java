package com.example.mds_multigame.dao;

import com.example.mds_multigame.model.Player;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM Player")
    List<Player> getAllPlayers();

    @Query("SELECT * FROM Player WHERE name = :name ")
    List<Player> getPlayerByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player player);

    @Delete
    void delete(Player player);


}
