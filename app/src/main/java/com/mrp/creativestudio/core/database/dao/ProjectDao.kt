package com.mrp.creativestudio.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mrp.creativestudio.core.database.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing production projects in the Room database.
 * Provides reactive queries and operations for project lifecycle management.
 */
@Dao
interface ProjectDao {

    /**
     * Retrieves all projects ordered by the most recently updated.
     * Returns a Flow to enable reactive UI updates in Jetpack Compose.
     */
    @Query("SELECT * FROM projects ORDER BY updated_at DESC")
    fun getAllProjects(): Flow<List<ProjectEntity>>

    /**
     * Retrieves a single project by its unique ID.
     */
    @Query("SELECT * FROM projects WHERE project_id = :projectId")
    suspend fun getProjectById(projectId: String): ProjectEntity?

    /**
     * Inserts a new project into the local database. Replaces on conflict to prevent crashes.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: ProjectEntity)

    /**
     * Updates an existing project's details.
     */
    @Update
    suspend fun updateProject(project: ProjectEntity)

    /**
     * Deletes a project by its unique ID.
     */
    @Query("DELETE FROM projects WHERE project_id = :projectId")
    suspend fun deleteProjectById(projectId: String)
}
