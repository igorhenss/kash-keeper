package com.igorhenss.kashkeeper.application.user

import com.igorhenss.kashkeeper.application.balance.Balance
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
data class User(
    @Column(name = "username", nullable = false, unique = true)
    val username: String,
    @Column(name = "surname", nullable = false)
    var surname: String,
    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    val balance: Balance,
) {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: UUID = UUID.randomUUID()

    fun updateSurname(surname: String) {
        this.surname = surname
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as User

        return username == other.username || id == other.id
    }

    override fun hashCode() = javaClass.hashCode()

    override fun toString() = username
}