class Tenant(
    val tenantId: Int,
    var name: String,
    var phoneNumber: String,
    var rentPaid: Double
) {

    fun displayTenant() {
        println("Tenant: $name")
        println("Phone: $phoneNumber")
        println("Rent Paid: $rentPaid")
    }

    fun payRent(amount: Double) {
        rentPaid += amount
    }
}

fun main() {
    val tenant = Tenant(1, "Jane Doe", "0700000000", 0.0)
    tenant.displayTenant()

    tenant.payRent(5000.0)
    println("After payment:")
    tenant.displayTenant()
}