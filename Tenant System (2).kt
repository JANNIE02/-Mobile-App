// Group 9 - BBT 3.2 Mobile Application Development
// Lab 2: Classes and Objects - Tenant Management System (continued)
//
// Diana Wambugha    - 192686
// Sumama Khan       - 193340
// Elizabeth Wanjuhi - 176803
// Muriithi Peris    - 193429
// Birungi Jannie Mudondo - 168275

// ============================================================
// Tenant class
// ============================================================

// Task 1
// Why can two objects created from the same Tenant class have different
// names, apartment numbers, and payment statuses?
// A class is a blueprint, not the tenants themselves. Each object created
// from it gets its own separate copy of the properties in memory, so
// changing one tenant's values has no effect on any other tenant.
//
// Task 2
// What is the advantage of passing information through a constructor?
// It guarantees every Tenant is fully filled in from the moment it is
// created  there's no window where the object exists with blank values
// that someone might forget to set afterwards.
//
// Task 3
// Why is it useful to validate a value before allowing it to be stored?
// It stops invalid data (a negative rent) from ever entering the system,
// which protects every later calculation that relies on rentAmount.
//
// Task 4
// When is the getter executed? Every time the property is READ (e.g.
// println(tenant.rentAmount)) - never when it is written to.
// Getting a property means reading its value (runs get()). Setting a
// property means assigning it a new value (runs set() instead).

// Task 6
// What does it mean when we say that an Apartment has Tenant objects?
// Apartment does not inherit from Tenant - it simply holds a list of
// separate Tenant objects as one of its own properties (composition), and
// can call their methods (like payRent()) through that list.

class Tenant(
    val name: String,
    val apartmentNumber: Int,
    rentAmount: Double // plain constructor parameter; turned into the
    // validated/announced property just below
) {
    var isPaid: Boolean = false

    // "field" refers to the property's own backing storage, so writing
    // "field = value" here does not call the setter again (no recursion).
    var rentAmount: Double = rentAmount
        set(value) {
            if (value >= 0) {
                field = value
            } else {
                println("Rent amount cannot be negative.")
            }
        }
        get() {
            println("Rent amount accessed.")
            return field
        }

    fun payRent() {
        isPaid = true
        println("Rent paid successfully by $name")
    }
}

// ============================================================
// Apartment class
// ============================================================
class Apartment(val apartmentNumber: Int) {
    val tenants: MutableList<Tenant> = mutableListOf()

    fun addTenant(tenant: Tenant) {
        tenants.add(tenant)
    }

    fun showTenants() {
        println("Apartment: $apartmentNumber")
        for (tenant in tenants) {
            println("Tenant: ${tenant.name}")
            println("Rent: ${tenant.rentAmount}")
            println("Rent paid: ${tenant.isPaid}")
        }
    }
}

fun main() {
    println("Welcome to the Tenant Management System - Lab 2")

    // ============================================================
    // Tasks 1 & 2 - create two tenants, pay rent for only one,
    // display payment status of both
    // ============================================================
    println("\n===== TASKS 1 & 2 =====")
    val tenant1 = Tenant("John Kamau", 101, 15000.0)
    val tenant2 = Tenant("Mary Achieng", 102, 18000.0)

    tenant1.payRent()

    println("${tenant1.name} paid: ${tenant1.isPaid}")
    println("${tenant2.name} paid: ${tenant2.isPaid}")

    // ============================================================
    // Task 3 - try to set a negative rent amount
    // ============================================================
    println("\n===== TASK 3 =====")
    println("Rent before change attempt: ${tenant1.rentAmount}")
    tenant1.rentAmount = -5000.0
    println("Rent after attempted negative change: ${tenant1.rentAmount}")

    // ============================================================
    // Task 4 - access rentAmount and observe the getter's message
    // ============================================================
    println("\n===== TASK 4 =====")
    println(tenant1.rentAmount)

    // ============================================================
    // Tasks 5 & 6 - create an Apartment, add two tenants to it
    // ============================================================
    println("\n===== TASKS 5 & 6 =====")
    val apartment = Apartment(101)
    val john = Tenant("John", 101, 15000.0)
    val mary = Tenant("Mary", 101, 18000.0)

    apartment.addTenant(john)
    apartment.addTenant(mary)

    println("Tenants stored in apartment ${apartment.apartmentNumber}: ${apartment.tenants.size}")

    // ============================================================
    // Task 7 - display all tenants in the apartment
    // ============================================================
    println("\n===== TASK 7 =====")
    john.payRent()
    apartment.showTenants()

    println("\n===== END OF LAB 2 =====")
}