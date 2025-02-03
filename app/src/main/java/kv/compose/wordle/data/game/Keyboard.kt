package kv.compose.wordle.data.game

data class Keyboard(
    val keys: List<LinkedHashMap<Char, CharacterState>> = KeyboardLayout.english.map {
        it.associateWith {
            CharacterState.Unknown
        } as LinkedHashMap<Char, CharacterState>
    }
)

object KeyboardLayout {
    val english = listOf(
        listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
        listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L'),
        listOf('Z', 'X', 'C', 'V', 'B', 'N', 'M')
    )

    val russian = listOf(
        listOf('Й', 'Ц', 'У', 'К', 'Е', 'Н', 'Г', 'Ш', 'Щ', 'З', 'Х', 'Ъ'),
        listOf('Ф', 'Ы', 'В', 'А', 'П', 'Р', 'О', 'Л', 'Д', 'Ж', 'Э'),
        listOf('Я', 'Ч', 'С', 'М', 'И', 'Т', 'Ь', 'Б', 'Ю')
    )
}
