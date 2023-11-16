package christmas.domain;

import java.util.List;
import java.util.Objects;

public class VisitDate {
    public static final String INVALID_DATE_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;

    private final int month;
    private final int date;

    public VisitDate(int month, int date) {
        validateDate(date);

        this.month = month;
        this.date = date;
    }

    private void validateDate(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(INVALID_DATE_EXCEPTION_MESSAGE);
        }
    }

    public boolean isIncludedIn(List<Integer> applicableDates) {
        return applicableDates.contains(date);
    }

    public int calculateDifferenceInDate(int date) {
        return this.date - date;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitDate visitDate = (VisitDate) o;
        return month == visitDate.month && date == visitDate.date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, date);
    }
}
