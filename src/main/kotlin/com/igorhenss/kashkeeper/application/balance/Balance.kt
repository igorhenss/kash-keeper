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
    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: User,
) {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    val id: UUID = user.id
    @Column(name = "current_value", nullable = false)
    var currentValue: BigDecimal = BigDecimal.ZERO
    @OneToMany(mappedBy = "id", cascade = [CascadeType.ALL])
    val history: MutableList<BalanceHistory> = mutableListOf()

    fun updateValue(history: BalanceHistory) {
        this.currentValue += history.addedValue
        this.history.add(history)
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