package winds.by.admire.social.windsserver

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class PersonObject(
        val name: String?,
        val who: String?,
        val profile: ArrayList<String>?,
        val tel: String?,
        val email: String?,
        val avatar: String?,
        val status: Int?) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
}