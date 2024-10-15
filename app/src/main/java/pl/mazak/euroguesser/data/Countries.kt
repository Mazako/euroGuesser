package pl.mazak.euroguesser.data

data class Country(
    val name: String,
    val flag: String
)

val countries = listOf(
    Country("Albania", "\uD83C\uDDE6\uD83C\uDDF1"),
    Country("Andorra", "\uD83C\uDDE6\uD83C\uDDE9"),
    Country("Armenia", "\uD83C\uDDE6\uD83C\uDDF2"),
    Country("Austria", "\uD83C\uDDE6\uD83C\uDDF9"),
    Country("Azerbaijan", "\uD83C\uDDE6\uD83C\uDDFF"),
    Country("Belarus", "\uD83C\uDDE7\uD83C\uDDFE"),
    Country("Belgium", "\uD83C\uDDE7\uD83C\uDDEA"),
    Country("Bosnia", "\uD83C\uDDE7\uD83C\uDDE6"),
    Country("Bulgaria", "\uD83C\uDDE7\uD83C\uDDEC"),
    Country("Croatia", "\uD83C\uDDED\uD83C\uDDF7"),
    Country("Cyprus", "\uD83C\uDDE8\uD83C\uDDFE"),
    Country("Czechia", "\uD83C\uDDE8\uD83C\uDDFF"),
    Country("Denmark", "\uD83C\uDDE9\uD83C\uDDF0"),
    Country("Estonia", "\uD83C\uDDEA\uD83C\uDDEA"),
    Country("Finland", "\uD83C\uDDEB\uD83C\uDDEE"),
    Country("France", "\uD83C\uDDEB\uD83C\uDDF7"),
    Country("Georgia", "\uD83C\uDDEC\uD83C\uDDEA"),
    Country("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
    Country("Greece", "\uD83C\uDDEC\uD83C\uDDF7"),
    Country("Hungary", "\uD83C\uDDED\uD83C\uDDFA"),
    Country("Iceland", "\uD83C\uDDEE\uD83C\uDDF8"),
    Country("Ireland", "\uD83C\uDDEE\uD83C\uDDEA"),
    Country("Italy", "\uD83C\uDDEE\uD83C\uDDF9"),
    Country("Kazakhstan", "\uD83C\uDDF0\uD83C\uDDFF"),
    Country("Latvia", "\uD83C\uDDF1\uD83C\uDDFB"),
    Country("Liechtenstein", "\uD83C\uDDF1\uD83C\uDDEE"),
    Country("Lithuania", "\uD83C\uDDF1\uD83C\uDDF9"),
    Country("Luxembourg", "\uD83C\uDDF1\uD83C\uDDFA"),
    Country("Malta", "\uD83C\uDDF2\uD83C\uDDF9"),
    Country("Moldova", "\uD83C\uDDF2\uD83C\uDDE9"),
    Country("Monaco", "\uD83C\uDDF2\uD83C\uDDE8"),
    Country("Montenegro", "\uD83C\uDDF2\uD83C\uDDEA"),
    Country("North Macedonia", "\uD83C\uDDF2\uD83C\uDDF0"),
    Country("Norway", "\uD83C\uDDF3\uD83C\uDDF4"),
    Country("Poland", "\uD83C\uDDF5\uD83C\uDDF1"),
    Country("Portugal", "\uD83C\uDDF5\uD83C\uDDF9"),
    Country("Romania", "\uD83C\uDDF7\uD83C\uDDF4"),
    Country("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
    Country("San Marino", "\uD83C\uDDF8\uD83C\uDDF2"),
    Country("Serbia", "\uD83C\uDDF7\uD83C\uDDF8"),
    Country("Slovakia", "\uD83C\uDDF8\uD83C\uDDF0"),
    Country("Slovenia", "\uD83C\uDDF8\uD83C\uDDEE"),
    Country("Spain", "\uD83C\uDDEA\uD83C\uDDF8"),
    Country("Sweden", "\uD83C\uDDF8\uD83C\uDDEA"),
    Country("Switzerland", "\uD83C\uDDE8\uD83C\uDDED"),
    Country("Turkey", "\uD83C\uDDF9\uD83C\uDDF7"),
    Country("Ukraine", "\uD83C\uDDFA\uD83C\uDDE6"),
    Country("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
    Country("Vatican", "\uD83C\uDDFB\uD83C\uDDE6")
)


fun pickThreeRandomCountries(trueCountry: Country): List<String> {
    val names = mutableListOf<String>()
    var i = 0
    while (i < 3) {
        val name = countries.random().name
        if (name != trueCountry.name && name !in names) {
            names.add(name)
            i++
        }
    }
    names.add(trueCountry.name)
    return names
}
