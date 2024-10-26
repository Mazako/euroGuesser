import shuffleArray from "shuffle-array";

export class Country {
    readonly name: string
    readonly flag: string

    constructor(name: string, flag: string) {
        this.name = name
        this.flag = flag
    }
}

export const countries = [
    new Country("Albania", "\uD83C\uDDE6\uD83C\uDDF1"),
    new Country("Andorra", "\uD83C\uDDE6\uD83C\uDDE9"),
    new Country("Armenia", "\uD83C\uDDE6\uD83C\uDDF2"),
    new Country("Austria", "\uD83C\uDDE6\uD83C\uDDF9"),
    new Country("Azerbaijan", "\uD83C\uDDE6\uD83C\uDDFF"),
    new Country("Belarus", "\uD83C\uDDE7\uD83C\uDDFE"),
    new Country("Belgium", "\uD83C\uDDE7\uD83C\uDDEA"),
    new Country("Bosnia", "\uD83C\uDDE7\uD83C\uDDE6"),
    new Country("Bulgaria", "\uD83C\uDDE7\uD83C\uDDEC"),
    new Country("Croatia", "\uD83C\uDDED\uD83C\uDDF7"),
    new Country("Cyprus", "\uD83C\uDDE8\uD83C\uDDFE"),
    new Country("Czechia", "\uD83C\uDDE8\uD83C\uDDFF"),
    new Country("Denmark", "\uD83C\uDDE9\uD83C\uDDF0"),
    new Country("Estonia", "\uD83C\uDDEA\uD83C\uDDEA"),
    new Country("Finland", "\uD83C\uDDEB\uD83C\uDDEE"),
    new Country("France", "\uD83C\uDDEB\uD83C\uDDF7"),
    new Country("Georgia", "\uD83C\uDDEC\uD83C\uDDEA"),
    new Country("Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
    new Country("Greece", "\uD83C\uDDEC\uD83C\uDDF7"),
    new Country("Hungary", "\uD83C\uDDED\uD83C\uDDFA"),
    new Country("Iceland", "\uD83C\uDDEE\uD83C\uDDF8"),
    new Country("Ireland", "\uD83C\uDDEE\uD83C\uDDEA"),
    new Country("Italy", "\uD83C\uDDEE\uD83C\uDDF9"),
    new Country("Kazakhstan", "\uD83C\uDDF0\uD83C\uDDFF"),
    new Country("Latvia", "\uD83C\uDDF1\uD83C\uDDFB"),
    new Country("Liechtenstein", "\uD83C\uDDF1\uD83C\uDDEE"),
    new Country("Lithuania", "\uD83C\uDDF1\uD83C\uDDF9"),
    new Country("Luxembourg", "\uD83C\uDDF1\uD83C\uDDFA"),
    new Country("Malta", "\uD83C\uDDF2\uD83C\uDDF9"),
    new Country("Moldova", "\uD83C\uDDF2\uD83C\uDDE9"),
    new Country("Monaco", "\uD83C\uDDF2\uD83C\uDDE8"),
    new Country("Montenegro", "\uD83C\uDDF2\uD83C\uDDEA"),
    new Country("North Macedonia", "\uD83C\uDDF2\uD83C\uDDF0"),
    new Country("Norway", "\uD83C\uDDF3\uD83C\uDDF4"),
    new Country("Poland", "\uD83C\uDDF5\uD83C\uDDF1"),
    new Country("Portugal", "\uD83C\uDDF5\uD83C\uDDF9"),
    new Country("Romania", "\uD83C\uDDF7\uD83C\uDDF4"),
    new Country("Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
    new Country("San Marino", "\uD83C\uDDF8\uD83C\uDDF2"),
    new Country("Serbia", "\uD83C\uDDF7\uD83C\uDDF8"),
    new Country("Slovakia", "\uD83C\uDDF8\uD83C\uDDF0"),
    new Country("Slovenia", "\uD83C\uDDF8\uD83C\uDDEE"),
    new Country("Spain", "\uD83C\uDDEA\uD83C\uDDF8"),
    new Country("Sweden", "\uD83C\uDDF8\uD83C\uDDEA"),
    new Country("Switzerland", "\uD83C\uDDE8\uD83C\uDDED"),
    new Country("Turkey", "\uD83C\uDDF9\uD83C\uDDF7"),
    new Country("Ukraine", "\uD83C\uDDFA\uD83C\uDDE6"),
    new Country("United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
    new Country("Vatican", "\uD83C\uDDFB\uD83C\uDDE6")
]


export const pickThreeRandomCountries = (trueCountry: Country): string[] => {
    const names: string[] = []
    let i = 0
    while (i < 3) {
        const name = countries[Math.floor(Math.random() * countries.length)].name
        if (name !== trueCountry.name && !names.includes(name)) {
            names.push(name)
            i++
        }
    }
    names.push(trueCountry.name)
    shuffleArray(names)
    return names
}
