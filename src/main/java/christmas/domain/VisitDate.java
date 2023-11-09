package christmas.domain;

import java.util.Objects;

public class VisitDate {
    private static final int DECEMBER = 12;
    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    private static final String INVALID_DATE_EXCEPTION_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final int month;
    private final int date;

    public VisitDate(int date) {
        validateDate(date);

        this.month = DECEMBER;
        this.date = date;
    }

    private void validateDate(int date) {
        if (date < MIN_DATE || date > MAX_DATE) {
            throw new IllegalArgumentException(INVALID_DATE_EXCEPTION_MESSAGE);
        }
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
