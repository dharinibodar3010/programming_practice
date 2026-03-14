package model;

public class Trainer {

    private int trainerId;
    private String name;
    private String specialty;
    private String schedule;

    public Trainer() {}

    public Trainer(String name, String specialty, String schedule) 
    {
        this.name = name;
        this.specialty = specialty;
        this.schedule = schedule;
    }

	
	public int getTrainerId() {
		return trainerId;
	}

	
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public String getSpecialty() {
		return specialty;
	}

	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	
	public String getSchedule() {
		return schedule;
	}

	
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
}