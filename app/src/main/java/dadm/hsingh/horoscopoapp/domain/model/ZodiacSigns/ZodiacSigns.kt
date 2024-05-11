package dadm.hsingh.horoscopoapp.domain.model.ZodiacSigns

import dadm.hsingh.horoscopoapp.domain.model.ZodiacSign

class ZodiacSigns() {
    val aries: ZodiacSign = ZodiacSign(
        "Aries",
        "Los nacidos bajo el signo de Aries son enérgicos, valientes y optimistas. Son líderes naturales y les gusta asumir desafíos.",
        listOf("Leo", "Sagitario"),
        "21 de marzo",
        "19 de abril",
        listOf("Energía", "Valentía", "Optimismo"),
        listOf("Impulsividad", "Tendencia a la confrontación"),
        "aries.jpg"
    )

    val taurus: ZodiacSign = ZodiacSign(
        "Taurus",
        "Los Tauro son personas prácticas y pacientes. Disfrutan de las cosas simples de la vida y son leales y confiables en sus relaciones.",
        listOf("Virgo", "Capricornio"),
        "20 de abril",
        "20 de mayo",
        listOf("Paciencia", "Lealtad", "Estabilidad"),
        listOf("Terquedad", "Reticencia al cambio"),
        "taurus.jpg"
    )

    val gemini: ZodiacSign = ZodiacSign(
        "Gemini",
        "Los Géminis son versátiles, curiosos y sociables. Les gusta aprender y comunicarse, y pueden adaptarse fácilmente a diferentes situaciones.",
        listOf("Libra", "Acuario"),
        "21 de mayo",
        "20 de junio",
        listOf("Versatilidad", "Adaptabilidad", "Intelecto"),
        listOf("Superficialidad", "Inconstancia"),
        "gemini.jpg"
    )

    val cancer: ZodiacSign = ZodiacSign(
        "Cancer",
        "Los Cáncer son sensibles, compasivos y protectores. Valoran la seguridad y la familia, y tienen una gran intuición.",
        listOf("Escorpio", "Piscis"),
        "21 de junio",
        "22 de julio",
        listOf("Sensibilidad", "Compasión", "Intuición"),
        listOf("Sensibilidad excesiva", "Nostalgia"),
        "cancer.jpg"
    )

    val leo: ZodiacSign = ZodiacSign(
        "Leo",
        "Los Leo son carismáticos, generosos y apasionados. Les gusta estar en el centro de atención y tienen una fuerte voluntad.",
        listOf("Aries", "Sagitario"),
        "23 de julio",
        "22 de agosto",
        listOf("Carisma", "Generosidad", "Pasión"),
        listOf("Orgullo", "Necesidad de reconocimiento"),
        "leo.jpg"
    )

    val virgo: ZodiacSign = ZodiacSign(
        "Virgo",
        "Los Virgo son prácticos, analíticos y trabajadores. Son perfeccionistas y prestan atención a los detalles en todo lo que hacen.",
        listOf("Tauro", "Capricornio"),
        "23 de agosto",
        "22 de septiembre",
        listOf("Practicidad", "Analítica", "Trabajador"),
        listOf("Perfeccionismo excesivo", "Crítica"),
        "virgo.jpg"
    )

    val libra: ZodiacSign = ZodiacSign(
        "Libra",
        "Los Libra son diplomáticos, amables y sociables. Valoran la armonía y la justicia, y son excelentes mediadores en conflictos.",
        listOf("Géminis", "Acuario"),
        "23 de septiembre",
        "22 de octubre",
        listOf("Diplomacia", "Amabilidad", "Sociabilidad"),
        listOf("Indecisión", "Evitan los conflictos"),
        "libra.jpg"
    )

    val scorpio: ZodiacSign = ZodiacSign(
        "Scorpio",
        "Los Scorpio son apasionados, determinados y misteriosos. Tienen una gran profundidad emocional y son leales en sus relaciones.",
        listOf("Cáncer", "Piscis"),
        "23 de octubre",
        "21 de noviembre",
        listOf("Pasión", "Determinación", "Lealtad"),
        listOf("Celos", "Desconfianza"),
        "scorpio.jpg"
    )

    val sagittarius: ZodiacSign = ZodiacSign(
        "Sagittarius",
        "Los Sagittarius son aventureros, optimistas y sinceros. Les gusta explorar y aprender, y tienen un gran sentido del humor.",
        listOf("Aries", "Leo"),
        "22 de noviembre",
        "21 de diciembre",
        listOf("Aventurero", "Optimista", "Sincero"),
        listOf("Impaciencia", "Tendencia a ser imprudente"),
        "sagittarius.jpg"
    )

    val capricorn: ZodiacSign = ZodiacSign(
        "Capricorn",
        "Los Capricorn son responsables, disciplinados y ambiciosos. Tienen una gran determinación para alcanzar sus metas a largo plazo.",
        listOf("Tauro", "Virgo"),
        "22 de diciembre",
        "19 de enero",
        listOf("Responsabilidad", "Disciplina", "Ambición"),
        listOf("Rigidez", "Pueden ser pesimistas"),
        "capricorn.jpg"
    )

    val aquarius: ZodiacSign = ZodiacSign(
        "Aquarius",
        "Los Aquarius son originales, independientes y humanitarios. Tienen una mente abierta y les gusta desafiar las normas establecidas.",
        listOf("Géminis", "Libra"),
        "20 de enero",
        "18 de febrero",
        listOf("Originalidad", "Independencia", "Humanitarismo"),
        listOf("Excentricidad", "Falta de compromiso emocional"),
        "aquarius.jpg"
    )

    val pisces: ZodiacSign = ZodiacSign(
        "Pisces",
        "Los Pisces son compasivos, intuitivos y soñadores. Son muy sensibles y pueden absorber fácilmente las energías de su entorno.",
        listOf("Cáncer", "Escorpio"),
        "19 de febrero",
        "20 de marzo",
        listOf("Compasión", "Intuición", "Creatividad"),
        listOf("Tendencia a la evasión", "Sensibilidad excesiva"),
        "pisces.jpg"
    )

    fun getZodiacSign(sign: String): ZodiacSign? {
        return when {
            sign.equals("aquarius") -> aquarius
            sign.equals("pisces") -> pisces
            sign.equals("aries") -> aries
            sign.equals("taurus") -> taurus
            sign.equals("gemini") -> gemini
            sign.equals("cancer") -> cancer
            sign.equals("leo") -> leo
            sign.equals("virgo") -> virgo
            sign.equals("libra") -> libra
            sign.equals("scorpio") -> scorpio
            sign.equals("sagittarius") -> sagittarius
            sign.equals("capricorn") -> capricorn
            else -> null
        }
    }

}
