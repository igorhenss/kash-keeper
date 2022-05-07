package com.igorhenss.kashkeeper.application.user

import com.igorhenss.kashkeeper.application.balance.Balance
import org.hibernate.Hibernate
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column(name = "username", nullable = false, unique = true)
    val username: String,
    @Column(name = "surname", nullable = false)
    var surname: String,
    balance: BigDecimal
) {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: UUID = UUID.randomUUID()

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    val balance: Balance

    init {
        this.balance = Balance(this, balance)
    }

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