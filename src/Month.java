public enum Month {
    JANUARY ("Январь", 1),
    FEBRUARY ("Февраль", 2),
    MARCH ("Март", 3);

    final String translation;
    final int monthNumber;

    Month (String translation, int monthNumber) {
        this.translation = translation;
        this.monthNumber = monthNumber;
    }

}
