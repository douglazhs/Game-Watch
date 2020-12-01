package br.com.GameWatch.main;

public class Requirements {
	private String cpu;
	private String gpu;
	private double sizeDisk;
	private int ram;
	
	public Requirements(String cpu, String gpu, double sizeDisk, int ram) {
		setCpu(cpu);
		setGpu(gpu);
		setSizeDisk(sizeDisk);
		setRam(ram);
	}

	//Getters and Setters ----------------------------------------------------------------------------------------------------
	
	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		if(!cpu.isEmpty())
			this.cpu = cpu;
		else
			throw new IllegalArgumentException("CPU INVALIDA.");
	}

	public String getGpu() {
		return gpu;
	}

	public void setGpu(String gpu) {
		if(!gpu.isEmpty())
			this.gpu = gpu;
		else
			throw new IllegalArgumentException("GPU INVALIDA.");
	}

	public double getSizeDisk() {
		return sizeDisk;
	}

	public void setSizeDisk(double sizeDisk) {
		if(sizeDisk > 0)
			this.sizeDisk = sizeDisk;
		else
			throw new IllegalArgumentException("ESPACO EM DISCO INVALIDO.");
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		if(ram > 0)
			this.ram = ram;
		else
			throw new IllegalArgumentException("RAM INVALIDA.");
	}
	
	//toString ----------------------------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		String total = "";
		
		total += "[REQUISITOS MINIMOS]";
		total += "\nCPU: " + this.cpu;
		total += "\nGPU: " + this.gpu;
		total += "\nDISCO: " + this.sizeDisk;
		total += "\nRAM: " + this.ram;
		return total;
	}
}
