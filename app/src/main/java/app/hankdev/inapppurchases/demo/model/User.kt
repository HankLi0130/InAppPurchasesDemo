package app.hankdev.inapppurchases.demo.model

data class User(
    val id: String = "",
    val token: String = "",
    val plan: Plan = Plan.FREE
)

enum class Plan {
    FREE, PAID
}