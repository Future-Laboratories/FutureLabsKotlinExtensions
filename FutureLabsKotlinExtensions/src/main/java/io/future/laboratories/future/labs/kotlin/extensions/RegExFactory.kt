package io.future.laboratories.future.labs.kotlin.extensions

public class RegExFactory {
    private var regExString = ""
    private var regExOptions: Set<RegexOption> = emptySet()

    public infix fun RegExFactory.not(rhs: RegExFactory.() -> RegExFactory): RegExFactory {
        regExString += """^"""
        rhs()

        return this
    }

    public fun or(rhs: RegExFactory.() -> RegExFactory): RegExFactory {
        regExString += """|"""
        rhs()

        return this
    }

    public fun addGroup(content: () -> RegExFactory): RegExFactory {
        regExString += "("
        content()
        regExString += ")"

        return this
    }

    public fun addClass(content: RegExFactory.() -> RegExFactory): RegExFactory {
        regExString += "["
        content()
        regExString += "]"

        return this
    }

    // region Group exclusive functions
    public fun RegExFactory.allowSpace(): RegExFactory {
        regExString += """\s""" // spaces

        return this
    }

    public fun RegExFactory.allowWord(): RegExFactory {
        regExString += """\w""" // [a-zA-Z0-9_]

        return this
    }

    public fun RegExFactory.allowNumbers(): RegExFactory {
        regExString += """\d""" // [0-9]

        return this
    }

    public fun RegExFactory.allowCharacters(): RegExFactory {
        regExString += """A-Za-z"""

        return this
    }

    public fun RegExFactory.allowUpperCharacters(): RegExFactory {
        regExString += """A-Z"""

        return this
    }

    public fun RegExFactory.allowLowerCharacters(): RegExFactory {
        regExString += """a-z"""

        return this
    }
    // endregion

    public fun addSingleChar(char: Char): RegExFactory {
        regExString += "$char"

        return this
    }

    public fun addChars(chars: String): RegExFactory {
        regExString += chars

        return this
    }

    public fun addWildCard(): RegExFactory {
        regExString += """*"""

        return this
    }

    public fun addOptions(vararg options: RegexOption) {
        regExOptions += options
    }

    public fun build(): Regex {
        regExString
            .trim()
            .also {
                return if (regExOptions.isEmpty()) {
                    it.toRegex()
                } else {
                    it.toRegex(regExOptions)
                }
            }
    }
}