package pojo;

public class GetCourse {
    private String url;
    private String services;
    private String expertise;
    private Courses Courses;
    private String instructor;
    private String linkedIn;

    public GetCourse() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return this.services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return this.expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Courses getCourses() {
        return this.Courses;
    }

    public void setCourses(Courses courses) {
        this.Courses = courses;
    }

    public String getInstructor() {
        return this.instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return this.linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

}
