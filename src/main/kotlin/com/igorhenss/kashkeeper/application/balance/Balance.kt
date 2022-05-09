package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.application.user.User
import org.hibernate.Hibernate
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "balance")
class Balance(
    user: User,
    currentValue: BigDecimal
) {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    val id: UUID = user.id
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User
    @Column(name = "current_value", nullable = false)
    var currentValue: BigDecimal = BigDecimal.ZERO

    init {
        this.user = user
        this.currentValue = currentValue
    }

    fun updateValue(valueToAdd: BigDecimal) {
        this.currentValue += valueToAdd
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Balance

        return user == other.user || id == other.id
    }

    override fun hashCode() = javaClass.hashCode()

    override fun toString() = "$user - R$ ${formatCurrentValue()}"

    private fun formatCurrentValue() = DecimalFormat("0.00").format(currentValue)
}