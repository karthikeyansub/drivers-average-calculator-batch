package com.drivers.data.model;

public class DriversData {

	private String name;
	
	private Double totalLapTime;
	
	private Integer numberOfLabs;
	
	private Double average;
	
	public DriversData(String name, Double totalLapTime, Integer numberOfLabs, Double average) {
		super();
		this.name = name;
		this.totalLapTime = totalLapTime;
		this.numberOfLabs = numberOfLabs;
		this.average = average;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalLapTime() {
		return totalLapTime;
	}

	public void setTotalLapTime(Double totalLapTime) {
		this.totalLapTime = totalLapTime;
	}

	public Integer getNumberOfLabs() {
		return numberOfLabs;
	}

	public void setNumberOfLabs(Integer numberOfLabs) {
		this.numberOfLabs = numberOfLabs;
	}
	
	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((average == null) ? 0 : average.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((numberOfLabs == null) ? 0 : numberOfLabs.hashCode());
		result = prime * result + ((totalLapTime == null) ? 0 : totalLapTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DriversData other = (DriversData) obj;
		if (average == null) {
			if (other.average != null)
				return false;
		} else if (!average.equals(other.average))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numberOfLabs == null) {
			if (other.numberOfLabs != null)
				return false;
		} else if (!numberOfLabs.equals(other.numberOfLabs))
			return false;
		if (totalLapTime == null) {
			if (other.totalLapTime != null)
				return false;
		} else if (!totalLapTime.equals(other.totalLapTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DriversData [name=" + name + ", totalLapTime=" + totalLapTime + ", numberOfLabs=" + numberOfLabs + ", average=" + average + "]";
	}

}
