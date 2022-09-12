package stopcu.db.entity;

public class Student {

    private short id;
    private String name;
    private short classId;

    public Student(short id, String name, short classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
    }

    public short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public short getClassId() {
        return classId;
    }

    @Override
    public String toString() {
        return "Student [classId=" + classId + ", id=" + id + ", name=" + name + "]";
    }

}
