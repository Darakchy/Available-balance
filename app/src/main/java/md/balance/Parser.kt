package md.balance

// maib, any app language: "... ***1234 [any words]. Available balance: 100.00 MDL."
// Card = "***" + 4 digits, balance = number + currency after one of the known phrases.
// [^*] lets words sit between card and balance, but never another "***XXXX" card.
private val PATTERN = Regex(
    """\*{3}(\d{4})\b[^*]*?(?:Available balance|Sold disponibil|Доступный остаток):\s*([\d.,\s]*\d)\s*([A-Z]{3})"""
)

/** Returns (last 4 digits, "100.00 MDL") or null if the text is not a maib balance message. */
fun parseBalance(text: CharSequence): Pair<String, String>? {
    val m = PATTERN.find(text) ?: return null
    val (card, amount, currency) = m.destructured
    return card to "${amount.trim()} $currency"
}
