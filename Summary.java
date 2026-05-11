import java.util.stream.Collector;

class Summary {

    long count;
    double totalSalary;
    int minAge = Integer.MAX_VALUE;
    int maxAge = Integer.MIN_VALUE;

    void accept(EmployeeRecord r) {
        count++;
        totalSalary += r.salary;
        minAge = Math.min(minAge, r.age);
        maxAge = Math.max(maxAge, r.age);
    }

    Summary combine(Summary other) {
        this.count += other.count;
        this.totalSalary += other.totalSalary;
        this.minAge = Math.min(this.minAge, other.minAge);
        this.maxAge = Math.max(this.maxAge, other.maxAge);
        return this;
    }

    static Collector<EmployeeRecord, Summary, Summary> collector() {
        return Collector.of(
                Summary::new,
                Summary::accept,
                Summary::combine
        );
    }

    @Override
    public String toString() {
        double avg = (count == 0) ? 0 : totalSalary / count;

        return "Processed Rows: " + count +
                ", Avg Salary: " + String.format("%.2f", avg) +
                ", Min Age: " + minAge +
                ", Max Age: " + maxAge;
    }
}