package winds.by.admire.social.windsserver

import org.springframework.data.jpa.repository.JpaRepository

internal interface ProjectsRepository : JpaRepository<ProjectObject, Long>