package TaskandWishManagementApplicationforChildren;

import java.time.LocalDateTime;

public class Task {
	private String id;
	public String title;
	private String description;
	public LocalDateTime deadlineDate;
	public LocalDateTime deadlineTime;
	private int points;
	private int level;
	private boolean completed; 
	private boolean approved;
	private int rating;
	private String assignmentBy;
	private LocalDateTime startDateTime; 
	private LocalDateTime endDateTime;
	
	
	public Task(String id, String title, String description,LocalDateTime deadlineDate, int level) {
	    this.id =id; 
	    this.title = title;
	    this.description = description; 
	    this.deadlineDate= deadlineDate;
	    this.level = level;
	  
	}

	public void approveTask(int rating) {
	    if (this.completed && !this.approved) {
	        this.points = rating;
	        this.rating = rating;
	        this.approved = true;
	    }
	}

	
	public String getId() {
	    return id; 
	} 
	public String getTitle() {
	    return title;
	} 

	public LocalDateTime getDeadlineDate() {
	    return deadlineDate;
	}
	public String toString() {
	    return "Task ID: " + id + ", Title: " + title + ", Due: " + deadlineDate + ", Points: " + points;
	}
	public boolean isCompleted() {
	    return completed;
	}

	public int getPoints() {
	    return points;
	}
	public LocalDateTime getStartDateTime() { return startDateTime; }
	public LocalDateTime getEndDateTime() { return endDateTime; }
	
	public void completeTask(int points) {
	    this.completed = true;
	    this.points = points;
	}


	public void setActivityTime(LocalDateTime start, LocalDateTime end) {
	    this.startDateTime = start;
	    this.endDateTime = end;
	}
	public String getDescription() {
	    return description;
	}

	public boolean isApproved() {
	    return approved;
	}

	public int getRating() {
	    return rating;
	}
	public int getLevel(int totalPoints) {
        
        if (totalPoints >= 80) { 
            return 4;
        } else if (totalPoints >= 60) {
            return 3;
        } else if (totalPoints >= 40) {
            return 2;
        } else {
            return 1;
        }
    }


}


