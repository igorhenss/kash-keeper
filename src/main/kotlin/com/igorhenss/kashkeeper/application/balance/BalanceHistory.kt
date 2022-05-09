package com.igorhenss.kashkeeper.application.balance

import org.hibernate.Hibernate
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "balance_history")
class BalanceHistory(
    @ManyToOne
    @JoinColumn(name = "balance_id", nullable = false)
    val balance: Balance,
    @Column(name = "title", length = 40, nullable = false)
    val title: String,
    @Column(name = "description", length = 512)
    val description: String,
    @Column(name = "added_value", nullable = false)
    val addedValue: BigDecimal,
) {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: UUID = UUID.randomUUID()
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as BalanceHistory

        return id == other.id
    }

    override fun hashCode() = javaClass.hashCode()

    override fun toString() = "[${formatCreatedAt()}] $title - R$ ${formatAddedValue()}"

    private fun formatCreatedAt() = createdAt.format(ISO_OFFSET_DATE_TIME)

    private fun formatAddedValue() = DecimalFormat("0.00").format(addedValue)
}