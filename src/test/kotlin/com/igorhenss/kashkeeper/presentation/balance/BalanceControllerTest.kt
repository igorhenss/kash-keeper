package com.igorhenss.kashkeeper.presentation.balance

import com.igorhenss.kashkeeper.TestHelper.Companion.USER_ID
import com.igorhenss.kashkeeper.TestHelper.Companion.asJson
import com.igorhenss.kashkeeper.application.balance.BalanceService
import com.igorhenss.kashkeeper.presentation.balance.dto.BalanceMutationDTO
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal

internal class BalanceControllerTest {

    companion object {
        private const val TITLE = "HistoryTitle"
        private const val DESCRIPTION = "HistoryDescription"
        private val ADDED_VALUE = BigDecimal.TEN
    }

    private val service = mock<BalanceService>()
    private val endpointCaller = setupController()
    private val mutationDto = BalanceMutationDTO(TITLE, ADDED_VALUE, DESCRIPTION)

    @Test
    fun shouldCallService_onControllerCall() {
        whenever(service.update(USER_ID, any())).thenReturn(BigDecimal(42))
        val result = endpointCaller.perform(put("/users/$USER_ID/balance").content(asJson(mutationDto)))
            .andReturn()

        assertThat(result.response.contentAsString).isEqualTo("42")
    }

    private fun setupController() = MockMvcBuilders.standaloneSetup(BalanceController::class.java).build()

}