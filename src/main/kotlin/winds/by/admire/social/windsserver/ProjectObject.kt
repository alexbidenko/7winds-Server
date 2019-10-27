package winds.by.admire.social.windsserver

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ProjectObject(
        val title: String?,
        val workers: ArrayList<Int>?,
        val platform: ArrayList<String>?,
        val start: Long?,
        val end: Long?,
        val avatar: String?) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
}