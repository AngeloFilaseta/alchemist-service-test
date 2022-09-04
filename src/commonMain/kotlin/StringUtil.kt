class StringUtil {
     companion object {
          fun toChars(string: String) : List<String> = string.toCharArray().map { it.toString() }
     }

}

