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

    @Test fun russian() = assertEquals(
        "1234" to "115.00 MDL",
        parseBalance("Перевод P2P на сумму 15 MDL был зачислен на карту ***1234. Доступный остаток: 115.00 MDL.")
    )

    @Test fun romanian() = assertEquals(
        "1234" to "130.00 MDL",
        parseBalance("Transferul P2P în sumă de 15 MDL a fost primit pe cardul ***1234. Sold disponibil: 130.00 MDL.")
    )

    @Test fun balanceWithoutCard() = assertNull(parseBalance("Available balance: 100.00 MDL."))

    @Test fun unrelatedText() = assertNull(parseBalance("Your OTP code is 1234"))
}
