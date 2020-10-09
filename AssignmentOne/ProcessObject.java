package assignmentOne;

public class ProcessObject {

    private String name;
    private String owner;
    private int pid;
    private int numberOfThreads;
    private int percentageOfCPU;
    private int totalCPUTimeUsed;

    public ProcessObject(String name, String owner, int pid, int numberOfThreads, int percentageOfCPU, int totalCPUTimeUsed) {
        this.name = name;
        this.owner = owner;
        this.pid = pid;
        this.numberOfThreads = numberOfThreads;
        this.percentageOfCPU = percentageOfCPU;
        this.totalCPUTimeUsed = totalCPUTimeUsed;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public int getPid() {
        return pid;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public int getPercentageOfCPU() {
        return percentageOfCPU;
    }

    public int getTotalCPUTimeUsed() {
        return totalCPUTimeUsed;
    }

    @Override
    public String toString() {
        String output = "Name: " + this.name + "\n" + "Owner: " + this.owner + "\n" + "PID: " + this.pid + "\n" + "Number of Threads: " + this.numberOfThreads + "\n" + "CPU Percentage: " + this.percentageOfCPU + "\n" + "Total CPU Time Used: " + this.totalCPUTimeUsed + "\n";
        return output;
    }

}
