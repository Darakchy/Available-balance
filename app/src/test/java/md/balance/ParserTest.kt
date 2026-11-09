package md.balance

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ParserTest {
    @Test fun realMaibMessage() = assertEquals(
        "1234" to "100.00 MDL",
        parseBalance("Successful payment of 25.50 MDL at SHOP with card ***1234. Available balance: 100.00 MDL.")
    )

    @Test fun thousandsSeparator() = assertEquals(
        "4321" to "1,100.00 MDL",
        parseBalance("Successful payment of 5.00 MDL at X with card ***4321. Available balance: 1,100.00 MDL.")
    )

    @Test fun p2pTransferOut() = assertEquals(
        "1234" to "98.00 MDL",
        parseBalance("The P2P transfer of 2 MDL from card ***1234 has been successfully initiated. Available balance: 98.00 MDL.")
    )

    @Test fun p2pTransferIn() = assertEquals(
        "5678" to "1,002 MDL",
        parseBalance("The P2P transfer of 2 MDL has been received on card ***5678. Available balance: 1,002 MDL.")
    )

    @Test fun unrelatedText() = assertNull(parseBalance("Your OTP code is 1234"))
}
